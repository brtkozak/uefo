package com.example.atendees

import org.springframework.boot.ApplicationArguments

import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class AppStartupRunner(
    val atendeeRepository: AtendeeRepository
) : ApplicationRunner {
    override fun run(args: ApplicationArguments) {
        atendeeRepository.deleteAllInBatch()
        atendeeRepository.save(Atendee(
            "90050410105",
            LocalDate.of(2021, 4, 17),
            LocalDate.of(2021, 10, 21)
        ))
    }
}
