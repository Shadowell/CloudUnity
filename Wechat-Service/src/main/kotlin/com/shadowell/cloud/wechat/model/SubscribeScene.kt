package com.shadowell.cloud.wechat.model

enum class SubscribeScene {
    // 公众号搜索，
    ADD_SCENE_SEARCH,

    // 公众号迁移，
    ADD_SCENE_ACCOUNT_MIGRATION,

    // 名片分享
    ADD_SCENE_PROFILE_CARD,

    // 扫描二维码
    ADD_SCENE_QR_CODE,

    // 图文页内名称点击
    ADD_SCENE_PROFILE_LINK,

    // 图文页右上角菜单
    ADD_SCENE_PROFILE_ITEM,

    // 支付后关注
    ADD_SCENE_PAID,

    // 微信广告
    ADD_SCENE_WECHAT_ADVERTISEMENT,

    // 其他
    ADD_SCENE_OTHERS
}
