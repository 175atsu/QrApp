package jp.co.cyberagent.dojo2019

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MemberDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.member_detail_view)

    }

    //各種アカウントの表示
    fun showAccount() {
        val userName = intent.getStringExtra("NAME")
        val twitter = intent.getStringExtra("TWITTER")
        val gitHub = intent.getStringExtra("GITHUB")
        val userNameTextView = findViewById<TextView>(R.id.user_name).apply {
            text = userName
        }
        val gitHubAccountTextView = findViewById<TextView>(R.id.user_twitter).apply {
            text = gitHub
        }
        val twitterTextView = findViewById<TextView>(R.id.user_github).apply {
            text = twitter
        }
    }

}