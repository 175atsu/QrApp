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

        //ボタンの名前の設定
        val button = findViewById<Button>(R.id.btnSave)
        //ボタン押された時に処理
        button.setOnClickListener {
            var NameText: EditText = findViewById(R.id.name_text)
            var TwitterText: EditText = findViewById(R.id.twitter_text)
            var GithubText: EditText = findViewById(R.id.github_text)
            Log.d("TAG",NameText.text.toString())

            //ユーザークラスのインスタンス化
            val user = User()
            //登録するデータ、項目名ではなく、User.ktでつけた変数名を使う
            user.uid = 1
            user.name = NameText.text.toString()
            user.twitterID = TwitterText.text.toString()
            user.githubID = GithubText.text.toString()
            //保存する処理・他のスレッドでやる（メインだとできない）
            thread {
                AppDatabase.getInstance(this).userDao().insert(user)
            }
            //画面移動
            var intent2 = Intent(this, MenuActivity::class.java)
            startActivity(intent2);
        }
    }
}