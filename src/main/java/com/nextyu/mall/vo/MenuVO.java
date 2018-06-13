package com.nextyu.mall.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 菜单
 * created on 2017-03-15 13:38
 *
 * @author nextyu
 */
@Getter
@Setter
public class MenuVO {
    private Long id;
    private String name;
    private String iconfont;
    private String url;

    private List<MenuVO> sub;

    public MenuVO() {
    }

    public MenuVO(Long id, String name, String iconfont, String url) {
        this.id = id;
        this.name = name;
        this.iconfont = iconfont;
        this.url = url;
    }


}
