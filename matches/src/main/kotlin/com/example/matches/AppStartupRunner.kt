package com.example.matches

import com.example.matches.entity.Match
import com.example.matches.entity.SeatTicket
import com.example.matches.entity.Team
import com.example.matches.repository.MatchRepository
import com.example.matches.repository.SeatTicketRepository
import com.example.matches.repository.TeamRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@Component
class AppStartupRunner(
    @Lazy val matchRepository: MatchRepository,
    @Lazy val seatTicketRepository: SeatTicketRepository,
    @Lazy val teamRepository: TeamRepository
) : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        Executors.newSingleThreadScheduledExecutor().schedule({
            val t1 = Team(null, "Arka Gdynia")
            val t2 = Team(null, "Lech Poznań")
            val t3 = Team(null, "Wisła Kraków")
            teamRepository.save(t1)
            teamRepository.save(t2)
            teamRepository.save(t3)

            val m1 = Match(null, "Arka Gdynia - Lech Poznań", ArrayList(), LocalDate.of(2021, 7, 8), 90, t1, t2)
            val m2 = Match(null, "Lech Poznań - Wisła Kraków", ArrayList(), LocalDate.of(2021, 8, 13), 90, t2, t3)
            matchRepository.save(m1)
            matchRepository.save(m2)

            val seats1 = listOf(
                SeatTicket(null, "A", "16A", 80.0, m1),
                SeatTicket(null, "A", "16B", 80.0, m1),
                SeatTicket(null, "A", "17", 90.0, m1),
                SeatTicket(null, "A", "21A", 80.0, m1),
                SeatTicket(null, "A", "21B", 80.0, m1),
                SeatTicket(null, "B", "12", 90.0, m1),
                SeatTicket(null, "B", "13", 90.0, m1),
            ).toMutableList()

            seats1.forEach { seatTicketRepository.save(it) }
            m1.allAvailableSeats = seats1
            matchRepository.save(m1)
        }, 5, TimeUnit.SECONDS)
    }
}
