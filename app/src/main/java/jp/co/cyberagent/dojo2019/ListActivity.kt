package jp.co.cyberagent.dojo2019


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.list_view.*
import kotlin.concurrent.thread
import java.util.Random

class ListActivity  : AppCompatActivity() {

//    private val mSampleData = itemList()
//    private lateinit var adapter : RecyclerView.Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_view)

        // データを設定
//        adapter = RecyclerAdapter(this, mSampleData)
//        listView2.adapter = adapter

        recyclerViewInitialSetting()

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

   }


    //アダプターなどなどの設定
    fun recyclerViewInitialSetting() {
        val recyclerView = recyclerView
        val adapter = MemberRecycleViewAdapter(fetchAllUserData(), object : MemberRecycleViewAdapter.ListListener {
            override fun onClickRow(tappedView: View, userListModel: MemberListModel) {
                this.onClickRow(tappedView, userListModel)
            }
        })
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    //セルのタップ
//    fun onClickRow(tappedView: View, userListModel: MemberListModel) {
//        //toDetailViewへ画面遷移
//        toUserDetailActivity(userListModel)
//    }

    //DBに保存したデータの取得
    fun fetchAllUserData(): List<MemberListModel> {
        //データリスト
        val dataList = mutableListOf<MemberListModel>()
        //DBからユーザーデータの取得
        var memberData  = Single.fromCallable { AppDatabase.getInstance(this).userDao().getAll() } //データベースから取ってくる
            //ioスレッドで実行する。
            .subscribeOn(Schedulers.io())
            //シングルからオブザバブルに変換
            .flatMapObservable { it.toObservable() }
            //変換する　文字列で来たものを数字にしたり
            .map { it}
            //ここまでに処理をmainThreadで実行 デフォルトの名前
            .observeOn(AndroidSchedulers.mainThread())
            //データの流れを監視、みる、流れてくるたびにプリントを実行する　/DISPOSEというクラスの処理だから出てきた。
            .subscribe({Log.d("TAG12", it.name.toString())
                var data : MemberListModel = MemberListModel().also {
                    it.nameID = it.nameID
                    it.twitterID = it.twitterID
                    it.githubID = it.githubID
                    }
                dataList.add(data)})

        return dataList
    }



    override fun onStart() {
        super.onStart()

    }


}