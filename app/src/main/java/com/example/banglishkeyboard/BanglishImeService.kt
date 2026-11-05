package com.example.banglishkeyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class BanglishImeService : InputMethodService() {

    private lateinit var suggestion: TextView
    private val typed = StringBuilder()

    private val rows = listOf(
        listOf("অ","আ","ই","ঈ","উ","এ","ও"),
        listOf("ক","খ","গ","ঘ","চ","ছ","জ"),
        listOf("ট","ঠ","ড","ঢ","ত","থ","দ"),
        listOf("ন","প","ফ","ব","ভ","ম","র"),
        listOf("ল","শ","স","হ","য","া","ি"),
        listOf("ী","ু","ে","ো","্","ং","SPACE")
    )

    override fun onCreateInputView(): View {
        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(4, 4, 4, 4)
        root.setBackgroundColor(Color.LTGRAY)

        suggestion = TextView(this)
        suggestion.text = "বাংলা লিখুন — Banglish দেখুন"
        suggestion.textSize = 18f
        suggestion.gravity = Gravity.CENTER
        suggestion.setBackgroundColor(Color.WHITE)
        suggestion.setTextColor(Color.BLACK)

        suggestion.setOnClickListener {
            if (typed.isNotEmpty()) {
                currentInputConnection?.commitText(
                    BanglaRoman.convert(typed.toString()), 1
                )
                typed.clear()
                updateSuggestion()
            }
        }

        root.addView(suggestion, LinearLayout.LayoutParams(-1, 55))

        rows.forEach { row ->
            val line = LinearLayout(this)
            line.orientation = LinearLayout.HORIZONTAL

            row.forEach { key ->
                val button = Button(this)
                button.text = if (key == "SPACE") "স্পেস" else key
                button.isAllCaps = false
                button.setOnClickListener { press(key) }

                line.addView(button, LinearLayout.LayoutParams(0, 52, 1f))
            }
            root.addView(line)
        }

        val back = Button(this)
        back.text = "⌫"
        back.setOnClickListener {
            if (typed.isNotEmpty()) {
                typed.deleteCharAt(typed.length - 1)
                updateSuggestion()
            }
        }
        root.addView(back)

        return root
    }

    private fun press(key: String) {
        if (key == "SPACE") {
            currentInputConnection?.commitText(
                BanglaRoman.convert(typed.toString()) + " ", 1
            )
            typed.clear()
        } else {
            typed.append(key)
        }
        updateSuggestion()
    }

    private fun updateSuggestion() {
        suggestion.text = if (typed.isEmpty())
            "বাংলা লিখুন — Banglish দেখুন"
        else BanglaRoman.convert(typed.toString())
    }
}
