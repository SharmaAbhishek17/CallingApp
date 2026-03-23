package com.example.callingapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {

    companion object {
        var isIncomingShown = false   // 🔥 control flag
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameDisplay = findViewById<TextView>(R.id.nameDisplay)
        val display = findViewById<TextView>(R.id.numberDisplay)
        val callBtn = findViewById<ImageButton>(R.id.callBtn)
        val grid = findViewById<GridLayout>(R.id.rootView)
        val backBtn = findViewById<ImageButton>(R.id.backBtn)

        //  Dial pad


        for (i in 0 until grid.childCount) {
            val view = grid.getChildAt(i)
            if (view is Button) {
                view.setOnClickListener {
                    it.animate().scaleX(0.9f).scaleY(0.9f).setDuration(100).withEndAction {
                        it.animate().scaleX(1f).scaleY(1f).duration = 100

                        // number add
                        display.append(view.text.toString())

                        // 🔥 CONTACT MAPPING
                        val number = display.text.toString()
                        val name = ContactManager.getName(number)

                        if (name != number) {
                            nameDisplay.text = name
                        } else {
                            nameDisplay.text = ""
                        }
                    }
                }
            }
        }
        //  Call button
        callBtn.setOnClickListener {
            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).duration = 100
               


                val number = display.text.toString()

                if (number.isEmpty()) {
                    Toast.makeText(this, "Enter number", Toast.LENGTH_SHORT).show()
                } else {
                    CallState.state = "Calling"

                    val intent = Intent(this, CallingActivity::class.java)
                    intent.putExtra("number", number)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
                }
            }
        }

        // ⬅ Backspace
        backBtn.setOnClickListener {
            val text = display.text.toString()
            if (text.isNotEmpty()) {
                display.text = text.dropLast(1)

                // 🔥 update name
                val number = display.text.toString()
                val name = ContactManager.getName(number)

                if (name != number) {
                    nameDisplay.text = name
                } else {
                    nameDisplay.text = ""
                }
            }
        }

        //  Long press clear
        backBtn.setOnLongClickListener {
            display.text = ""
            nameDisplay.text = ""
            true
        }

        // Incoming call
        if (!isIncomingShown) {

            isIncomingShown = true

            Handler(Looper.getMainLooper()).postDelayed({

                CallState.state = "Ringing"

                val intent = Intent(this, IncomingCallActivity::class.java)
                intent.putExtra("number", "9999999999")
                startActivity(intent)

            }, 5000)
        }
    }
}