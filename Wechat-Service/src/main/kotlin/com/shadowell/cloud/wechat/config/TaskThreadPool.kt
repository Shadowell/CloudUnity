//package com.shadowell.cloud.wechat.config;
//
//import org.springframework.scheduling.annotation.Async
//import org.springframework.stereotype.Component
//import java.util.*
//
//@Component
//class TaskThreadPool {
//
//    @Async("taskExecutor")
//    fun doTaskOne() {
//        System.out.println("开始做任务一")
//        val start: Long = System.currentTimeMillis()
//        Thread.sleep(1000)
//        val end: Long = System.currentTimeMillis()
//        System.out.println(Thread.currentThread().name + " 完成任务一，耗时：" + (end - start) + "毫秒")
//    }
//
//    @Async("taskExecutor")
//    fun doTaskTwo() {
//        System.out.println("开始做任务二")
//        val start: Long = System.currentTimeMillis()
//        Thread.sleep(1000)
//        val end: Long = System.currentTimeMillis()
//        System.out.println(Thread.currentThread().name + " 完成任务二，耗时：" + (end - start) + "毫秒")
//    }
//
//    @Async("taskExecutor")
//    fun doTaskThree() {
//        System.out.println("开始做任务三")
//        val start: Long = System.currentTimeMillis()
//        Thread.sleep(1000)
//        val end: Long = System.currentTimeMillis()
//        System.out.println(Thread.currentThread().name + " 完成任务三，耗时：" + (end - start) + "毫秒")
//    }
//
//
//
//}
//
