package com.shadowell.cloud.wechat.model

data class UserInfo(

    val subscribe: String,
    val openid: String,
    val nickname: String,
    val sex: Int,
    val language: String,
    val city: String,
    val province: String,
    val country: String,
    val headimgurl: String,
    val subscribe_time: Long,
    val unionid: String,
    val remark: String,
    val groupid: Int,
    val tagid_list: List<String>,
    val subscribe_scene: SubscribeScene,
    val qr_scene: Long,
    val qr_scene_str: String
)
