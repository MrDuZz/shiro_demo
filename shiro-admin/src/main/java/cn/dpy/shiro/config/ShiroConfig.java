package cn.dpy.shiro.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/11/13 16:36
 * @Version: 1.0
 */
@Configuration
@Slf4j
public class ShiroConfig {


    @Bean(name = "shiroFilter")
    @DependsOn({ "securityManager" })
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        log.info("ShiroConfiguration.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        // 配置退出过滤器，其中的具体退出实现shiro已经完成
        filterChainDefinitionMap.put("admin/user/logout", "logout");
        // 过滤链定义，从上向下顺序执行，一般将/**放在最下边 这是一个坑
        // authc：所有url都必须认证通过才可以访问; anon：所有url都可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");
        // 如果不设置默认会自动寻找web工程目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登陆成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * Shiro生命周期处理器
     *
     *   /*1.LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     *   负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     *   主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     *
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }


    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法，这里使用的md5
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""))
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }


    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager() {
        log.info("ShiroConfiguration.getEhCacheManager()");
        EhCacheManager cacheManager = new EhCacheManager();
        cacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return cacheManager;
    }

    @Bean(name = "simpleCookie")
    public SimpleCookie getSimpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);
        return simpleCookie;
    }


    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroRealm shiroRealm(EhCacheManager ehCacheManager) {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        shiroRealm.setCacheManager(ehCacheManager);
        return shiroRealm;
    }

    /**
     * cookie 管理器
     * @return
     */
    @Bean(name = "cookieRememberMeManager")
    @DependsOn({ "simpleCookie" })
    public CookieRememberMeManager getCookieRememberMeManager(SimpleCookie simpleCookie) {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(simpleCookie);
        /**
         * 设置 rememberMe cookie 的密钥 ，不设置 很可能：javax.crypto.BadPaddingException: Given final block not properly padded
         */
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }


    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),
     * 需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证 * 配置以下两个bean
     * (DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能 * @return
     */
    /**
     * 由Advisor决定对哪些类的方法进行AOP代理。
     * @return
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * @DependOn  :在初始化 defaultWebSecurityManager 实例前 强制先初始化 adminRealm ，ehCacheManager。。。。。
     * @param realm
     * @param ehCacheManager
     * @param cookieRememberMeManager
     * @return
     */
    @Bean(name = "securityManager")
    @DependsOn({ "adminRealm", "ehCacheManager", "cookieRememberMeManager" })
    public DefaultWebSecurityManager getDefaultWebSecurityManager(ShiroRealm realm, EhCacheManager ehCacheManager, CookieRememberMeManager cookieRememberMeManager) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 设置realm.
        defaultWebSecurityManager.setRealm(realm);
        defaultWebSecurityManager.setCacheManager(ehCacheManager);
        defaultWebSecurityManager.setRememberMeManager(cookieRememberMeManager);
        return defaultWebSecurityManager;
    }


    @Bean
    @DependsOn("securityManager")
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
