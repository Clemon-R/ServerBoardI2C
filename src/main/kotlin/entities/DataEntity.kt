package fr.rtone.entities

import javax.persistence.*

@Entity
@Table(name = "datas")
data class DataEntity (
    @Id
    @GeneratedValue
    val id: Int = 0
)