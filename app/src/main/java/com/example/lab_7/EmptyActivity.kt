package com.example.lab_7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class EmptyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empty)

        val fragment = DetailsFragment().apply {
            arguments = intent.extras
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.detailContainer, fragment)
            .commit()
    }
}