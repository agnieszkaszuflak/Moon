package com.example.ksiezyc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.first.*

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first)
        val sharedPreference:SharedPreferences=SharedPreferences(this)
        s.setOnClickListener {
            sharedPreference.saveS("part", "s")
        }
        n.setOnClickListener {
            sharedPreference.saveS("part", "n")
        }
        radioButton.setOnClickListener {
            sharedPreference.save("algo", 1)
        }
        radioButton2.setOnClickListener {
            sharedPreference.save("algo", 2)
        }
        radioButton3.setOnClickListener {
            sharedPreference.save("algo", 3)
        }
        radioButton4.setOnClickListener {
            sharedPreference.save("algo", 4)
        }
        button2.setOnClickListener {
            finishActivity(1);
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}
