package com.example.atendees

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AtendeeRepository : JpaRepository<Atendee, Long> {
    fun findByPesel(pesel: String): Optional<Atendee>
}
