package jp.co.cyberagent.dojo2019

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)

        val button = findViewById<Button>(R.id.QrButton)
        button.setOnClickListener {
            intent = Intent(this, QrActivity::class.java)
            startActivity(intent);
        }

    }
}