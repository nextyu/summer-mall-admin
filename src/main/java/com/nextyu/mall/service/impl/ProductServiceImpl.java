package com.nextyu.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nextyu.mall.constant.RedisConstants;
import com.nextyu.mall.dao.ProductDetailMapper;
import com.nextyu.mall.dao.ProductMapper;
import com.nextyu.mall.entity.Product;
import com.nextyu.mall.entity.ProductDetail;
import com.nextyu.mall.query.ProductQuery;
import com.nextyu.mall.repository.ProductRepository;
import com.nextyu.mall.service.ProductService;
import com.nextyu.mall.service.UploadService;
import com.nextyu.mall.util.DateTimeUtil;
import com.nextyu.mall.util.MoneyUtil;
import com.nextyu.mall.vo.ProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductDetailMapper productDetailMapper;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean save(ProductVO productVO) {
        Product product = new Product();
        BeanUtils.copyProperties(productVO, product);

        String subImages = productVO.getSubImages();
        if (StrUtil.isNotEmpty(subImages)) {
            product.setMainImage(subImages.split(",")[0]);
        }

        product.setOriginalPrice(MoneyUtil.yuan2Fen(productVO.getOriginalPriceYuan()));
        product.setCurrentPrice(MoneyUtil.yuan2Fen(productVO.getCurrentPriceYuan()));


        if (null != product.getId()) {
            Product existProduct = productMapper.selectByPrimaryKey(product.getId());
            product.setVersion(existProduct.getVersion());
            product.setUpdateTime(DateTimeUtil.currentTimeMillis());
            int rows = productMapper.updateByPrimaryKeySelective(product);
            if (rows <= 0) {
                throw new RuntimeException("更新产品失败");
            }

            ProductDetail productDetail = new ProductDetail();
            productDetail.setProductId(product.getId());
            productDetail.setDetail(productVO.getDetail());
            productDetail.setUpdateTime(DateTimeUtil.currentTimeMillis());

            rows = productDetailMapper.updateByProductId(productDetail);
            if (rows <= 0) {
                throw new RuntimeException("更新产品详情失败");
            }

        } else {
            product.setCreateTime(DateTimeUtil.currentTimeMillis());
            int rows = productMapper.insertSelective(product);

            if (rows <= 0) {
                throw new RuntimeException("插入产品失败");
            }

            ProductDetail productDetail = new ProductDetail();
            productDetail.setProductId(product.getId());
            productDetail.setDetail(productVO.getDetail());
            productDetail.setCreateTime(DateTimeUtil.currentTimeMillis());

            rows = productDetailMapper.insertSelective(productDetail);
            if (rows <= 0) {
                throw new RuntimeException("插入产品详情失败");
            }
        }


        syncESAndRedis(product.getId(),product.getStatus());

        return true;
    }

    /**
     * 同步ES以及Redis
     * @param id
     * @param status
     */
    private void syncESAndRedis(Long id, Integer status) {
        if (1 == status) {
            productRepository.save(productMapper.selectByPrimaryKey(id));
            redisTemplate.opsForValue().set(RedisConstants.PRODUCT_PREFIX + id, productDetailMapper.getByProductId(id).getDetail());
        } else if (0 == status) {
            productRepository.deleteById(id);
            redisTemplate.delete(RedisConstants.PRODUCT_PREFIX + id);
        }
    }

    @Override
    public Boolean update(ProductVO productVO) {
        Product product = new Product();
        BeanUtils.copyProperties(productVO, product);
        int result = productMapper.updateByPrimaryKeySelective(product);
        return result > 0;
    }

    @Override
    public ProductVO getById(Long id) {
        Product product = productMapper.selectByPrimaryKey(id);
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(product, productVO);

        ProductDetail productDetail = productDetailMapper.getByProductId(id);
        if (null != productDetail) {
            productVO.setDetail(productDetail.getDetail());
        }

        String subImages = product.getSubImages();
        if (StrUtil.isNotEmpty(subImages)) {
            List<String> images = new ArrayList<>();
            String[] strings = subImages.split(",");
            for (String string : strings) {
                images.add(uploadService.getImgDomain() + string);
            }
            productVO.setImages(images);
        }

        productVO.setOriginalPriceYuan(MoneyUtil.fen2Yuan(productVO.getOriginalPrice()));
        productVO.setCurrentPriceYuan(MoneyUtil.fen2Yuan(product.getCurrentPrice()));

        return productVO;
    }

    @Override
    public List<ProductVO> listPage(ProductQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<ProductVO> productVOS = null;
        return productVOS;
    }

    @Override
    public List<ProductVO> listAll() {
        List<ProductVO> productVOS = null;
        return productVOS;
    }

    @Override
    public PageInfo<ProductVO> getPageInfo(ProductQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        List<ProductVO> productVOS = productMapper.list(query);
        for (ProductVO productVO : productVOS) {
            productVO.setMainImage(uploadService.getImgDomain() + productVO.getMainImage());
        }
        return new PageInfo<>(productVOS);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateStatus(Long id, Integer status) {

        Product product = productMapper.selectByPrimaryKey(id);
        if (null == product) {
            return false;
        }

        Product updateProduct = new Product();
        updateProduct.setId(id);
        updateProduct.setStatus(status);
        updateProduct.setUpdateTime(DateTimeUtil.currentTimeMillis());
        updateProduct.setVersion(product.getVersion());
        int rows = productMapper.updateByPrimaryKeySelective(updateProduct);
        if (rows <= 0) {
            return false;
        }

        syncESAndRedis(id,status);


        return true;
    }

    @Override
    public Boolean updateDelete(Long id) {
        Product product = productMapper.selectByPrimaryKey(id);
        if (null == product) {
            return false;
        }
        Product updateProduct = new Product();
        updateProduct.setId(id);
        updateProduct.setIsDelete(1);
        updateProduct.setUpdateTime(DateTimeUtil.currentTimeMillis());
        updateProduct.setVersion(product.getVersion());
        int rows = productMapper.updateByPrimaryKeySelective(updateProduct);
        return rows > 0;

    }
}