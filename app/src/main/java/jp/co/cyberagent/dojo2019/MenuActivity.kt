package jp.co.cyberagent.dojo2019

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        //qr表示画面へ
        val buttonQr = findViewById<Button>(R.id.btnQr)
        buttonQr.setOnClickListener {
            intent = Intent(this, QrActivity::class.java)
            startActivity(intent);
        }
        //編集画面へ
        val buttonEdit = findViewById<Button>(R.id.btnEdit)
        buttonEdit.setOnClickListener {
            intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent);
        }
        //一覧画面へ
        val buttonList = findViewById<Button>(R.id.btnList)
        buttonList.setOnClickListener {
            intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent);
        }
        //読み込み画面へ
        val buttonCamera = findViewById<Button>(R.id.btnCamera)
        buttonCamera.setOnClickListener {
            intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent);
        }

    }
}