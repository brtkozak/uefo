package com.example.matches.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Team(
    @Id
    @GeneratedValue
    var id: Long? = null,
    var name: String
)