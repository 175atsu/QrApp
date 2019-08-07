package jp.co.cyberagent.dojo2019


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun nextView(view: View) {
        //インテントの作成
        var intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent);
    }
}
