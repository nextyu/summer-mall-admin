package com.nextyu;

import com.nextyu.mall.parse.MallPageProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.utils.HttpConstant;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SummerMallAdminApplicationTests {

    @Autowired
    private MallPageProcessor mallPageProcessor;


    @Test
    public void testSpider() throws Exception {
        Request request = new Request();
        request.setMethod(HttpConstant.Method.POST);

        //设置POST参数
        Map<String, Object> map = new HashMap<>();
        map.put("wp", "eyJwYWdlIjoxNSwic29ydCI6MSwiY2lkIjpudWxsLCJzZWFyY2giOiJcdTg4ZTRcdTViNTAiLCJ0eXBlIjpudWxsfQ==");
        request.setRequestBody(HttpRequestBody.form(map, "utf-8"));

        request.setUrl("http://mantao.lovelytao.com/saber/index/search");

        Spider.create(mallPageProcessor).addRequest(request)
                .thread(1) //开启2个线程抓取
                .run(); //启动爬虫
    }

}
