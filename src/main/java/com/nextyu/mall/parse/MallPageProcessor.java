package com.nextyu.mall.parse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nextyu.mall.service.UploadService;
import com.nextyu.mall.util.UUIDUtil;
import com.nextyu.mall.vo.ProductVO;
import com.qiniu.storage.model.DefaultPutRet;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * created on 2017-07-31 14:21
 *
 * @author nextyu
 */
@Component
public class MallPageProcessor implements PageProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MallPageProcessor.class);

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    private Long categoryId;
    private int pages = 1; // 抓取多少页

    @Autowired
    private UploadService uploadService;

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    private Site site = Site.me()
            .setRetryTimes(3)
            .setSleepTime(1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");

    @Override
    public void process(Page page) {

        if (page.getUrl().regex(".*/index\\?cid=.*").match()) {

            String html = page.getHtml().get();
            if (StringUtils.isEmpty(html)) {
                return;
            }

            LOGGER.info(html);

            String url = page.getUrl().get();
            String cid = url.substring(url.lastIndexOf("=") + 1);
            LOGGER.debug("my cid {}", cid);

            String wp = page.getHtml().$("#wp", "value").get();

            LOGGER.debug("my wp {}", wp);
            open(wp, page);
        } else {
            String json = page.getJson().get();
            if (StringUtils.isEmpty(json)) {
                return;
            }
            JSONObject jsonObject = JSON.parseObject(json);
            JSONObject result = jsonObject.getJSONObject("result");

            if (page.getUrl().regex(".*search.*").match()) {

                String wp = result.getString("wp");
                List<ListInfo> items = JSON.parseArray(result.getString("items"), ListInfo.class);

                open(wp, page);

                for (ListInfo item : items) {
                    StringBuilder sb = new StringBuilder("http://mantao.lovelytao.com/saber/index/detailData?");
                    sb.append("itemId=").append(item.getItemId());
                    sb.append("&");
                    sb.append("activityId=").append(item.getActivityId());

                    Request request = new Request();
                    request.setMethod(HttpConstant.Method.POST);
                    request.setUrl(sb.toString());


                    page.addTargetRequest(request);
                }
            } else {
                DetailInfo detailInfo = JSON.parseObject(result.getString("item"), DetailInfo.class);
                LOGGER.debug("detailInfo {}", detailInfo);
                try {

                    ProductVO productVO = new ProductVO();
                    productVO.setTitle(detailInfo.getTitle());
                    productVO.setSubTitle(detailInfo.getTitle());
                    productVO.setSummary(detailInfo.getRecommend());

                    StringBuilder imageName = new StringBuilder("");
                    List<String> auctionImages = detailInfo.getAuctionImages();
                    int quantity = 0;
                    for (String auctionImage : auctionImages) {
                        if (2 == quantity++) {
                            break;
                        }
                        String suffix = auctionImage.substring(auctionImage.lastIndexOf('.'));
                        InputStream input = new URL(auctionImage).openStream();
                        DefaultPutRet defaultPutRet = uploadService.uploadImg(input, UUIDUtil.uuid() + suffix);
                        if (null != defaultPutRet) {
                            if (StringUtils.isNotEmpty(imageName)) {
                                imageName.append(",").append(defaultPutRet.key);
                            } else {
                                imageName.append(defaultPutRet.key);
                            }
                        }
                    }

                    productVO.setSubImages(imageName.toString());


                    imageName.setLength(0);

                    quantity = 0;
                    List<String> detailImages = detailInfo.getDetailImages();
                    for (String detailImage : detailImages) {
                        if (2 == quantity++) {
                            break;
                        }
                        String suffix = detailImage.substring(detailImage.lastIndexOf('.'));
                        InputStream input = new URL(detailImage).openStream();
                        DefaultPutRet defaultPutRet = uploadService.uploadImg(input, UUIDUtil.uuid() + suffix);
                        if (null != defaultPutRet) {
                            imageName.append("<img src=\"").append(uploadService.getImgDomain()).append(defaultPutRet.key).append("\" alt=\"\">");
                        }
                    }

                    productVO.setDetail(imageName.toString());

                    String originalPriceYuan = detailInfo.getOPrice().replace("¥", "").replace(".00", "");
                    String currentPriceYuan = detailInfo.getPrice().replace("¥", "").replace(".00", "");
                    productVO.setOriginalPriceYuan(new BigDecimal(originalPriceYuan));
                    productVO.setCurrentPriceYuan(new BigDecimal(currentPriceYuan));

                    productVO.setStockQuantity(100L);
                    productVO.setCategoryId(categoryId);
                    productVO.setStatus(1);


                    LOGGER.debug("productVO {}", productVO);

//                productService.save(productVO);

                    page.putField("productVO", productVO);

                } catch (IOException e) {
                    LOGGER.error("", e);
                }

            }
        }

    }

    private void open(String wp, Page page) {

        int i = ATOMIC_INTEGER.incrementAndGet();
        if (i == pages) {
            return;
        }

        Request request = new Request();
        request.setMethod(HttpConstant.Method.POST);

        //设置POST参数
        Map<String, Object> map = new HashMap<>();
        map.put("wp", wp);
        try {

            request.setRequestBody(HttpRequestBody.form(map, "utf-8"));
        } catch (Exception e) {
            LOGGER.error("", e);
        }

        request.setUrl("http://mantao.lovelytao.com/saber/index/search");

        page.addTargetRequest(request);

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {

        Request request = new Request();
        request.setMethod(HttpConstant.Method.POST);

        //设置POST参数
        Map<String, Object> map = new HashMap<>();
        map.put("wp", "eyJwYWdlIjozLCJzb3J0IjoxLCJjaWQiOm51bGwsInNlYXJjaCI6Ilx1NjI0Ylx1NjczYSIsInR5cGUiOm51bGx9");
        request.setRequestBody(HttpRequestBody.form(map, "utf-8"));

        request.setUrl("http://mantao.lovelytao.com/saber/index/search");

        Spider.create(new MallPageProcessor()).addRequest(request)
                .thread(1) //开启2个线程抓取
                .run(); //启动爬虫
    }

}
