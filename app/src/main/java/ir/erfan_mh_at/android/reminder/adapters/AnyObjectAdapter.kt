package ir.erfan_mh_at.android.reminder.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ir.erfan_mh_at.android.reminder.R
import ir.erfan_mh_at.android.reminder.app.AppFonts
import ir.erfan_mh_at.android.reminder.db.entitys.AnyObject
import kotlinx.android.synthetic.main.layout_any_object.view.*

class AnyObjectAdapter : RecyclerView.Adapter<AnyObjectAdapter.AnyObjectViewHolder>() {

    inner class AnyObjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<AnyObject>() {
        override fun areItemsTheSame(oldItem: AnyObject, newItem: AnyObject): Boolean =
            if (oldItem.id == null && newItem.id == null) oldItem.created_at == newItem.created_at
            else oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AnyObject, newItem: AnyObject): Boolean =
            oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallBack)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnyObjectViewHolder =
        AnyObjectViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_any_object,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: AnyObjectViewHolder, position: Int) {
        val anyObject = differ.currentList[position]
        holder.itemView.apply {
            tvName.text = anyObject.name
            tvCreateAt.text = anyObject.created_at
            tvContent.text = anyObject.data

            tvName.typeface = AppFonts.SUMMER_CALLING_FONT
            tvCreateAt.typeface = AppFonts.SUMMER_CALLING_FONT
            tvContent.typeface = AppFonts.SUMMER_CALLING_FONT

            setOnClickListener {
                onItemClickListener?.let {
                    it(anyObject)
                }
            }
        }
    }

    private var onItemClickListener: ((AnyObject) -> Unit)? = null

    fun setOnItemClickListener(listener: (AnyObject) -> Unit) {
        onItemClickListener = listener
    }
}