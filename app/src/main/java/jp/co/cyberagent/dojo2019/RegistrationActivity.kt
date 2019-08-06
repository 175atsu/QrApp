package jp.co.cyberagent.dojo2019

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class RegistrationActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

    }


    fun save(view: View) {
        var NameText: EditText = findViewById(R.id.name_text)
        var TwitterText: EditText = findViewById(R.id.twitter_text)
        var GithubText: EditText = findViewById(R.id.github_text)
        var intent = Intent(this, ProfileData::class.java)
        intent.putExtra("NAME_DATA", NameText.text.toString())
        intent.putExtra("TWITTER_DATA", TwitterText.text.toString())
        intent.putExtra("GITHUB_DATA", GithubText.text.toString())


        val button = findViewById<Button>(R.id.btnSave)
        button.setOnClickListener {
            var intent2 = Intent(this, MenuActivity::class.java)
            startActivity(intent2);
        }
    }
}