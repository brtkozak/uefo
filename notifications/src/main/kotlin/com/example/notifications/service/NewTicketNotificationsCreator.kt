package com.example.notifications.service

import com.example.notifications.dto.NewTicketNotificationBlueprint
import java.io.File
import java.text.MessageFormat

class NewTicketNotificationsCreator {
    companion object {
        val MESSAGES_REL_PATH = File.separator.plus(listOf("messages_templates", "new_ticket", "messages")
            .joinToString(File.separator))
    }

    fun createNotificationMessage(newTicketNotificationBlueprint: NewTicketNotificationBlueprint): String {
        val lang = newTicketNotificationBlueprint.language
        val emailTemplate = getMessageTemplateFromFile(lang)

        return fillTemplateWithData(emailTemplate, newTicketNotificationBlueprint)
    }

    private fun getMessageTemplateFromFile(language: String): String {
        val path = MESSAGES_REL_PATH.plus(File.separatorChar).plus("$language-new_ticket.html")
        return Thread.currentThread().getContextClassLoader().getResource(path).readText(Charsets.UTF_8)
    }

    private fun fillTemplateWithData(template: String, data: NewTicketNotificationBlueprint): String {
        return MessageFormat.format(template, data.person.fullName)
    }
}
