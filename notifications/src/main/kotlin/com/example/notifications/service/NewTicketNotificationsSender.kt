package com.example.notifications.service

import com.example.notifications.dto.NewTicketNotificationBlueprint
import java.io.File

class NewTicketNotificationsSender {
    companion object {
        val newTicketNotificationsCreator = NewTicketNotificationsCreator()
        val notificationEmailSender = NotificationEmailSender()

        val SUBJECTS_REL_PATH = listOf("messages_templates", "new_ticket", "subjects")
            .joinToString(File.separator)
    }

    fun sendMessage(requestBodyObj: NewTicketNotificationBlueprint) {
        val emailMessage = newTicketNotificationsCreator.createNotificationMessage(requestBodyObj)
        val emailSubject = getSubject(requestBodyObj.language)
        notificationEmailSender.sendEmailNotification(emailMessage, emailSubject, requestBodyObj.emailAddress)
    }

    private fun getSubject(language: String): String {
        val path = SUBJECTS_REL_PATH.plus(File.separatorChar).plus("$language-subject.txt")
        return Thread.currentThread().getContextClassLoader().getResource(path).readText(Charsets.UTF_8)
    }
}
