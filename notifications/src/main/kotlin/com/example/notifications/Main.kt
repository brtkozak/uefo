package com.example.notifications

import com.beust.klaxon.Klaxon
import com.example.notifications.dto.NewTicketNotificationBlueprint
import com.example.notifications.service.NewTicketNotificationsCreator
import com.example.notifications.service.NewTicketNotificationsSender
import io.javalin.Javalin

fun main(args: Array<String>) {
    val newTicketNotificationsSender = NewTicketNotificationsSender()

    //TEST
    val test = NewTicketNotificationsCreator().getMessageTemplateFromFile("pl")
    println("Test OK, ${test.take(10)}...")

    val jsonParser = Klaxon()
    val app = Javalin.create().start(8087)

    app.post("/newTicket") { ctx ->
        val requestBodyJson = ctx.body()
        val requestBodyObj = jsonParser.parse<NewTicketNotificationBlueprint>(requestBodyJson)
        if(requestBodyObj == null) {
            ctx.status(401)
        } else {
            try {
                println("newTicketNotificationsSender.sendMessage")
                newTicketNotificationsSender.sendMessage(requestBodyObj)
                ctx.status(200)
            } catch (e: Error) {
                println(e.message)
                ctx.res.sendError(401, e.message)
            }
        }
    }
}
