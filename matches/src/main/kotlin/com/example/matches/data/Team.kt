package com.example.matches.data

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table
data class Team(
    var name: String,
    @Id @GeneratedValue var id: Long? = null
)