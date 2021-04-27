package com.example.notifications

import com.beust.klaxon.Klaxon
import com.example.notifications.dto.NewTicketNotificationBlueprint
import com.example.notifications.service.NewTicketNotificationsSender
import io.javalin.Javalin

fun main(args: Array<String>) {
    val app = Javalin.create().start(7000)

    val jsonParser = Klaxon()
    val newTicketNotificationsSender = NewTicketNotificationsSender()

    app.post("/newTicket") { ctx ->
        val requestBodyJson = ctx.body()
        val requestBodyObj = jsonParser.parse<NewTicketNotificationBlueprint>(requestBodyJson)
        if(requestBodyObj == null) {
            ctx.status(401)
        } else {
            newTicketNotificationsSender.sendMessage(requestBodyObj)
            ctx.status(200)
        }
    }
}
