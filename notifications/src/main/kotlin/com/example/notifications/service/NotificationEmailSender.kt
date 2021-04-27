package com.example.notifications.service

import com.example.notifications.tech.EmailSenderConfiguration
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeBodyPart
import javax.mail.internet.MimeMessage
import javax.mail.internet.MimeMultipart

class NotificationEmailSender {
    companion object {
        val serviceConfiguration: EmailSenderConfiguration = EmailSenderConfiguration(
            host= "smtp.mailtrap.io",
            port = "2525",
            username = "3bf541ae4a5f08",
            password = "90bde81fe3b521",
            addressFrom = "uefo_robot@uefo.com",
            messageContentType = "text/html; charset=UTF-8"
        )
    }

    fun sendEmailNotification(messageContent: String, subject: String, emailAddress: String) {
        val emailMessage = MimeMessage(createSession())
        emailMessage.setFrom(InternetAddress(serviceConfiguration.addressFrom))
        emailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailAddress))
        emailMessage.subject = subject

        val mimeBodyPart = MimeBodyPart()
        mimeBodyPart.setContent(messageContent, serviceConfiguration.messageContentType)

        val multipart = MimeMultipart()
        multipart.addBodyPart(mimeBodyPart)
        emailMessage.setContent(multipart)
        Transport.send(emailMessage)
    }

    private fun createSession(): Session {
        val sessionProperties = Properties()
        sessionProperties["mail.smtp.auth"] = true
        sessionProperties["mail.smtp.starttls.enable"] = true
        sessionProperties["mail.smtp.host"] = serviceConfiguration.host
        sessionProperties["mail.smtp.port"] = serviceConfiguration.port
        sessionProperties["mail.smtp.user"] = serviceConfiguration.username
        sessionProperties["mail.smtp.password"] = serviceConfiguration.password

        return Session.getInstance(sessionProperties, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(serviceConfiguration.username, serviceConfiguration.password)
            }
        })
    }
}
