package com.example.banglishkeyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class BanglishImeService : InputMethodService() {
    private lateinit var root: LinearLayout
    private lateinit var suggestion: TextView
    private val rows = listOf(
        listOf("অ","আ","ই","ঈ","উ","ঊ","এ","ঐ","ও","ঔ"),
        listOf("ক","খ","গ","ঘ","ঙ","চ","ছ","জ","ঝ","ঞ"),
        listOf("ট","ঠ","ড","ঢ","ণ","ত","থ","দ","ধ","ন"),
        listOf("প","ফ","ব","ভ","ম","য","র","ল","শ","ষ"),
        listOf("স","হ","ড়","ঢ়","য়","ং","ঃ","ঁ","্","া"),
        listOf("ি","ী","ু","ূ","ে","ৈ","ো","ৌ","ৃ","SPACE")
    )
    private val typed = StringBuilder()

    override fun onCreateInputView(): View {
        root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(5, 5, 5, 5)
            setBackgroundColor(Color.rgb(225, 229, 235))
        }
        suggestion = TextView(this).apply {
            text = "বাংলা লিখুন — উপরে Banglish দেখাবে"
            textSize = 18f
            gravity = Gravity.CENTER
            setTextColor(Color.BLACK)
            setPadding(8, 14, 8, 14)
            setBackgroundColor(Color.WHITE)
            setOnClickListener {
                val output = BanglaRoman.convert(typed.toString())
                currentInputConnection?.commitText(output, 1)
                typed.clear()
                updateSuggestion()
            }
        }
        root.addView(suggestion, LinearLayout.LayoutParams(-1, dp(48)))
        rows.forEach { row ->
            val line = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }
            row.forEach { key ->
                val b = Button(this).apply {
                    text = if (key == "SPACE") "স্পেস" else key
                    textSize = 16f
                    isAllCaps = false
                    setPadding(0, 0, 0, 0)
                    setOnClickListener { press(key) }
                }
                line.addView(b, LinearLayout.LayoutParams(0, dp(46), 1f).apply {
                    setMargins(2, 2, 2, 2)
                })
            }
            root.addView(line)
        }
        val actions = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }
        listOf("⌫", "১২৩", "বাংলা", "↵").forEach { label ->
            val b = Button(this).apply {
                text = label; isAllCaps = false
                setOnClickListener {
                    when(label) {
                        "⌫" -> { if (typed.isNotEmpty()) typed.deleteCharAt(typed.length-1); updateSuggestion() }
                        "↵" -> { currentInputConnection?.commitText("\n", 1); typed.clear(); updateSuggestion() }
                        "বাংলা" -> { currentInputConnection?.commitText(typed.toString(), 1); typed.clear(); updateSuggestion() }
                        else -> currentInputConnection?.commitText(" ", 1)
                    }
                }
            }
            actions.addView(b, LinearLayout.LayoutParams(0, dp(44), 1f))
        }
        root.addView(actions)
        return root
    }

    private fun press(key: String) {
        if (key == "SPACE") {
            // Space commits the Banglish form of the current Bengali word.
            currentInputConnection?.commitText(BanglaRoman.convert(typed.toString()) + " ", 1)
            typed.clear()
        } else {
            typed.append(key)
        }
        updateSuggestion()
    }

    private fun updateSuggestion() {
        val roman = BanglaRoman.convert(typed.toString())
        suggestion.text = if (typed.isEmpty()) "বাংলা লিখুন — উপরে Banglish দেখাবে"
            else "$roman   (এখানে চাপলে বসবে)"
    }
    private fun dp(v: Int) = (v * resources.displayMetrics.density).toInt()
}
