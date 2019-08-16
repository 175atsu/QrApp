package jp.co.cyberagent.dojo2019

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.member_detail_view.*
import kotlinx.android.synthetic.main.row.*
import kotlin.concurrent.thread

class MemberDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.member_detail_view)
        //val buttonTwitter = findViewById<TextView>(R.id.user_twitter)
        //val buttonGithub = findViewById<TextView>(R.id.user_github)


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
        val userName = Uri.decode(intent.getStringExtra("NAME"))
        val twitter = intent.getStringExtra("TWITTER")
        val gitHub = intent.getStringExtra("GITHUB")
        val userNameTextView = findViewById<TextView>(R.id.user_name).apply {
            text = userName
        }
        val gitHubAccountTextView = findViewById<ImageView>(R.id.user_github).apply {
            //text = gitHub
        }
        val twitterTextView = findViewById<ImageView>(R.id.user_twitter).apply {
            //text = twitter
        }
    }

    //アラート
    // ダイアログを作成して表示
    fun alert() = AlertDialog.Builder(this).apply {
        setTitle("削除します")
        setMessage("削除してもよろしいですか")
        setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
            // OKをタップしたときの処理
            // 永続データベースを作成
            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db").build()
            //DBからユーザーデータの取得
            val dao = db.userDao()
//            thread {
//                val users = dao.findByName(name = NAME,)
//                if (users.isNotEmpty()) {
//                    NameText.setText(users.get(0).name)
//                    TwitterText.setText(users.get(0).twitter)
//                    GithubText.setText(users.get(0).github)
//                } else {
//                    NameText.text = null
//                    TwitterText.text = null
//                    GithubText.text = null
//                }
//            }
        })
        setNegativeButton("Cancel", null)
        show()
    }

    fun web() {

        val buttonTwitter = findViewById<ImageView>(R.id.user_twitter)
        buttonTwitter.setOnClickListener{
            val twitterWeb = Intent(applicationContext, WebViewActivity::class.java)
            val twitter = intent.getStringExtra("TWITTER")
            //val twitter = findViewById<ImageView>(R.id.user_twitter).text
            twitterWeb.putExtra("TWITTER","https://twitter.com/$twitter")
            startActivity(twitterWeb)
        }
        val buttonGithub = findViewById<ImageView>(R.id.user_github)
        buttonGithub.setOnClickListener {
            val githubWeb = Intent(applicationContext, WebViewActivity::class.java)
            //val gitHub = findViewById<TextView>(R.id.user_github).text
            val gitHub = intent.getStringExtra("GITHUB")
            githubWeb.putExtra("GITHUB","https://github.com/$gitHub")
            startActivity(githubWeb)
        }
    }

}