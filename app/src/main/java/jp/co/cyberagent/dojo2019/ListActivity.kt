package jp.co.cyberagent.dojo2019



import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import kotlinx.android.synthetic.main.list_view.*
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
        reedCustom()
        //recyclerViewのセッティング
        recyclerViewInitialSetting()
    }

    //カスタムURLの読み取り
    fun reedCustom() {

        //
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
                .filterIndexed { index, _ -> index % 2 != 0 }
            Log.d("TAG1", values.toString())

            user.uid = Random().nextInt()
            user.name = values[0]
            user.twitter = values[1]
            user.github = values[2]
            Log.d("TAG2", user.uid.toString())
            Log.d("TAG3", user.name)
            Log.d("TAG4", user.twitter)
            Log.d("TAG5", user.github)

            thread {
                AppDatabase.getInstance(this).userDao().insert(user)
            }

        }

    }


    //アダプターなどなどの設定
    fun recyclerViewInitialSetting() {
        val rv = recyclerView
        val adapter = MemberRecycleViewAdapter(fetchAllUserData(), object : MemberRecycleViewAdapter.ListListener {
            override fun onClickRow(tappedView: View, userListModel: MemberListModel) {
                this.onClickRow(tappedView, userListModel)
            }
        })
        //リストのtrueコンテンツの大きさがデータによって変わらないならを渡す。これをRecyclerViewにいつもすることで、パフォーマンスが良くなる。
        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter
    }

    //DBに保存したデータの取得
    fun fetchAllUserData(): List<MemberListModel> {
        //データリスト
        val dataList = mutableListOf<MemberListModel>()
        // 永続データベースを作成
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database.db").build()
        //DBからユーザーデータの取得
        val dao = db.userDao()

        dao.getAll().observe(this, Observer<List<User>> { users ->
            if (users != null) {
                Log.d("TAG3",users.toString())
                for (i in users) {
                    val data: MemberListModel = MemberListModel().also {
                        it.nameID = i.name
                        it.twitterID = i.twitter
                        it.githubID = i.github
                    }
                    dataList.add(data)
                }
                //更新
                recyclerView.adapter?.notifyDataSetChanged()
            }
        })
        return dataList
    }

    //セルのタップ
    fun onClickRow(tappedView: View, userListModel: MemberListModel) {
        //toDetailViewへ画面遷移
        toUserDetailActivity(userListModel)
    }

    //UserDetailActivityへの遷移とアカウントの値渡し
    fun toUserDetailActivity(userAccount: MemberListModel) {
        val intent = Intent (getActivity(), MemberDetailActivity::class.java)
        intent.putExtra("NAME", userAccount.nameID)
        intent.putExtra("TWITTER", userAccount.twitterID)
        intent.putExtra("GITHUB", userAccount.githubID)
        getActivity()?.startActivity(intent)
    }
}