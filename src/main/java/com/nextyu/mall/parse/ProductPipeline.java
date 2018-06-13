package com.nextyu.mall.parse;

import com.nextyu.mall.service.ProductService;
import com.nextyu.mall.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * created on 2017-08-01 14:36
 *
 * @author nextyu
 */
@Component
public class ProductPipeline implements Pipeline {

    @Autowired
    private ProductService productService;


    @Override
    public void process(ResultItems resultItems, Task task) {
        ProductVO productVO = resultItems.get("productVO");
        productService.save(productVO);
    }
}
