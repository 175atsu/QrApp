package jp.co.cyberagent.dojo2019

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import kotlinx.android.synthetic.main.camera_view.*

class CameraActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera_view)

        //new IntentIntegrator(CameraActivity.this).initiateScan();

        //カメラ許可関数
        //checkPermissions()
        //QRカメラ
        //initQRCamera()
    }

    val qr_view = findViewById<View>(R.id.qr_view)

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        when(requestCode) {
//            REQUEST_CAMERA_PERMISSION -> { initQRCamera() }
//        }
//    }


    //カメラ許可関数
//    private fun checkPermissions() {
//        // already we got permission.
//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//            qr_view.resume()
//        }
//
//        if (ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.CAMERA)) {
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), 999)
//        }
//    }

    companion object {
        const val REQUEST_CAMERA_PERMISSION:Int = 1
    }

    //QR関数
//    @SuppressLint("WrongConstant")
//    private fun initQRCamera() {
//
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return
//
//        val isReadPermissionGranted = (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
//        val isWritePermissionGranted = (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
//        val isCameraPermissionGranted = (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
//
//        if (isReadPermissionGranted && isWritePermissionGranted && isCameraPermissionGranted) {
//            openQRCamera() // ← カメラ起動
//        } else {
//            requestPermissions(arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CAMERA_PERMISSION)
//        }
//    }

//    private fun openQRCamera() {
//        qr_view.decodeContinuous(object : BarcodeCallback {
//            override fun barcodeResult(result: BarcodeResult?) {
//                if (result != null) {
//                    onPause()
//                    Log.d("QRCode", "$result")
//                }
//            }
//
//            override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) { }
//        })
//    }
}