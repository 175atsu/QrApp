package jp.co.cyberagent.dojo2019

import android.content.Intent

//package com.example.natsuki.qr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
//import android.support.v7.app.AppCompatActivity
import android.widget.Button


class QrActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_view)

        val button = findViewById<Button>(R.id.btnReturnQr)
        button.setOnClickListener {
            intent = Intent(this, QrActivity::class.java)
            startActivity(intent);
        }

    }

    override fun onResume() {
        super.onResume()

    }
}