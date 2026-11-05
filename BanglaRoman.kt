package com.example.banglishkeyboard

object BanglaRoman {
    // Lightweight starter transliteration. It is an approximation, not a full Avro engine.
    private val map = mapOf(
        "অ" to "o", "আ" to "a", "ই" to "i", "ঈ" to "ee", "উ" to "u", "ঊ" to "oo",
        "এ" to "e", "ঐ" to "oi", "ও" to "o", "ঔ" to "ou",
        "া" to "a", "ি" to "i", "ী" to "i", "ু" to "u", "ূ" to "u",
        "ে" to "e", "ৈ" to "oi", "ো" to "o", "ৌ" to "ou", "ৃ" to "ri",
        "ক" to "k", "খ" to "kh", "গ" to "g", "ঘ" to "gh", "ঙ" to "ng",
        "চ" to "ch", "ছ" to "chh", "জ" to "j", "ঝ" to "jh", "ঞ" to "ny",
        "ট" to "t", "ঠ" to "th", "ড" to "d", "ঢ" to "dh", "ণ" to "n",
        "ত" to "t", "থ" to "th", "দ" to "d", "ধ" to "dh", "ন" to "n",
        "প" to "p", "ফ" to "ph", "ব" to "b", "ভ" to "bh", "ম" to "m",
        "য" to "j", "র" to "r", "ল" to "l", "শ" to "sh", "ষ" to "sh",
        "স" to "s", "হ" to "h", "ড়" to "r", "ঢ়" to "rh", "য়" to "y",
        "ং" to "ng", "ঃ" to "h", "ঁ" to "n", "্" to ""
    )
    fun convert(input: String): String {
        val out = StringBuilder()
        input.forEach { ch -> out.append(map[ch.toString()] ?: ch.toString()) }
        return out.toString()
    }
}
