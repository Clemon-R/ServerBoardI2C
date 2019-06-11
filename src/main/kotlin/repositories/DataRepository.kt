package fr.rtone.repositories

import fr.rtone.entities.DataEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DataRepository : JpaRepository<DataEntity, Int>