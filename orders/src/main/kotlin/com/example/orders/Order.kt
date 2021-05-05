package com.example.orders

import javax.persistence.*

@Entity
data class Order (
    var userId: Long,
    var matchId: Long,
) {
    @Id
    @GeneratedValue
    var id: Long? = null

    @OneToMany(
        mappedBy = "order",
        cascade = [CascadeType.ALL],
        orphanRemoval = true
    )
    lateinit var tickets: MutableList<Ticket>
}
