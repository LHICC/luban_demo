package com.lb.common.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lb.common.util.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 封装分页查询结果
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private Long total;
    private List<T> rows;
    private Integer code; // 编码：200成功，0和其它数字为失败
    private String msg; // 错误信息


    /**
     * PageResult构造函数
     *
     * @param p:
     * @param vos:
     * @return PageResult<V>
     */
    private static <V, P> PageResult<V> getPageResult(Page<P> p, List<V> vos) {
        return new PageResult<>(
                p.getTotal(),
                vos, 200, "查询成功");
    }

    /**
     * 返回空分页结果
     *
     * @param p   MybatisPlus的分页结果
     * @param <V> 目标VO类型
     * @param <P> 原始PO类型
     * @return VO的分页对象
     */
    public static <V, P> PageResult<V> empty(Page<P> p) {
        return getPageResult(p, Collections.emptyList());
    }


    /**
     * 将MybatisPlus分页结果转为 VO分页结果
     *
     * @param p              MybatisPlus的分页结果
     * @param targetSupplier 目标VO类型的字节码
     * @param <V>            目标VO类型
     * @param <P>            原始PO类型
     * @return VO的分页对象
     */
    public static <V, P> PageResult<V> of(Page<P> p, Supplier<V> targetSupplier) {
        // 非空校验,无数据，返回空结果
        List<P> records = p.getRecords();
        if (records.isEmpty()) return empty(p);
        // 数据转换
        List<V> vos = BeanUtil.copyToList(records, targetSupplier);
        // 封装返回
        return getPageResult(p, vos);
    }


    /**
     * 将MybatisPlus分页结果转为 VO分页结果，允许用户自定义PO到VO的转换方式
     *
     * @param p         MybatisPlus的分页结果
     * @param convertor PO到VO的转换函数
     * @param <V>       目标VO类型
     * @param <P>       原始PO类型
     * @return VO的分页对象
     */
    public static <V, P> PageResult<V> of(Page<P> p, Function<P, V> convertor) {
        // 非空校验
        List<P> records = p.getRecords();
        if (records == null || records.size() <= 0) {
            // 无数据，返回空结果
            return empty(p);
        }
        // 数据转换
        List<V> vos = records.stream().map(convertor).collect(Collectors.toList());
        // 封装返回
        return getPageResult(p, vos);
    }

    /**
     * 将MybatisPlus分页结果转为 PageResult
     *
     * @param p:MybatisPlus的分页结果
     * @return PageResult<P>PageResult
     */
    public static <P> PageResult<P> of(Page<P> p) {
        // 非空校验，无数据->返回空结果
        List<P> records = p.getRecords();
        if (records.isEmpty()) return empty(p);
        // 封装返回
        return getPageResult(p, records);
    }

}
