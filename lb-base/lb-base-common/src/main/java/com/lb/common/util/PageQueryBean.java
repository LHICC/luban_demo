package com.lb.common.util;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author: hl
 * @date: 2023/10/6 9:32
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageQueryBean {
    @NotNull
    @Size(max = 20, message = "最大分页20")
    private Integer pageNum = 1;
    @NotNull
    @Size(max = 100, message = "最大显示条数100")
    private Integer pageSize = 10;
    private String KeyWord;

    public <T> Page<T> toMpPage(OrderItem... orders) {
        // 1.分页条件
        Page<T> p = new Page<>(pageNum, pageSize);
        // 2.排序条件

        // 指定排序字段
        if (orders != null) {
            p.addOrder(orders);
        }
        return p;
    }

    public <T> Page<T> toMpPage(String defaultSortBy, boolean isAsc) {
        return this.toMpPage(new OrderItem(defaultSortBy, isAsc));
    }

    public <T> Page<T> toMpPageDefaultSortByCreateTimeDesc() {
        return toMpPage("create_time", false);
    }

    public <T> Page<T> toMpPageDefaultSortByUpdateTimeDesc() {
        return toMpPage("update_time", false);
    }
}
