package com.example.notifications.service

import com.example.notifications.dto.NewTicketNotificationBlueprint

class NewTicketNotificationsSender {
    companion object {
        val newTicketNotificationsCreator = NewTicketNotificationsCreator()
        val notificationEmailSender = NotificationEmailSender()

        val supportedLanguages = listOf("pl", "en")

        const val SUBJECTS_REL_PATH = "/messages_templates/new_ticket/subjects/"
    }

    fun sendMessage(requestBodyObj: NewTicketNotificationBlueprint) {
        if(!supportedLanguages.contains(requestBodyObj.language)) {
            throw Error("Language not supported!")
        }

        val emailMessage = newTicketNotificationsCreator.createNotificationMessage(requestBodyObj)
        val emailSubject = getSubject(requestBodyObj.language)
        notificationEmailSender.sendEmailNotification(emailMessage, emailSubject, requestBodyObj.emailAddress)
    }

    private fun getSubject(language: String): String {
        val path = SUBJECTS_REL_PATH.plus("$language-subject.txt")
        println("getSubject, path: $path")
//        val resource = Thread.currentThread().contextClassLoader?.getResource(path)
//            ?: javaClass.classLoader?.getResource(path)
//            ?: ClassLoader.getSystemClassLoader().getResource(path)
        val resource = {}.javaClass.getResource(path)
        return resource.readText(Charsets.UTF_8)
    }
}
