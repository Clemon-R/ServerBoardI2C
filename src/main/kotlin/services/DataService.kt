package fr.rtone.services

import fr.rtone.repositories.DataRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DataService{
    @Autowired
    private val dataRepository: DataRepository? = null

}