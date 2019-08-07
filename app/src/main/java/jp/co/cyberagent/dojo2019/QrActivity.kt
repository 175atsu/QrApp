package jp.co.cyberagent.dojo2019

import android.content.Intent

//package com.example.natsuki.qr

import android.os.Bundle
import android.util.AndroidRuntimeException
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder


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
        val profile = ProfileData(intent)
        var data = profile.my_name
        val size = 1000

        try {
            val barcodeEncoder = BarcodeEncoder()
            //QRコードをBitmapで作成
            val bitmap = barcodeEncoder.encodeBitmap(data, BarcodeFormat.QR_CODE, size, size)

            //作成したQRコードを画面上に配置
            val imageViewQrCode = findViewById<View>(R.id.imageView) as ImageView
            imageViewQrCode.setImageBitmap(bitmap)

        } catch (e: WriterException) {
            throw AndroidRuntimeException("Barcode Error.", e)
        }

    }
}