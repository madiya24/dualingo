package com.example.duolingapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.system.exitProcess


class example : AppCompatActivity() {
    private lateinit var avtext: TextView
    private lateinit var dtltext: TextView
    private lateinit var mainbtn: Button
    private lateinit var exitbtn : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example)
        avtext = findViewById(R.id.avtext)
        dtltext = findViewById(R.id.dtltext)
        mainbtn = findViewById(R.id.mainbtn)
        exitbtn = findViewById(R.id.exitbtn)

        mainbtn.setOnClickListener {
            startActivity(Intent(this,MainScreen::class.java))
        }
        exitbtn.setOnClickListener {
            moveTaskToBack(true)
            android.os.Process.killProcess( android.os.Process.myPid())
            exitProcess(1)
        }
        val detailedInfo = generateDetailInfo()
        dtltext.text = detailedInfo
        val averageTime = DataManager.averageScreen()
        avtext.text = "Average Screen: ${averageTime} minutes"
    }
    private fun generateDetailInfo(): String {
        val builder = StringBuilder()
        for (i in 0 until DataManager.editdate.size){
            val date = DataManager.editdate[i]
            val morning = DataManager.mredit[i]
            val afternoon =DataManager.afedit[i]
            val formattedDayInfo = """""
                    $date\n 
                    Morning: $morning\n | Afternoon: $afternoon\n
            """.trimIndent()
            builder.append(formattedDayInfo)
        }
        return builder.toString()
    }
}