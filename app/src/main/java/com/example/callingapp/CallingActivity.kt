package com.example.callingapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class CallingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calling)

        val numberText = findViewById<TextView>(R.id.callNumber)
        val endBtn = findViewById<ImageButton>(R.id.endCallBtn) // ✅ changed




        val number = intent.getStringExtra("number")

//  Contact Mapping
        val name = ContactManager.getName(number)
        numberText.text = name
        CallState.state = "Calling"

        endBtn.setOnClickListener {
            CallState.state = "Ended"

            val intent = Intent(this, ActiveCallActivity::class.java)
            intent.putExtra("number", number)
            startActivity(intent)

            finish()
        }

        Handler(Looper.getMainLooper()).postDelayed({

            CallState.state = "Active"

            val intent = Intent(this, ActiveCallActivity::class.java)
            intent.putExtra("number", number)
            startActivity(intent)

        }, 3000)

        numberText.alpha = 0f
        numberText.animate().alpha(1f).setDuration(1000)
    }
}