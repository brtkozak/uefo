package com.example.orders

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SeatRepository : JpaRepository<Seat, Long>