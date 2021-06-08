package com.example.orders.service

import com.example.orders.Order
import com.example.orders.OrderRepository
import com.example.orders.Ticket
import com.example.orders.TicketRepository
import com.example.orders.dto.NewOrderDto
import com.example.orders.dto.NewOrderFormDataDto
import com.example.orders.dto.OrderDetailsDto
import com.example.orders.dto.payment.CreatePaymentRequest
import com.example.orders.dto.payment.CreatePaymentResponse
import com.example.orders.dto.payment.TicketForPaymentDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(
    val newOrderService: NewOrderService,
    val availableSeatsService: AvailableSeatsService,
    val ticketRepository: TicketRepository,
    val orderRepository: OrderRepository,
) {
    fun createNewOrder(newOrderFormDataDto: NewOrderFormDataDto): NewOrderDto? {
        val restrictedAtendees = newOrderService.checkAtendeesRestrictions(newOrderFormDataDto.tickets)
        if(restrictedAtendees.isNotEmpty()) {
            throw IllegalArgumentException("Users with restrictions: " + restrictedAtendees.joinToString())
        }

        var orderEntityId: Long? = null
        return try {
            val savedOrder = saveOrder(newOrderFormDataDto)
            println("savedOrder")
            orderEntityId = savedOrder.id
            val paymentReq = preparePayment(savedOrder) ?: throw Exception("Cannot realize payment.")
            println("paymentReq")
            NewOrderDto(savedOrder, paymentReq)
        } catch (e: Exception) {
            rollbackOrder(orderEntityId)
            throw e
        }
    }

    fun saveOrder(newOrderFormDataDto: NewOrderFormDataDto): Order {
        val orderEntity = Order(newOrderFormDataDto.userId, newOrderFormDataDto.matchId)
        orderRepository.save(orderEntity)
        val tickets = newOrderFormDataDto.tickets.map {
            val ticketEntity = Ticket(it.atendee.pesel, it.seatId, orderEntity)
            ticketRepository.save(ticketEntity)
        }
        orderEntity.tickets = tickets.toMutableList()
        return orderEntity
    }

    fun rollbackOrder(orderIdToRollback: Long?) {
        if(orderIdToRollback != null) {
            orderRepository.deleteById(orderIdToRollback)
        }
    }

    fun getOrderDetails(orderId: Long): OrderDetailsDto? {
        val savedEntity = orderRepository.findByIdOrNull(orderId) ?: return null
        val savedEntityId = savedEntity.id ?: return null
        return OrderDetailsDto(savedEntityId, savedEntity.userId, savedEntity.matchId, savedEntity.tickets)
    }

    fun preparePayment(savedOrder: Order): CreatePaymentResponse? {
        //TODO temp
        val currency = "PLN"

        val orderName = "Order_${savedOrder.id}"
        val tickets = getTicketsForPayment(savedOrder)
        val infoForPayment = CreatePaymentRequest(orderName, savedOrder.id!!, savedOrder.userId, currency, tickets)
        return try {
            newOrderService.requestOrderPayment(infoForPayment)
        } catch (e: Exception) {
            e.printStackTrace()
            println("Cannot realize payment. Rollback order.")
            null
        }
    }

    fun getTicketsForPayment(savedOrder: Order): List<TicketForPaymentDto> {
        return savedOrder.tickets
            .mapNotNull { availableSeatsService.getSeatDetails(it.seatId) }
            .map {
                val ticketName = "ticket_" + it.id
                TicketForPaymentDto(ticketName, it.seatPrice, 1, it.id)
            }
    }
}
