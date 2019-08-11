package jp.co.cyberagent.dojo2019

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


public class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }
    var nameID: TextView? = null
    var twitterID: TextView? = null
    var githubID: TextView? = null
    fun MemberViewHolder(itemView: View) {
        super.itemView
        nameID = itemView.findViewById(R.id.name)
        twitterID = itemView.findViewById(R.id.twitter)
        githubID = itemView.findViewById(R.id.github)
    }
}