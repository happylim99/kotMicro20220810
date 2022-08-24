package com.sean.gateway.config

import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter
import org.springframework.core.Ordered
import org.springframework.http.HttpHeaders
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

/**
 * 跨域请求头处理过滤器扩展
 */
class CorsResponseHeaderFilter : GlobalFilter, Ordered {
    override fun getOrder(): Int {
        // 指定此过滤器位于NettyWriteResponseFilter之后
        // 即待处理完响应体后接着处理响应头
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER + 1
    }

    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
        return chain.filter(exchange).then(Mono.defer {
            exchange.response.headers.entries.stream()
                .filter { (_, value): Map.Entry<String?, List<String?>?> -> value != null && value.size > 1 }
                .filter { (key): Map.Entry<String, List<String?>?> -> key == HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN || key == HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS }
                .forEach { kv: MutableMap.MutableEntry<String?, List<String?>> ->
                    kv.setValue(object : ArrayList<String?>() {
                        init {
                            add(kv.value[0])
                        }
                    })
                }
            chain.filter(exchange)
        })
    }
}