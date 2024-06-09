package com.example.duolingapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.NumberFormatException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class MainScreen : AppCompatActivity() {
    private  lateinit var editdate: EditText
    private  lateinit var mredit:EditText
    private  lateinit var afedit: EditText
    private lateinit var addbtn: Button
    private lateinit var buttonclr: Button
    private lateinit var dtlbutton: Button


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)
        editdate = findViewById(R.id.editdate)
        mredit = findViewById(R.id.mredit)
        afedit = findViewById(R.id.afedit)
        addbtn = findViewById(R.id.addbtn)
        buttonclr = findViewById(R.id.buttonclr)
        dtlbutton= findViewById(R.id.dtlbutton)

        dtlbutton.setOnClickListener {
            startActivity(Intent(this,example::class.java))
        }
        addbtn.setOnClickListener {
            addData()
        }
        buttonclr.setOnClickListener {
            clear()
        }
    }
    private fun addData() {
        val date = editdate.text.toString()
        val morning = mredit.text.toString()
        val afternoon = afedit.text.toString()
        val errors = mutableListOf<String>()

        if (DataManager.getNumberOfDays() <= 7) {
            try {
                val dateFormat = SimpleDateFormat("d MMM yyy", Locale.US)
                dateFormat.parse(date)
            } catch (e: ParseException) {
                errors.add("Invalid date format.Use format: 8 june 2024")
            }

            if (morning.isEmpty()) {
                errors.add("enter a minutes first")
            }
            val minutes = morning.toIntOrNull()
            if (minutes == null) {
                errors.add("Morning time mus be numeric")
            } else if (minutes < 0 || minutes > 360) {
                errors.add("Your morning time must be between o and 360 minutes")
            }
            if (afternoon.isEmpty()) {
                errors.add("enter a minutes first ")
            }
            val minutes2 = afternoon.toIntOrNull()
            if (minutes2 == null) {
                errors.add("your afternoon time must be numeric")
            } else if (minutes2 < 0 || minutes2 > 360) {
                errors.add("Your afternoon time must be between 0 and 360 minutes")
            }
            DataManager.addData(date, morning.toInt(), afternoon.toInt())
            editdate.text.clear()
            mredit.text.clear()
            afedit.text.clear()
        } else {
            Toast.makeText(this, "you have already saved 7 days ", Toast.LENGTH_LONG).show()
        }
    }
    private fun clear(){
        editdate.text.clear()
        mredit.text.clear()
        afedit.text.clear()
    }
}
class DataManager(){
    companion object{
        var editdate = mutableListOf<String>()
        var mredit = mutableListOf<Int>()
        var afedit = mutableListOf<Int>()

        fun addData(date:String, morning:Int, afternoon:Int) {
            editdate.add(date)
            mredit.add(morning)
            afedit.add(afternoon)
        }
        fun averageScreen(): Double{
            val totalDays = editdate.size
            val totalScreen = mredit.sum() + afedit.sum()
            if (totalDays>0){
                totalScreen.toDouble() / totalDays
            }
            return averageScreen()
        }
        fun getNumberOfDays(): Int {
            return editdate.size
        }
    }
}
