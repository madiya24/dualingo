package com.example.duolingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonStart: Button = findViewById(R.id.buttonStart)
        val buttonExit: Button = findViewById(R.id.buttonExit)
        buttonStart.setOnClickListener{
            startActivity(Intent(this,MainScreen::class.java))
        }
        buttonExit.setOnClickListener{
            finish()
        }
    }




}






