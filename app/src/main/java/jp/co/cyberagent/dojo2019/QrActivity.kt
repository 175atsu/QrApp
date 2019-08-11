package jp.co.cyberagent.dojo2019

import android.content.Intent

//package com.example.natsuki.qr

import android.os.Bundle
import android.util.AndroidRuntimeException
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers


class QrActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.qr_view)

        val button = findViewById<Button>(R.id.btnReturn)
        button.setOnClickListener {
            intent = Intent(this, QrActivity::class.java)
            startActivity(intent);
        }

        val buttonQr = findViewById<Button>(R.id.btnReturn)
        buttonQr.setOnClickListener {
            intent = Intent(this, MenuActivity::class.java)
            startActivity(intent);
        }

    }

    override fun onResume() {
        super.onResume()

        val url = "ca-tech://dojo/share"

        var mydata  = Single.fromCallable { AppDatabase.getInstance(this).userDao().loadAllaByIds(1) } //データベースから取ってくる
            //ioスレッドで実行する。
            .subscribeOn(Schedulers.io())
            //シングルからオブザバブルに変換
            .flatMapObservable { it.toObservable() }
            //変換する　文字列で来たものを数字にしたり
            .map { it}
            //ここまでに処理をmainThreadで実行 デフォルトの名前
            .observeOn(mainThread())
            //データの流れを監視、みる、流れてくるたびにプリントを実行する　/DISPOSEというクラスの処理だから出てきた。
            .subscribe({
                Log.d("TAG12", it.uid.toString())
                val size = 1000
                var myParameters = "?iam=${it.name}"+"&tw=${it.twitterID}"+"&gh=${it.githubID}"
                try {
                    val barcodeEncoder = BarcodeEncoder()
                    //QRコードをBitmapで作成
                    val bitmap = barcodeEncoder.encodeBitmap(url+myParameters, BarcodeFormat.QR_CODE, size, size)
                    //作成したQRコードを画面上に配置
                    val imageViewQrCode = findViewById<View>(R.id.imageView) as ImageView
                    imageViewQrCode.setImageBitmap(bitmap)
                } catch (e: WriterException) {
                    throw AndroidRuntimeException("Barcode Error.", e)
                }
            })
    }
}