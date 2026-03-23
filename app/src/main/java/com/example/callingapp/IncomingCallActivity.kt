package com.example.callingapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class IncomingCallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.fade_in, R.anim.popup_in)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incoming_call)

        val numberText = findViewById<TextView>(R.id.incomingNumber)
        val ringingText = findViewById<TextView>(R.id.ringingText)
        val acceptBtn = findViewById<ImageButton>(R.id.acceptBtn)
        val rejectBtn = findViewById<ImageButton>(R.id.rejectBtn)

        val number = intent.getStringExtra("number")

        //  CONTACT MAPPING
        val name = ContactManager.getName(number)
        numberText.text = name

        CallState.state = "Ringing"
        ringingText.text = "Ringing..."

        acceptBtn.setOnClickListener {

            it.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).withEndAction {
                it.animate().scaleX(1f).scaleY(1f).duration = 100

                CallState.state = "Active"

                val intent = Intent(this, ActiveCallActivity::class.java)
                intent.putExtra("number", number)
                startActivity(intent)

                finish()
            }
        }

        rejectBtn.setOnClickListener {

            it.animate().rotation(20f).setDuration(100).withEndAction {
                it.animate().rotation(0f).duration = 100

                CallState.state = "Ended"

                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

                finish()
            }
        }
    }
}