//package com.shadowell.cloud.productService.Config
//
//import com.netflix.zuul.ZuulFilter
//import com.netflix.zuul.context.RequestContext
//import org.apache.logging.log4j.LogManager
//import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE
//import org.springframework.stereotype.Component
//
//
//@Component
//class TokenFilter: ZuulFilter() {
//    private var log = LogManager.getLogger(TokenFilter::class)
//
//    override fun filterOrder(): Int {
//        return 0
//    }
//
//    override fun filterType(): String {
//        return PRE_TYPE
//    }
//
//    override fun shouldFilter(): Boolean {
//        return true
//    }
//
//    override fun run(): Any {
//        val ctx = RequestContext.getCurrentContext()
//        val request = ctx.request
//        val accessToken = request.getParameter("token")
//        if (accessToken == null){
//            log.warn("Token is empty!")
//            ctx.setSendZuulResponse(false)
//            ctx.responseStatusCode
//            try {
//                ctx.response.writer.write("Tonken is empty!")
//            }catch (e: Exception) {
//                return Exception("Tonken is empty!")
//            }
//        }
//        log.info("ok")
//        return 0
//    }
//}