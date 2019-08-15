package jp.co.cyberagent.dojo2019

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.getTransitionName
import androidx.core.app.ActivityOptionsCompat
import kotlinx.android.synthetic.main.menu.*
import kotlinx.android.synthetic.main.qr_view.*


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        //getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        val binding = setContentView(R.layout.menu);

        //qr表示画面へ
        val buttonQr = findViewById<View>(R.id.abc)
        buttonQr.setOnClickListener {
            val intent = Intent(this, QrActivity::class.java)
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, abc,
                abc.getTransitionName()
            )
            startActivity(intent, compat.toBundle())
            //startActivity(intent);
        }
        //編集画面へ
        val buttonEdit = findViewById<Button>(R.id.btnEdit)
        buttonEdit.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent);
        }
        //一覧画面へ
        val buttonList = findViewById<View>(R.id.btnList)
        buttonList.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            val compat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, abc,
                abc.getTransitionName()
            )
            startActivity(intent);
        }
        //読み込み画面へ
        val buttonCamera = findViewById<Button>(R.id.btnCamera)
        buttonCamera.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent);
        }

    }
}