package com.example.ksiezyc

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.second.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            setContentView(R.layout.second)
            val sharedPreference:SharedPreferences=SharedPreferences(this)
            textView2.text = Algorithm.getYear().toString()
            text1.text = Algorithm.getFull(sharedPreference.getValueInt("algo"))

            plus.setOnClickListener {
                Algorithm.plusYear()
                textView2.text = Algorithm.getYear().toString()
                text1.text = Algorithm.getFull(sharedPreference.getValueInt("algo"))
            }
            minus.setOnClickListener {
                Algorithm.minusYear()
                textView2.text = Algorithm.getYear().toString()
                text1.text = Algorithm.getFull(sharedPreference.getValueInt("algo"))

            }
            button3.setOnClickListener{
                val intent = Intent(this, Main3Activity::class.java)
                startActivity(intent)
            }

    }

}
