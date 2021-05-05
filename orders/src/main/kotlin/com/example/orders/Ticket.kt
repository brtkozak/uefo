package com.example.orders

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Ticket (
    var atendeeId: Long,
    var seatId: Long,

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_fk")
    var order: Order
) {
    @Id
    @GeneratedValue
    var id: Long? = null
}
