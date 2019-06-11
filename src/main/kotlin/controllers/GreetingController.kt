package fr.rtone.controllers

import fr.rtone.services.DataService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicLong

data class Greeting(val id: Long, val content: String)

@RestController
@RequestMapping("/greeting")
class GreetingController {

    @Autowired
    val dataService: DataService? = null

    val counter = AtomicLong()

    @GetMapping("/default")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String) =
        Greeting(counter.incrementAndGet(), "Hello, $name")

    @PostMapping("/post")
    fun greeting2(@RequestBody data: Greeting) =
        data

}