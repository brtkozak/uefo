package com.example.notifications.translations

object TextTranslations {
    fun getSeatTranslation(lang: String): String {
        if(lang == "pl") {
            return "miejsce: "
        }
        return "seat: "
    }

    fun getSectorTranslation(lang: String): String {
        if(lang == "pl") {
            return "Sektor: "
        }
        return "Sector: "
    }
}
