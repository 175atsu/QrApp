package jp.co.cyberagent.dojo2019


import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class WebViewActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)

        webShow()
    }

    fun webShow(){
        val twitter = intent.getStringExtra("TWITTER")
        val github = intent.getStringExtra("GITHUB")
        val webView: WebView = findViewById(R.id.webView1)

        if (twitter != null){
            webView.loadUrl(twitter)
        } else {
            webView.loadUrl(github)
        }
    }
}