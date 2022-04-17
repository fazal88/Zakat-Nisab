package com.androidvoyage.zakat.screens.dashboard

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidvoyage.zakat.databinding.ItemDashboardBinding
import com.androidvoyage.zakat.model.NisabCategoryItem


open class DashboardListAdapter(
    private val clickListener: DashboardClickListener
) : ListAdapter<NisabCategoryItem, DashboardListAdapter.ViewHolder>(
    PassbookDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position), clickListener)

    class PassbookDiffCallback : DiffUtil.ItemCallback<NisabCategoryItem>() {
        override fun areItemsTheSame(oldItem: NisabCategoryItem, newItem: NisabCategoryItem): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: NisabCategoryItem, newItem: NisabCategoryItem): Boolean =
            oldItem.type == newItem.type
    }

    class ViewHolder private constructor(val binding: ItemDashboardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val res = itemView.context.resources

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(
            item: NisabCategoryItem,
            clickListener: DashboardClickListener
        ) {
            binding.item = item
            binding.click = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ItemDashboardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(binding)
            }
        }
    }

    open class DashboardClickListener(
        val clickFeature: (item: String) -> Unit,
        val addFeature: (item: String) -> Unit,
    ) {

        fun onClickFeature(item: String) = clickFeature(item)
        fun onAddFeature(item: String) = addFeature(item)
    }

}