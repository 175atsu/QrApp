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
        intent = Intent(this, RegistrationActivity::class.java)
        //データをセット
        //EditText editText = (EditText)this.findViewById(R.id.editText);
        //intent.putExtra("sendText",editText.getText().toString());
        //遷移先の画面を起動
        startActivity(intent);
    }
}
