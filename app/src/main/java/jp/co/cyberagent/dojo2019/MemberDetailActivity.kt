package jp.co.cyberagent.dojo2019

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.member_detail_view.*
import kotlin.concurrent.thread

class MemberDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.member_detail_view)
        val buttonTwitter = findViewById<TextView>(R.id.user_twitter)
        val buttonGithub = findViewById<TextView>(R.id.user_github)


        web()
        showAccount()

        val buttonReturn = findViewById<Button>(R.id.btnReturn)
        buttonReturn.setOnClickListener {
            intent = Intent(this, ListActivity::class.java)
            startActivity(intent);
        }

        val buttonDetail = findViewById<Button>(R.id.btnDetail)
        buttonDetail.setOnClickListener {
            alert()
        }
    }

    //各種アカウントの表示
    fun showAccount() {
        val userName = intent.getStringExtra("NAME")
        val twitter = intent.getStringExtra("TWITTER")
        val gitHub = intent.getStringExtra("GITHUB")
        val userNameTextView = findViewById<TextView>(R.id.user_name).apply {
            text = userName
        }
        val gitHubAccountTextView = findViewById<TextView>(R.id.user_github).apply {
            text = gitHub
        }
        val twitterTextView = findViewById<TextView>(R.id.user_twitter).apply {
            text = twitter
        }
    }

    //アラート
    // ダイアログを作成して表示
    fun alert() = AlertDialog.Builder(this).apply {
        setTitle("削除します")
        setMessage("削除してもよろしいですか")
        setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
            // OKをタップしたときの処理
            val user = User()
            user.uid = 1
            user.name = user_name.text.toString()
            //user.twitter = user_twitter.text.toString()
            user.github = user_github.text.toString()
//            thread {
//                AppDatabase.getInstance(this).userDao().delete(user)
//            }
        })
        setNegativeButton("Cancel", null)
        show()
    }

    fun web() {

        val buttonTwitter = findViewById<TextView>(R.id.user_twitter)
        buttonTwitter.setOnClickListener{
            val twitterWeb = Intent(applicationContext, WebViewActivity::class.java)
            val twitter = findViewById<TextView>(R.id.user_twitter).text
            twitterWeb.putExtra("TWITTER","https://twitter.com/$twitter")
            startActivity(twitterWeb)
        }
        val buttonGithub = findViewById<TextView>(R.id.user_github)
        buttonGithub.setOnClickListener {
            val githubWeb = Intent(applicationContext, WebViewActivity::class.java)
            val gitHub = findViewById<TextView>(R.id.user_github).text
            githubWeb.putExtra("GITHUB","https://github.com/$gitHub")
            startActivity(githubWeb)
        }
    }

}