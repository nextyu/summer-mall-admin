package com.nextyu.mall.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class WelcomeVO {
    private Long userTotalCount;
    private Long productTotalCount;
    private Long orderTotalCount;
}
