package com.example.orders.service

import com.example.orders.dto.AvailableSeatsDto
import org.springframework.stereotype.Service

@Service
class AvailableSeatsService {
    fun getAvailableSeatsForMatch(matchId: Long): AvailableSeatsDto? {
        return null
    }
}
