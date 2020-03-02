package com.shadowell.cloud.core.utils

enum class Result(code: Int, message: String) {

    SUCCESS(200, "成功"),
    FAIL(500,"失败"),
    NOT_FOUNT(404, "找不到"),
    REPEAT(992, "数据重复"),
    CodeOR(993, "系统错误"),
    ADMIN_USER_REPEAT(994, "后台用户名重复"),
    NULL_DATA(995, "没有数据"),
    TIME_PASSED(996, "时间己过期"),
    USER_NOT_FOUND(997, "找不到用户"),
    USER_REPEAT(998, "用户重复"),
    AUTH_FAILED(999, "用户名或密码错误");
}