package com.example.matches

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
data class Team(
    var name: String,
    @Id @GeneratedValue var id: Long? = null
)