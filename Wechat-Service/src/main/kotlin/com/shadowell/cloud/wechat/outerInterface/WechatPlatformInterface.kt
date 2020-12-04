package com.shadowell.cloud.wechat.outerInterface

import com.shadowell.cloud.wechat.config.FeignConfig
import com.shadowell.cloud.wechat.model.AccessToken
import com.shadowell.cloud.wechat.model.SubscribeScene
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "product-service",configuration = [FeignConfig::class],fallback = ProductServiceFallback::class)
interface WechatPlatformInterface {

    // 获取Access Token
    // GET https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
    @GetMapping (value = ["/token?grant_type={clientCredential}&appid={appId}&secret={appSecret}"])
    fun getAccessToken(@PathVariable clientCredential: String, appId: String, appSecret: String): AccessToken

    // 获取用户信息
    // GET https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
    @GetMapping(value = ["/user/info?access_token={accessToken}&openid={openId}&lang=zh_CN"])
    fun getUserInfo(@PathVariable accessToken: String, openId: String): SubscribeScene

}
