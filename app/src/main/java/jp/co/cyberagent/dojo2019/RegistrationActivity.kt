package jp.co.cyberagent.dojo2019

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.*
import kotlin.concurrent.thread


class RegistrationActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        val button = findViewById<Button>(R.id.btnSave)
        button.setOnClickListener {
            //            var NameText: EditText = findViewById(R.id.name_text)
//            Log.d("TAG", NameText.text.toString())
//            var TwitterText: EditText = findViewById(R.id.twitter_text)
//            var GithubText: EditText = findViewById(R.id.github_text)
//            var intent = Intent(this, ProfileData::class.java)
//            intent.putExtra("NAME_DATA", NameText.text.toString())
//            intent.putExtra("TWITTER_DATA", TwitterText.text.toString())
//            intent.putExtra("GITHUB_DATA", GithubText.text.toString())
            val user = User()
            user.uid = "1"
            user.firstName = "Yuya"
            user.lastName = "Matsuo"
            // データを保存
            thread {
                AppDatabase.getInstance(this).userDao().insert(user)
            }

            var intent2 = Intent(this, MenuActivity::class.java)
            startActivity(intent2);
        }
    }
}