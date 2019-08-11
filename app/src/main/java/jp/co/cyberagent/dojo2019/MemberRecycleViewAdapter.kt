package jp.co.cyberagent.dojo2019

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class MemberRecycleViewAdapter(private val context: Context, private val itemClickListener: MemberViewHolder.ItemClickListener, private val itemList:List<String>) : RecyclerView.Adapter<MemberViewHolder>() {
    private var mRecyclerView : RecyclerView? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            val layoutInflater = LayoutInflater.from(context)
            val mView = layoutInflater.inflate(R.layout.list_view, parent, false)

            mView.setOnClickListener { view ->
                mRecyclerView?.let {
                    itemClickListener.onItemClick(view, it.getChildAdapterPosition(view))
                }
            }

            return MemberViewHolder(mView)
        }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder?.let {
            it.nameID = itemList.get(position)
            it.twitterID.toString() = itemList.get(position)
            it.githubID.toString() = itemList.get(position)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}