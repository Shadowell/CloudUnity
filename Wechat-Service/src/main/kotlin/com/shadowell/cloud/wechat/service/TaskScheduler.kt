package com.shadowell.cloud.wechat.service


import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

@Component
class TaskScheduler {

    private val logger: Logger = LogManager.getLogger(TaskScheduler::class)


    /**
     *  定时调度器：
     *      1. 每天定时调用微信的数据接口并将查询到的数据持久化到MySQL
     */
    @Scheduled(fixedDelayString = "\${wechat.scheduleHour}")
    fun refreshInfo() {

    }

    fun refreshUserInfo() {

    }
}
