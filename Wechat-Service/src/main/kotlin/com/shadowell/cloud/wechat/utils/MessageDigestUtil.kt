package com.shadowell.cloud.wechat.utils

import java.security.MessageDigest

object MessageDigestUtil {

    fun md5(input: String): String {
        val digest = MessageDigest.getInstance("MD5")
        val result = digest.digest(input.toByteArray())
        return toHex(result)
    }

    fun sha1(input: String): String {
        val digest = MessageDigest.getInstance("SHA-1")
        val result = digest.digest(input.toByteArray())
        return toHex(result)
    }

    fun sha256(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val result = digest.digest(input.toByteArray())
        return toHex(result)
    }

    //转成16进制
    fun toHex(byteArray: ByteArray): String {

        //转成16进制
        val result = with(StringBuilder()) {
            byteArray.forEach {
                val value = it
                val hex = value.toInt() and (0xFF)
                val hexStr = Integer.toHexString(hex)
                //println(hexStr)
                if (hexStr.length == 1) {
                    //this.append("0").append(hexStr)
                    append("0").append(hexStr)
                } else {
                    //this.append(hexStr)
                    append(hexStr)
                }
            }
            this.toString()
        }
        return result

    }
}
