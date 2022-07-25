package com.androidvoyage.zakat.screens.list

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidvoyage.zakat.databinding.ItemDashboardBinding
import com.androidvoyage.zakat.databinding.ItemListNisabBinding
import com.androidvoyage.zakat.model.NisabItem
import com.androidvoyage.zakat.util.onClickWithAnimation


open class NisabListAdapter(
    private val clickListener: NisabClickListener
) : ListAdapter<NisabItem, NisabListAdapter.ViewHolder>(
    PassbookDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), clickListener)

    class PassbookDiffCallback : DiffUtil.ItemCallback<NisabItem>() {
        override fun areItemsTheSame(oldItem: NisabItem, newItem: NisabItem): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: NisabItem, newItem: NisabItem): Boolean =
            oldItem == newItem
    }

    class ViewHolder private constructor(val binding: ItemListNisabBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val res = itemView.context.resources

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(
            item: NisabItem,
            clickListener: NisabClickListener
        ) {
            binding.vm = item
            binding.click = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ItemListNisabBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(binding)
            }
        }
    }

    open class NisabClickListener(
        val editNisab: (item: NisabItem) -> Unit,
        val deleteNisab: (item: NisabItem) -> Unit,
        val viewNisab: (item: NisabItem) -> Unit
    ) {
        fun onClickEdit(item: NisabItem) = editNisab(item)
        fun onClickDelete(item: NisabItem) = deleteNisab(item)
        fun onClickImage(item: NisabItem) = viewNisab(item)
    }

}