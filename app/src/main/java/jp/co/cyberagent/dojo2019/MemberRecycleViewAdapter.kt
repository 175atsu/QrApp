package jp.co.cyberagent.dojo2019

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


//質問　itemClickListenerとは
class MemberRecycleViewAdapter( private val itemList:List<MemberListModel>, private val listener: ListListener ) : RecyclerView.Adapter<MemberViewHolder>() {
    private var mRecyclerView : RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val rowView = layoutInflater.inflate(R.layout.list_view, parent, false)
            return MemberViewHolder(rowView)
        }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder?.let {
            it.nameID.text = itemList[position].nameID
            it.twitterID.text = itemList[position].twitterID
            it.githubID.text = itemList[position].githubID

            //質問
            it.itemView.setOnClickListener {
                listener.onClickRow(it, itemList[position])
            }

        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    //質問
    interface ListListener {
        fun onClickRow(tappedView: View, rowModel: MemberListModel)
    }
}