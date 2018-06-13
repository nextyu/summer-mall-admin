package com.nextyu.mall.parse;

import lombok.Data;

import java.util.List;

/**
 * created on 2017-07-31 17:10
 *
 * @author nextyu
 */
@Data
public class DetailInfo {
    private String image;
    private List<String> auctionImages;
    private List<String> detailImages;
    private String title;
    private String recommend;
    private String oPrice;
    private String price;
}
