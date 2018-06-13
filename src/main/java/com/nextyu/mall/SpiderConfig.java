package com.nextyu.mall;

import com.nextyu.mall.parse.MallPageProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * created on 2017-08-01 14:58
 *
 * @author nextyu
 */
@Configuration
public class SpiderConfig {

    private static final int SPIDER_THREAD_QUANTITY = 3;

    @Autowired
    private MallPageProcessor mallPageProcessor;

    @Autowired
    private Pipeline pipeline;

    @Bean
    public Spider spider() {
        return Spider.create(mallPageProcessor).addPipeline(pipeline)
                .thread(SPIDER_THREAD_QUANTITY);
    }

}
