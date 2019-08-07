package jp.co.cyberagent.dojo2019

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import java.util.*


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
//            var intent2 = Intent(this, MenuActivity::class.java)
//            startActivity(intent2);
            // データモデルを作成
            var db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build()
            val user = User()
            user.uid = Random().nextInt()
            user.firstName = "Yuya"
            user.lastName = "Matsuo"
            // データを保存
            db.userDao().insert(user)
        }



    }

}