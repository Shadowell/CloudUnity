package com.shadowell.cloud.productService.Config


import org.springframework.boot.web.context.WebServerInitializedEvent
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component


@Component
class ServiceInfoConfig: ApplicationListener<WebServerInitializedEvent> {

	private var serverPort: Int = 0

	override fun onApplicationEvent(event: WebServerInitializedEvent) {
		this.serverPort = event.webServer.port
	}

	fun getServicePort(): Int {
		return this.getServicePort()
	}
}