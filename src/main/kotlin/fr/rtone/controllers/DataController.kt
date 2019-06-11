package fr.rtone.controllers

import fr.rtone.entities.DataEntity
import fr.rtone.services.DataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

data class Greeting(val id: Long, val content: String)

@RestController
@RequestMapping("/data")
class DataController {

    @Autowired
    val dataService: DataService? = null

    @GetMapping("/all")
    fun allDatas() : List<DataEntity>
    {
        return dataService!!.getAll()
    }

}