package com.kishorramani.noteapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val i = Intent(this@SplashActivity, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}