package com.example.banglishkeyboard

object BanglaRoman {

    private val letters = mapOf(
        'অ' to "o", 'আ' to "a", 'ই' to "i",
        'ঈ' to "i", 'উ' to "u", 'ঊ' to "u",
        'এ' to "e", 'ঐ' to "oi", 'ও' to "o",
        'ঔ' to "ou",

        'ক' to "k", 'খ' to "kh", 'গ' to "g",
        'ঘ' to "gh", 'ঙ' to "ng",
        'চ' to "ch", 'ছ' to "chh", 'জ' to "j",
        'ঝ' to "jh", 'ঞ' to "ny",
        'ট' to "T", 'ঠ' to "Th", 'ড' to "D",
        'ঢ' to "Dh", 'ণ' to "N",
        'ত' to "t", 'থ' to "th", 'দ' to "d",
        'ধ' to "dh", 'ন' to "n",
        'প' to "p", 'ফ' to "ph", 'ব' to "b",
        'ভ' to "bh", 'ম' to "m",
        'য' to "j", 'র' to "r", 'ল' to "l",
        'শ' to "sh", 'ষ' to "sh", 'স' to "s",
        'হ' to "h", 'ড়' to "r", 'ঢ়' to "rh",
        'য়' to "y", 'ং' to "ng", 'ঃ' to "h",
        'ঁ' to "n",

        'া' to "a", 'ি' to "i", 'ী' to "i",
        'ু' to "u", 'ূ' to "u", 'ে' to "e",
        'ৈ' to "oi", 'ো' to "o", 'ৌ' to "ou",
        'ৃ' to "ri", '্' to ""
    )

    fun convert(text: String): String {
        return text.map { letters[it] ?: it.toString() }.joinToString("")
    }
}
