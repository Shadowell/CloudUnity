package com.shadowell.cloud

import com.shadowell.cloud.customerService.config.TaskThreadPool
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class CustomerServiceApplicationTests {

	@Test
	fun contextLoads() {
	}

    @Test
    fun testAsync() {
        val task = TaskThreadPool()
        task.doTaskOne()
        task.doTaskTwo()
        task.doTaskTwo()

        Thread.currentThread().join()
        Thread.sleep(2000)
    }

}
