package fr.rtone

import fr.rtone.mqtt.MqttClient
import fr.rtone.repositories.DataRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.core.task.TaskExecutor

@Configuration
class Configuration {
    @Bean
    fun taskExecutor() : TaskExecutor {
        return SimpleAsyncTaskExecutor()
    }

    @Bean
    fun schedulingRunner(executor: TaskExecutor, dataRepository: DataRepository) : CommandLineRunner {
        return CommandLineRunner{
            executor.execute(MqttClient(dataRepository))
        }
    }
}