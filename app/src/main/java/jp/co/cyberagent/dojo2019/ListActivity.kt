package jp.co.cyberagent.dojo2019


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlin.concurrent.thread
import java.util.Random

class ListActivity  : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view)

        val buttonQr = findViewById<Button>(R.id.btnReturn)
        buttonQr.setOnClickListener {
            intent = Intent(this, MenuActivity::class.java)
            startActivity(intent);
        }

        //カスタムスキームURL取得　　解説求める
        val intent = intent
        val action = intent.action
        if (Intent.ACTION_VIEW == action) {
            val user = User()
            //URL取得
            var uri = intent.data
            //テキスト表示
            var messageView: TextView = findViewById(R.id.textView4)
            messageView.setText(uri.toString());

            //URLからパラメータの取得
            var values = uri.toString().split(Regex("[ =,&,\n]"))
                .filter { it.isNotEmpty() }
                .filterIndexed {index, _ -> index % 2 != 0}
            Log.d("TAG1", values.toString())

            user.uid = Random().nextInt()
            user.name = values[0]
            user.twitterID = values[1]
            user.githubID = values[2]
            Log.d("TAG2", user.uid.toString())
            Log.d("TAG3", user.name)
            Log.d("TAG4", user.twitterID)
            Log.d("TAG5", user.githubID)

            thread {
                AppDatabase.getInstance(this).userDao().insert(user)
            }

            var mydata  = Single.fromCallable { AppDatabase.getInstance(this).userDao().getAll() }
                //ioスレッドで実行する。
                .subscribeOn(Schedulers.io())
                //シングルからオブザバブルに変換
                .flatMapObservable { it.toObservable() }
                //変換する　文字列で来たものを数字にしたり
                .map { it}
                //ここまでに処理をmainThreadで実行 デフォルトの名前
                .observeOn(AndroidSchedulers.mainThread())
                //データの流れを監視、みる、流れてくるたびにプリントを実行する　/DISPOSEというクラスの処理だから出てきた。
                .subscribe({  Log.d("TAG6", it.uid.toString()+it.name)})
                }
//
//        val data = ArrayList()
//        data.add("国語")
//        data.add("社会")
//        data.add("算数")
//        data.add("理科")
//        data.add("生活")
//        data.add("音楽")
//        data.add("図画工作")
//        data.add("家庭")
//        data.add("体育")
//
//        // リスト項目とListViewを対応付けるArrayAdapterを用意する
//        ArrayAdapter() adapter = new ArrayAdapter<>(this, android.R.layout.list_view, data);
//
//        // ListViewにArrayAdapterを設定する
//        ListView listView = (ListView)findViewById(R.id.memberList);
//        listView.setAdapter(adapter);
   }

    override fun onStart() {
        super.onStart()

    }


}