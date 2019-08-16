package jp.co.cyberagent.dojo2019


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.room.Room
import com.takusemba.spotlight.OnSpotlightStateChangedListener
import com.takusemba.spotlight.Spotlight
import com.takusemba.spotlight.shape.RoundedRectangle
import com.takusemba.spotlight.target.SimpleTarget
import kotlin.concurrent.thread


class RegistrationActivity: AppCompatActivity() {

    lateinit var mGlobalLayoutListener: ViewTreeObserver.OnGlobalLayoutListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)
        val mParentLayout: ConstraintLayout =  findViewById(R.id.Constraint)


        //sam変換調べる
        mGlobalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
            //高さとかget
            val width = mParentLayout.getWidth()
            val height = mParentLayout.getHeight()
            //スポットライト
            spotLite()
            //何回も呼ばれるので1回しか呼ばれないように外す
            mParentLayout.viewTreeObserver.removeOnGlobalLayoutListener(mGlobalLayoutListener)
        }
        //mGlobalLayoutListenerをaddする、呼び出したい処理をセットする。
        mParentLayout.viewTreeObserver.addOnGlobalLayoutListener(mGlobalLayoutListener)

        //読み込み
        reedMydata()

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

    //スポットライトメソッド
    fun spotLite() {
        //fast target
        val target = findViewById<View>(R.id.name_text)
        val targetLocation = IntArray(2)
        target.getLocationInWindow(targetLocation)
        val targetX = targetLocation[0] + target.width / 2f  - 50f
        val targetY = targetLocation[1] + target.height / 2f

        //second target
        val target2 = findViewById<View>(R.id.twitter_text)
        val targetLocation2 = IntArray(2)
        target2.getLocationInWindow(targetLocation2)
        val targetX2 = targetLocation2[0] + target2.width / 2f - 50f
        val targetY2 = targetLocation2[1] + target2.height / 2f

        //third target
        val target3 = findViewById<View>(R.id.github_text)
        val targetLocation3 = IntArray(2)
        target3.getLocationInWindow(targetLocation3)
        val targetX3 = targetLocation3[0] + target3.width / 2f - 50f
        val targetY3 = targetLocation3[1] + target3.height / 2f

        // 円の大きさ
        val targetRadius = 200f
        // 四角いコーチマークの高さと幅を追加
        val targetWidth = 200f
        val targetHeight = 1000f

        // first target
        val firstTarget = SimpleTarget.Builder(this@RegistrationActivity)
            .setPoint(targetX, targetY)
            // CircleからRoundedRectangleに変更すると四角いコーチマーク表示できる
            .setShape(RoundedRectangle(targetWidth, targetHeight, 25f))
            .setTitle("名前入力")
            .setDescription("あなたの名前を入れてね")
            .setOverlayPoint(100f, targetY + targetRadius + 100f)
            .build()

        // second target
        val secondTarget = SimpleTarget.Builder(this@RegistrationActivity)
            .setPoint(targetX2, targetY2)
            // CircleからRoundedRectangleに変更すると四角いコーチマーク表示できる
            .setShape(RoundedRectangle(targetWidth, targetHeight, 25f))
            .setTitle("Twitter")
            .setDescription("@←これはいらないよ")
            .setOverlayPoint(100f, targetY2 + targetRadius + 100f)
            .build()

        // third target
        val thirdTarget = SimpleTarget.Builder(this@RegistrationActivity)
            .setPoint(targetX3, targetY3)
            // CircleからRoundedRectangleに変更すると四角いコーチマーク表示できる
            .setShape(RoundedRectangle(targetWidth, targetHeight, 25f))
            .setTitle("Github")
            .setDescription("アカウント名書くんだよ")
            .setOverlayPoint(100f, targetY3 + targetRadius + 100f)
            .build()

        // コーチマークを作成
        Spotlight.with(this@RegistrationActivity)
            // コーチマーク表示される時の背景の色
            .setOverlayColor(R.color.background)
            // 表示する時間
            .setDuration(1000L)
            // 表示するスピード
            .setAnimation(DecelerateInterpolator(1f))
            // 注目されたいところ（複数指定も可能）
            .setTargets(firstTarget, secondTarget, thirdTarget)
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