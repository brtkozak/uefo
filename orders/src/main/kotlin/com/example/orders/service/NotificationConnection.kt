package com.example.orders.service

import com.example.orders.Order
import com.example.orders.dto.Match
import com.example.orders.dto.NewTicketNotificationBlueprint
import com.example.orders.dto.Person
import com.example.orders.dto.Seat
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient

@Service
class NotificationConnection(
    val seatsService: SeatsService
) {
    companion object {
        const val NOTIFICATION_SERVER_URL = "http://notifications:8087"
    }

    private val notificationsWebClient: WebClient = WebClient.builder()
        .baseUrl(NOTIFICATION_SERVER_URL)
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build()

    fun sendNewTicketNotification(order: Order) {
        println("sendNewTicketNotification - start")

        val userDetails = getUserDetails(order.userId)
        val matchDetails = getMatchDetails(order)
        val ticketsDetails = getTicketsDetails(order)

        val notificationBlueprint = NewTicketNotificationBlueprint(
            emailAddress = userDetails.first,
            language = userDetails.second,
            person = userDetails.third,
            match = matchDetails,
            seats = ticketsDetails
        )

        println("notificationBlueprint")

        val request = notificationsWebClient
            .post()
            .uri("/newTicket")
            .body(BodyInserters.fromValue(notificationBlueprint))
        val response = request
            .exchange()
            .block()
        if (response?.statusCode() != HttpStatus.OK) {
            println("sendNewTicketNotification, response: ${response?.statusCode()}")
            throw Exception("Payment HTTP response != 400, but ${response?.statusCode()}")
        }
    }

    private fun getUserDetails(userId: Long): Triple<String, String, Person> {
        if(userId == 1L) {
            return Triple("adam.nowak@xmail.com", "pl", Person("Adam Nowak"))
        }
        if(userId == 2L) {
            return Triple("peter.smith@xmail.com", "en", Person("Peter Smith"))
        }
        println("getUserDetails - User not found")
        throw Error("User not found")
    }

    private fun getMatchDetails(order: Order): Match {
        val match = seatsService.getMatchById(order.matchId)
            ?: throw Error("Cannot find match with id: ${order.matchId}")
        return Match(
            name = match.name,
            date = match.beginDate.toLocaleString()
        )
    }

    private fun getTicketsDetails(order: Order): List<Seat> {
        return order.tickets
            .mapNotNull { seatsService.getSeatDetails(it.seatId) }
            .map { Seat(
                sector = it.sector,
                seat = it.seatNumber
            ) }
    }
}
