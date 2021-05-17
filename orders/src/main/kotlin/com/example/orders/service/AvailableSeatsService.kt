package com.example.orders.service

import com.example.orders.OrderRepository
import com.example.orders.dto.AvailableSeatsDto
import com.example.orders.dto.MatchDto
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
        const val MATCHES_SERVER_URL = "localhost:8083"
    }

    private val matchesWebClient: WebClient = WebClient.builder()
        .baseUrl(MATCHES_SERVER_URL)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    fun getAvailableSeatsForMatch(matchId: Long): AvailableSeatsDto? {
        val match = getMatchById(matchId)
        val ordersForMatch = orderRepository.getAllByMatchId(matchId)
        //TODO filter out free seats; cannot do it because matches does not return seats
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
}
