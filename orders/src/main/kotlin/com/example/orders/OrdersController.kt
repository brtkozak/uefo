package com.example.orders

import com.example.orders.dto.AvailableSeatsDto
import com.example.orders.dto.NewOrderDto
import com.example.orders.dto.NewOrderFormDataDto
import com.example.orders.dto.OrderDetailsDto
import com.example.orders.service.OrderService
import com.example.orders.service.SeatsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/orders")
class OrdersController(
    val orderService: OrderService,
    val seatsService: SeatsService
) {

    @PostMapping("/order")
    fun createNewOrder(@RequestBody newOrderFormDataDto: NewOrderFormDataDto) : ResponseEntity<Any> {
        return try {
            val newOrder = orderService.createNewOrder(newOrderFormDataDto)
                ?: ResponseEntity.status(HttpStatus.BAD_REQUEST).build<NewOrderDto>()
            ResponseEntity.ok(newOrder)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping("/order/{orderId}")
    fun getOrderDetail(@PathVariable orderId: String): ResponseEntity<OrderDetailsDto> {
        return try {
            val details = orderService.getOrderDetails(orderId.toLong())
                ?: return ResponseEntity.notFound().build()
            ResponseEntity.ok(details)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/seat/{matchId}")
    fun getAvailableSeats(@PathVariable matchId: String): ResponseEntity<AvailableSeatsDto> {
        return try {
            val availableSeats = seatsService.getAvailableSeatsForMatch(matchId.toLong())
            ResponseEntity.ok(availableSeats)
        } catch (e: Exception) {
            ResponseEntity.notFound().build()
        }
    }
}
