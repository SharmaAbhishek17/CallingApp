package com.example.callingapp

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActiveCallActivity : AppCompatActivity() {

    private var seconds = 0
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_call)

        val numberText = findViewById<TextView>(R.id.activeNumber)
        val timerText = findViewById<TextView>(R.id.timerText)
        val endBtn = findViewById<ImageButton>(R.id.endCallBtn) // ✅ changed
        val muteBtn = findViewById<ImageButton>(R.id.muteBtn)   // ✅ changed
        val speakerBtn = findViewById<ImageButton>(R.id.speakerBtn) // ✅ changed

        val number = intent.getStringExtra("number")

// Contact Mapping
        val name = ContactManager.getName(number)
        numberText.text = name

        when (CallState.state) {

            "Active" -> {
                startTimer(timerText)
                muteBtn.visibility = View.VISIBLE
                speakerBtn.visibility = View.VISIBLE
                endBtn.visibility = View.VISIBLE
            }

            "Ringing" -> {
                timerText.text = "Ringing..."
                muteBtn.visibility = View.GONE
                speakerBtn.visibility = View.GONE
                endBtn.visibility = View.GONE
            }

            "Ended" -> {
                timerText.text = "Call Ended"
                muteBtn.visibility = View.GONE
                speakerBtn.visibility = View.GONE
                endBtn.visibility = View.GONE
            }
        }

        muteBtn.setOnClickListener {
            it.isSelected = !it.isSelected
        }

        speakerBtn.setOnClickListener {
            it.isSelected = !it.isSelected
        }

        endBtn.setOnClickListener {
            CallState.state = "Ended"
            stopTimer()

            timerText.text = "Call Ended"

            muteBtn.visibility = View.GONE
            speakerBtn.visibility = View.GONE
            endBtn.visibility = View.GONE
        }
    }

    private fun startTimer(timerText: TextView) {
        handler = Handler(Looper.getMainLooper())

        runnable = object : Runnable {
            override fun run() {
                seconds++
                timerText.text = String.format("%02d:%02d", seconds / 60, seconds % 60)
                handler.postDelayed(this, 1000)
            }
        }

        handler.post(runnable)
    }

    private fun stopTimer() {
        if (::handler.isInitialized) {
            handler.removeCallbacks(runnable)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopTimer()
    }
}