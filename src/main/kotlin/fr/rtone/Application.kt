package fr.rtone

import fr.rtone.mqtt.MqttClient
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import kotlin.concurrent.thread

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    try{
        Runtime.getRuntime().addShutdownHook(
            thread {
                MqttClient.instance?.close()
            })
    }catch (e: Exception){

    }

    SpringApplication.run(Application::class.java, *args)
}
