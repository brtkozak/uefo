package com.example.orders.service

import com.example.orders.OrderRepository
import com.example.orders.dto.AvailableSeatsDto
import com.example.orders.dto.MatchDto
import com.example.orders.dto.TicketSeatDto
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient


@Service
class AvailableSeatsService(
    private val orderRepository: OrderRepository
) {
    companion object {
        const val MATCHES_SERVER_URL = "http://matches:8083"
    }

    private val matchesWebClient: WebClient = WebClient.builder()
        .baseUrl(MATCHES_SERVER_URL)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    fun getAvailableSeatsForMatch(matchId: Long): AvailableSeatsDto? {
        val match = getMatchById(matchId) ?: return null
        val ordersForMatch = orderRepository.getAllByMatchId(matchId)
        val availableSeats = match.allAvailableSeats.filter { availableSeatCandidate ->
            ordersForMatch.any { order ->
                order.tickets.any { ticket ->
                    ticket.seatId == availableSeatCandidate.id } }
        }
        return AvailableSeatsDto(matchId, availableSeats)
    }

    fun getMatchById(matchId: Long): MatchDto? {
        val request = matchesWebClient
            .get()
            .uri("/matches/${matchId}")
        val response = request
            .exchange()
            .block()
        if (response?.statusCode() != HttpStatus.OK) {
            throw Exception()
        }
        return response.bodyToMono(MatchDto::class.java).block()
    }

    fun getSeatDetails(seatId: Long): TicketSeatDto? {
        val request = matchesWebClient
            .get()
            .uri("/seats/${seatId}")
        val response = request
            .exchange()
            .block()
        if (response?.statusCode() != HttpStatus.OK) {
            throw Exception()
        }
        return response.bodyToMono(TicketSeatDto::class.java).block()
    }
}
