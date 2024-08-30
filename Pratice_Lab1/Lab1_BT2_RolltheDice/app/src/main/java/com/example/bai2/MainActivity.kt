package com.example.bai2

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bai2.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val handler = Handler(Looper.getMainLooper())
    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()

    }

    private fun startNumberSequence() {
        isRunning = true
        binding.btnChange.isEnabled = false

        val startTime = System.currentTimeMillis()
        val runnable = object : Runnable {
            override fun run() {
                if (System.currentTimeMillis() - startTime < 1000) {
                    val number = ((System.currentTimeMillis() / 100) % 6 + 1).toInt()
                    binding.btnChange.text = number.toString()
                    handler.postDelayed(this, 100)
                } else {
                    showRandomNumber()
                }
            }
        }
        handler.post(runnable)
    }

    private fun showRandomNumber() {
        val randomNumber = Random.nextInt(1, 7)
        binding.btnChange.text = randomNumber.toString()
        binding.btnChange.isEnabled = true
        isRunning = false
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    private fun setListeners() {
        binding.btnChange.setOnClickListener {
           startNumberSequence()
        }
    }
}