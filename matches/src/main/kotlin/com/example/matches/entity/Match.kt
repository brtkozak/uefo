package com.example.matches.entity

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.time.LocalDate
import javax.persistence.*

@Entity
data class Match(
    @Id @GeneratedValue
    @Column(name="match_id")
    var id: Long? = null,
    var name: String,
    @OneToMany(mappedBy = "match")
    var allAvailableSeats: MutableList<SeatTicket>,
    var beginDate: LocalDate,
    var durationTime: Int,
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    var teamOne: Team,
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    var teamTwo: Team,
)
