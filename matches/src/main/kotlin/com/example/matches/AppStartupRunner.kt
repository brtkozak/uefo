package com.example.matches

import com.example.matches.entity.Match
import com.example.matches.entity.SeatTicket
import com.example.matches.entity.Team
import com.example.matches.repository.MatchRepository
import com.example.matches.repository.SeatTicketRepository
import com.example.matches.repository.TeamRepository
import org.springframework.boot.ApplicationArguments

import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class AppStartupRunner(
    val matchRepository: MatchRepository,
    val seatTicketRepository: SeatTicketRepository,
    val teamRepository: TeamRepository
) : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        matchRepository.deleteAllInBatch()
        seatTicketRepository.deleteAllInBatch()
        teamRepository.deleteAllInBatch()

        val t1 = Team(1L, "Arka Gdynia")
        val t2 = Team(2L, "Lech Poznań")
        val t3 = Team(3L, "Wisła Kraków")
        teamRepository.save(t1)
        teamRepository.save(t2)
        teamRepository.save(t3)

        val m1 = Match(1L, "Arka Gdynia - Lech Poznań", ArrayList(), LocalDate.of(2021, 7, 8), 90, t1, t2)
        val m2 = Match(1L, "Lech Poznań - Wisła Kraków", ArrayList(), LocalDate.of(2021, 8, 13), 90, t2, t3)
        matchRepository.save(m1)
        matchRepository.save(m2)

        val seats1 = listOf(
            SeatTicket(1L, "A", "16A", 80.0, m1),
            SeatTicket(2L, "A", "16B", 80.0, m1),
            SeatTicket(3L, "A", "17", 90.0, m1),
            SeatTicket(4L, "A", "21A", 80.0, m1),
            SeatTicket(5L, "A", "21B", 80.0, m1),
            SeatTicket(6L, "B", "12", 90.0, m1),
            SeatTicket(7L, "B", "13", 90.0, m1),
        ).toMutableList()

        seats1.forEach { seatTicketRepository.save(it) }
        m1.allAvailableSeats = seats1
    }
}
