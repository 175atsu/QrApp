package jp.co.cyberagent.dojo2019

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import androidx.room.RoomDatabase
import com.takusemba.spotlight.OnSpotlightStateChangedListener
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.shape.RoundedRectangle
import com.takusemba.spotlight.target.SimpleTarget
import kotlinx.android.synthetic.main.member_detail_view.*
import java.util.*
import kotlin.concurrent.thread


class RegistrationActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        //読み込み
        reedMydata()
        spotLite()

        //ボタンの名前の設定
        val button = findViewById<Button>(R.id.btnSave)
        //ボタン押された時に処理
        button.setOnClickListener {
            saveData()
            //画面移動
            var intent2 = Intent(this, MenuActivity::class.java)
            startActivity(intent2);
        }
    }

    //読み込みメソッド
    fun reedMydata() {
        var NameText: EditText = findViewById(R.id.name_text)
        var TwitterText: EditText = findViewById(R.id.twitter_text)
        var GithubText: EditText = findViewById(R.id.github_text)
        // 永続データベースを作成
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db").build()
        //DBからユーザーデータの取得
        val dao = db.userDao()
        thread {
            val users = dao.loadAllaByIds(1)
            if (users.isNotEmpty()) {
                NameText.setText(users.get(0).name)
                TwitterText.setText(users.get(0).twitter)
                GithubText.setText(users.get(0).github)
            } else {
                NameText.text = null
                TwitterText.text = null
                GithubText.text = null
            }
        }
    }

    //保存メソッド
    fun saveData() {
        var NameText: EditText = findViewById(R.id.name_text)
        var TwitterText: EditText = findViewById(R.id.twitter_text)
        var GithubText: EditText = findViewById(R.id.github_text)

        Log.d("TAG",NameText.text.toString())

        //ユーザークラスのインスタンス化
        val user = User()
        //登録するデータ、項目名ではなく、User.ktでつけた変数名を使う
        user.uid = 1
        user.name = NameText.text.toString()
        user.twitter = TwitterText.text.toString()
        user.github = GithubText.text.toString()
        //保存する処理・他のスレッドでやる（メインだとできない）
        thread {
            AppDatabase.getInstance(this).userDao().insert(user)
        }
    }

    fun spotLite() {
        val target = findViewById<Button>(R.id.btnSave)
        val targetLocation = IntArray(2)
        target.getLocationInWindow(targetLocation)
        val targetX = targetLocation[0] + target.width / 2f
        val targetY = targetLocation[1] + target.height / 2f
        // 円の大きさ
        val targetRadius = 200f
        // 四角いコーチマークの高さと幅を追加
        val targetWidth = 400f
        val targetHeight = 400f

        // first target
        val firstTarget = SimpleTarget.Builder(this@RegistrationActivity)
            .setPoint(targetX, targetY)
            // CircleからRoundedRectangleに変更すると四角いコーチマーク表示できる
            .setShape(RoundedRectangle(targetWidth, targetHeight, 25f))
            .setTitle("タイトル")
            .setDescription("メッセージここで表示")
            .setOverlayPoint(100f, targetY + targetRadius + 100f)
            .build()

        // コーチマークを作成
        Spotlight.with(this@RegistrationActivity)
            // コーチマーク表示される時の背景の色
            .setOverlayColor(R.color.background)
            // 表示する時間
            .setDuration(1000L)
            // 表示するスピード
            .setAnimation(DecelerateInterpolator(2f))
            // 注目されたいところ（複数指定も可能）
            .setTargets(firstTarget)
            // 注目されたいところ以外をタップする時に閉じられるかどうか
            .setClosedOnTouchedOutside(true)
            // コーチマーク表示される時になんかする
            .setOnSpotlightStateListener(object : OnSpotlightStateChangedListener {
                override fun onStarted() {
                    Toast.makeText(this@RegistrationActivity, "spotlight is started", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onEnded() {
                    Toast.makeText(this@RegistrationActivity, "spotlight is ended", Toast.LENGTH_SHORT).show()
                }
            })
            .start()
    }
}