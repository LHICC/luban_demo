package com.lb.product.controller.v1;


import cn.hutool.core.bean.BeanUtil;
import com.lb.common.vo.PageResult;
import com.lb.common.vo.R;
import lb.hl.product.entity.ProductEntity;
import com.lb.product.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lb.hl.product.dto.ProductDto;
import lb.hl.product.dto.ProductQueryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器 产品管理
 * </p>
 *
 * @author hl
 * @since 2024-03-07
 */
@RestController
@RequestMapping("/product")
@Api(value = "ProductController", tags = "产品管理")
@Slf4j
public class ProductController {

    @Resource
    private ProductService productService;

    /**
     * 产品列表查询
     */
    @GetMapping("list")
    @ApiOperation(value = "产品列表查询", notes = "产品列表查询")
    public PageResult<List<ProductEntity>> list(ProductQueryDto dto) {
        return PageResult.of(productService.queryList(dto));
    }

    /**
     * 产品添加
     */
    @PostMapping("add")
    @ApiOperation(value = "产品添加", notes = "产品添加")
    public R add(@RequestBody ProductDto dto) {
        ProductEntity productEntity = BeanUtil.copyProperties(dto, ProductEntity.class);
        return R.isSuccess(productService.save(productEntity));
    }

    /**
     * 产品更新
     */
    @PutMapping("update")
    @ApiOperation(value = "产品更新", notes = "产品更新")
    public R update(@RequestBody ProductDto dto) {
        ProductEntity productEntity = BeanUtil.copyProperties(dto, ProductEntity.class);
        return R.isSuccess(productService.updateById(productEntity));
    }

    /**
     * 产品删除
     */
    @DeleteMapping("delete/{id}")
    @ApiOperation(value = "产品删除", notes = "产品删除")
    public R delete(@PathVariable Integer id) {
        return R.isSuccess(productService.removeById(id));
    }


}
