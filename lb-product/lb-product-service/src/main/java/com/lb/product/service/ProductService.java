package com.lb.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lb.hl.product.entity.ProductEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import lb.hl.product.dto.ProductQueryDto;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hl
 * @since 2024-03-07
 */
public interface ProductService extends IService<ProductEntity> {

    Page queryList(ProductQueryDto dto);
}
