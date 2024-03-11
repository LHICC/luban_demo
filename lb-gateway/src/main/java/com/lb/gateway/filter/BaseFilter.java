package com.lb.gateway.filter;

import cn.hutool.core.text.StrPool;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.lb.common.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 基础 网关过滤器
 */
@Slf4j
public abstract class BaseFilter implements GlobalFilter, Ordered {

    protected static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    /**
     * 未认证
     *
     * @param response   响应
     * @param code       状态码
     * @param msg        消息
     * @param httpStatus 状态
     * @return Mono<Void>
     */
    protected Mono<Void> unAuth(ServerHttpResponse response, int code, String msg, HttpStatus httpStatus) {
        response.setStatusCode(httpStatus != null ? httpStatus : HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        R tokenError = R.fail(code, msg);

        String result = JSON.toJSONString(tokenError);
        DataBuffer buffer = response.bufferFactory().wrap(result.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Flux.just(buffer));
    }

    /**
     * 添加请求头
     *
     * @param exchange 请求
     * @param headers  请求头
     */
    protected void addHeaders(ServerWebExchange exchange, Map headers) {
        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            httpHeader.setAll(headers);
        };

        ServerHttpRequest serverHttpRequest = exchange.getRequest().mutate().headers(httpHeaders).build();
        exchange.mutate().request(serverHttpRequest).build();
    }

    /**
     * 获取请求头
     *
     * @param request  请求
     * @param name     请求头名称
     * @return 请求头
     */
    protected String getHeaderFromRequest(ServerHttpRequest request, String name) {
        String heander = request.getHeaders().getFirst(name);
        if (StrUtil.isBlank(heander)) {
            heander = request.getQueryParams().getFirst(name);
        }
        return heander;
    }

    /**
     * 判断是否忽略资源路径
     *
     * @param collection 资源路径集合
     * @param value      当前请求路径
     * @return boolean 是否忽略
     */
    protected boolean anyMatch(Collection<String> collection, String value) {
        return collection.stream().anyMatch(
                (item) -> value.equals(item) || value.startsWith(item) || ANT_PATH_MATCHER.match(item, value)
        );
    }

    /**
     * 格式化路径
     *
     * @param route 路由
     * @param path  路径
     * @return 格式化后的路径
     */
    protected String formatPath(Route route, String path) {
        for (GatewayFilter filter : route.getFilters()) {
            String delegate = ((OrderedGatewayFilter) filter).getDelegate().toString();
            if (delegate.contains("StripPrefix parts")) { //[StripPrefix parts = 1]
                Integer parts = Integer.parseInt(delegate.replaceAll("StripPrefix parts", "").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("=", "").replaceAll("\\s", ""));
                return splicingPath(this.splitPath(path), parts);
            }
        }
        return path;
    }

    /**
     * 将路径拆分成数组
     *
     * @param path 路径
     * @return 拆分后的数组
     */
    protected List<String> splitPath(String path) {
        String[] paths = StrUtil.splitToArray(path, StrPool.SLASH);
        List<String> list = Arrays.asList(paths).stream().filter(StrUtil::isNotBlank).collect(Collectors.toList());
        return list;
    }

    /**
     * 将路径拼接
     *
     * @param paths       路径数组
     * @param stripPrefix 前缀
     * @return 拼接后的路径
     */
    protected String splicingPath(List<String> paths, Integer stripPrefix) {
        if (stripPrefix != null) {
            for (int i = 0; i < stripPrefix; i++) {
                String removePath = paths.remove(0);// 删除第一个
                log.debug("删除前缀 index:{} value:{}", i, removePath);
            }
        }

        String path = StrPool.SLASH +  StrUtil.join(StrPool.SLASH, paths);
        return path;
    }

}
