package jp.co.cyberagent.dojo2019

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MemberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    val nameID: TextView = itemView.findViewById(R.id.name)
    val twitterID: TextView = itemView.findViewById(R.id.twitter)
    val githubID: TextView = itemView.findViewById(R.id.github)
}