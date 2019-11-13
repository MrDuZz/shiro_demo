package cn.dpy.shiro.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

/**
 * @Author: dupinyan
 * @Description:
 * @Date: 2019/11/13 15:43
 * @Version: 1.0
 */

public enum CommonStatus {

    /**
     * 表示正常状态
     */
    NORMAL("正常"),
    /**
     * 表示非法状态
     */
    ILLEGAL("非法");

    String name;

    CommonStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrdinal(){
        return this.ordinal();
    }

//    public static CommonStatus stateOf(int index) {
//        for (CommonStatus state : values()) {
//            if (state.getState() == index) {
//                return state;
//            }
//        }
//        return null;
//    }

}
