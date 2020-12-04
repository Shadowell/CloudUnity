//package com.shadowell.cloud.wechat.config
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.scheduling.annotation.EnableAsync
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
//import java.util.concurrent.Executor
//import java.util.concurrent.ThreadPoolExecutor
//
//
//@EnableAsync
//@Configuration
//class TaskPoolConfiguration{
//
//    @Bean("taskExecutor")
//    fun taskExecutor(): Executor {
//        val executor = ThreadPoolTaskExecutor()
//        executor.setCorePoolSize(10)
//        executor.setMaxPoolSize(20)
//        executor.setQueueCapacity(200)
//        executor.setKeepAliveSeconds(60)
//        executor.setThreadNamePrefix("taskExecutor-")
//        executor.setAwaitTerminationSeconds(60)
//        executor.setRejectedExecutionHandler(ThreadPoolExecutor.CallerRunsPolicy())
//        return executor
//    }
//}
