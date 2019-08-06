package jp.co.cyberagent.dojo2019

import android.content.Intent

class ProfileData {

    var intent = Intent(this, ProfileData::class.java)

    var name = arrayOf(1, 2, 3)
    var twitter = arrayOf(1, 2, 3)
    var github = arrayOf(1, 2, 3)

    val my_name = intent.getStringExtra("NAME_DATA")
    val teitter_name = intent.getStringExtra("TWITTER_DATA")
    val github_name = intent.getStringExtra("GIT_DATA")

}