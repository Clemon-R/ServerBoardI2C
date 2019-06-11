package fr.rtone.mqtt

import fr.rtone.entities.DataEntity
import fr.rtone.repositories.DataRepository
import org.springframework.stereotype.Component

@Component
class MqttClient(private val dataRepository: DataRepository) : Runnable {

    override fun run() {
        dataRepository.save(DataEntity())
        println("${dataRepository.findAll().size}")
    }

}