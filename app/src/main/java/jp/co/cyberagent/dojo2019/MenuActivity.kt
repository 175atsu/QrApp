package jp.co.cyberagent.dojo2019

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        val buttonQr = findViewById<Button>(R.id.btnQr)
        buttonQr.setOnClickListener {
            intent = Intent(this, QrActivity::class.java)
            startActivity(intent);
        }

        val buttonEdit = findViewById<Button>(R.id.btnEdit)
        buttonEdit.setOnClickListener {
            intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent);
        }

    }
}