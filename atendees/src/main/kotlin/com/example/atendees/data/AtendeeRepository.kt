package com.example.atendees.data

import Atendee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AtendeeRepository : JpaRepository<Atendee, Long>