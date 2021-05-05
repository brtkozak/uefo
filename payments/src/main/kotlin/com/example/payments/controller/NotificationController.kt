package com.example.payments.controller

import com.example.payments.entity.Notification
import com.example.payments.repository.NotificationRepository
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/payments/notification")
class NotificationController(
    private val notificationRepository: NotificationRepository
) {

    @GetMapping("/notification/{id}")
    fun getNotification(@PathVariable id : Long): Optional<Notification> {
        return notificationRepository.findById(id)
    }

    @DeleteMapping("/notification/{id}")
    fun deleteNotification(@PathVariable id: Long) {
        notificationRepository.deleteById(id)
    }

    @PostMapping("/notification")
    fun receiveNotification(notification: Notification) : String {
        notificationRepository.save(notification)
        return "Notification has been received"
    }

}