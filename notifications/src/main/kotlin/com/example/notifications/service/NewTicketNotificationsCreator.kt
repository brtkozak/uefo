package com.example.notifications.service

import com.example.notifications.dto.NewTicketNotificationBlueprint
import com.example.notifications.translations.TextTranslations
import java.text.MessageFormat

class NewTicketNotificationsCreator {
    companion object {
        const val MESSAGES_REL_PATH = "/messages_templates/new_ticket/messages/"
    }

    fun createNotificationMessage(newTicketNotificationBlueprint: NewTicketNotificationBlueprint): String {
        val lang = newTicketNotificationBlueprint.language
        val emailTemplate = getMessageTemplateFromFile(lang)

        return fillTemplateWithData(lang, emailTemplate, newTicketNotificationBlueprint)
    }

    fun getMessageTemplateFromFile(language: String): String {
        val path = MESSAGES_REL_PATH.plus("$language-new_ticket.html")
        println("getMessageTemplateFromFile, path: $path")
//        val resource = javaClass.classLoader?.getResource(path)
//            ?: ClassLoader.getSystemClassLoader().getResource(path)
//            ?: Thread.currentThread().contextClassLoader?.getResource(path)
        val resource = {}.javaClass.getResource(path)
        return resource!!.readText(Charsets.UTF_8)
    }

    private fun fillTemplateWithData(lang: String, template: String, data: NewTicketNotificationBlueprint): String {
        val personName = data.person.fullName
        val matchName = data.match.name
        val matchDate = data.match.date
        val seatsText = data.seats.joinToString("<br>") {
            TextTranslations.getSectorTranslation(lang) + it.sector + ", " +
                    TextTranslations.getSeatTranslation(lang) + it.seat
        }
        return MessageFormat.format(template, personName, matchName, matchDate, seatsText)
    }
}
