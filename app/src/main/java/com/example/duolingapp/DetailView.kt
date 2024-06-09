package com.example.duolingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.system.exitProcess


class DetailView : AppCompatActivity() {

    private lateinit var detailViewtextView:TextView
    private lateinit var detailtextView: TextView
    private lateinit var titletextView: TextView
    private lateinit var averagetextView: TextView
    private lateinit var mainbutton:Button
    private lateinit var exitbutton: Button

    private val screenMinuteData = mutableListOf<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detaied_view)

        detailtextView = findViewById(R.id.detailtextView)
        detailViewtextView = findViewById(R.id.detailViewtextView)
        titletextView = findViewById(R.id.titletextView)
        averagetextView = findViewById(R.id.averagetextView)
        mainbutton = findViewById(R.id.mainbutton)
        exitbutton = findViewById(R.id.exitbutton)

        intent.getIntArrayExtra("screenMinuteData")?.let { screenMinuteData.addAll(it) }
        displayDetailView()
        calculateAverage()

        mainbutton.setOnClickListener {
            finish()
        }

        exitbutton.setOnClickListener{
            android.os.Process.killProcess(android.os.Process.myPid())
            exitProcess(1)
        }
    }
    private fun  displayDetailView() {
        val detailviewText = StringBuilder()
        for((index,screenMinute) in screenMinuteData .withIndex()){
            detailviewText.append("Day ${index + 1}: $screenMinute minute\n")
        }
    }
    private  fun calculateAverage(){
        val totalScreenMinute = screenMinuteData.sum()
        val averageScreenMinute = if (screenMinuteData.isEmpty()){
            totalScreenMinute/ screenMinuteData.size
        }else {
            0
        }
        averagetextView.text = "Average screen minute : $averageScreenMinute minutes"
    }
    private fun <E> MutableList<E>.addAll(ints: IntArray)  {

    }
}











