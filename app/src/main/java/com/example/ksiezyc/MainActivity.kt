package com.example.ksiezyc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ksiezyc.Algorithm.Companion.getDateF
import com.example.ksiezyc.Algorithm.Companion.getDateN
import com.example.ksiezyc.Algorithm.Companion.getNumber
import com.example.ksiezyc.Algorithm.Companion.getPercent

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sharedPreference:SharedPreferences=SharedPreferences(this)

        textView5.text = "Poprzedni nów: " + getDateN(sharedPreference.getValueInt("algo")) + "r."
        textView1.text = "Następna pełnia: " + getDateF(sharedPreference.getValueInt("algo")) + "r."
        textView3.text = "Dzisiaj: " + getPercent(sharedPreference.getValueInt("algo"))

        imageView.setImageResource(getNumber(sharedPreference.getValueString("part").toString(), sharedPreference.getValueInt("algo")))

        button.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }
        button4.setOnClickListener {
            val intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        }
    }
}



