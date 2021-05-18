package com.example.orders.service

import com.example.orders.Order
import com.example.orders.OrderRepository
import com.example.orders.Ticket
import com.example.orders.TicketRepository
import com.example.orders.dto.NewOrderDto
import com.example.orders.dto.OrderDetailsDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(
    val newOrderService: NewOrderService,
    val ticketRepository: TicketRepository,
    val orderRepository: OrderRepository
) {
    fun createNewOrder(newOrderDto: NewOrderDto): Order? {
        var orderEntity: Order? = null
        try {
            orderEntity = saveOrder(newOrderDto)
            newOrderService.requestOrderPayment()
        } catch (e: Exception) {
            rollbackOrder(orderEntity)
        }

        return orderEntity
    }

    fun saveOrder(newOrderDto: NewOrderDto): Order {
        val orderEntity = Order(newOrderDto.userId, newOrderDto.matchId)
        orderRepository.save(orderEntity)
        val tickets = newOrderDto.tickets.map {
            val ticketEntity = Ticket(it.atendeeId, it.seatId, orderEntity)
            ticketRepository.save(ticketEntity)
        }
        orderEntity.tickets = tickets.toMutableList()
        return orderEntity
    }

    fun rollbackOrder(orderToRollback: Order?) {
        val orderId = orderToRollback?.id
        if(orderId != null) {
            orderRepository.deleteById(orderId)
        }
    }

    fun getOrderDetails(orderId: Long): OrderDetailsDto? {
        val savedEntity = orderRepository.findByIdOrNull(orderId) ?: return null
        val savedEntityId = savedEntity.id ?: return null
        return OrderDetailsDto(savedEntityId, savedEntity.userId, savedEntity.matchId, savedEntity.tickets)
    }
}
