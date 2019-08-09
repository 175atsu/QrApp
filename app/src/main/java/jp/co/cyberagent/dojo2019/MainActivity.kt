package jp.co.cyberagent.dojo2019


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.util.Log



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //追加
        val intent = intent
        val action = intent.action
        if (Intent.ACTION_VIEW == action) {
            val uri = intent.data


            val messageView = findViewById(R.id.textView3)
            messageView
            Log.d("TAG3",uri.toString())
        }
    }

    fun nextView(view: View) {
        //インテントの作成
        var intent = Intent(this, RegistrationActivity::class.java)
        startActivity(intent);
    }
}
