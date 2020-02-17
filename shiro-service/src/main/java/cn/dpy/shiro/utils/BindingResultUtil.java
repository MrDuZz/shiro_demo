package cn.dpy.shiro.utils;

import cn.dpy.shiro.controller.MessageResult;
import org.springframework.validation.BindingResult;


public class BindingResultUtil {
    public static MessageResult validate(BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String message=bindingResult.getFieldError().getDefaultMessage();
            return MessageResult.error(500, message);
        }else {
            return null;
        }
    }
}
