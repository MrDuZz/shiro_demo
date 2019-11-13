package cn.dpy.shiro.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/11/13 17:17
 * @Version: 1.0
 */
@Data
@Builder
public class Menu {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long parentId;
    private String name;
    private String url;
    private Integer sort;
    private List<Menu> subMenu;
    private String title;
    private String description;
}
