package com.lb.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lb.hl.product.entity.ProductEntity;
import com.lb.product.mapper.ProductMapper;
import com.lb.product.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lb.hl.product.dto.ProductQueryDto;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2024-03-07
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, ProductEntity> implements ProductService {

    @Override
    public Page<ProductEntity> queryList(ProductQueryDto dto) {
        return this.lambdaQuery().likeRight(StrUtil.isNotBlank(dto.getKeyWord()), ProductEntity::getName, dto.getKeyWord()).page(dto.toMpPage());
    }
}
