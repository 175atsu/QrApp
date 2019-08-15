package jp.co.cyberagent.dojo2019

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.room.Room
import kotlinx.android.synthetic.main.menu.*


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        val binding = setContentView(R.layout.menu);

        //qr表示画面へ
        val buttonQr = findViewById<View>(R.id.btnQr)
        buttonQr.setOnClickListener {
            val intent = Intent(this, QrActivity::class.java)
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, btnQr,
                btnQr.getTransitionName()
            )
            startActivity(intent, compat.toBundle())
        }
        //編集画面へ
        val buttonEdit = findViewById<View>(R.id.btnEdit)
        buttonEdit.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, btnEdit,
                btnEdit.getTransitionName()
            )
            startActivity(intent, compat.toBundle())
        }
        //一覧画面へ
        val buttonList = findViewById<View>(R.id.btnList)
        buttonList.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, btnList,
                btnList.getTransitionName()
            )
            startActivity(intent, compat.toBundle())
        }
        //読み込み画面へ
        val buttonCamera = findViewById<View>(R.id.btnCamera)
        buttonCamera.setOnClickListener {
            alert()
        }

    }

    fun alert() = AlertDialog.Builder(this).apply {
        setTitle("開発中")
        setMessage("元の画面に戻ります")
        setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
            // OKをタップしたときの処理
        })
        setNegativeButton("はい", null)
        show()
    }
}