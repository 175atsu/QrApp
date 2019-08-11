package jp.co.cyberagent.dojo2019

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//１つの行に入れる情報の項目がEntity
@Entity
class User {
    //外から呼び出す際に使う項目
    @PrimaryKey
    var uid: Int = 0
    //ColumnInfoがあると、列（項目）の名前を指定できる。他で使うときはfirstName。項目名はfirst_name
    @ColumnInfo(name = "name")
    var name: String? = null

    var twitterID: String? = null

    var githubID: String? = null
}