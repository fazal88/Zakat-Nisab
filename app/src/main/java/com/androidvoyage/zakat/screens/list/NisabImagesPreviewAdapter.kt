package com.androidvoyage.zakat.screens.list

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidvoyage.zakat.databinding.ItemImageNisabSmallBinding
import com.androidvoyage.zakat.databinding.ItemImagePreviewBinding


open class NisabImagesPreviewAdapter : ListAdapter<String, NisabImagesPreviewAdapter.ViewHolder>(
    PassbookDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.from(parent)

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    class PassbookDiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean =
            oldItem == newItem
    }

    class ViewHolder private constructor(val binding: ItemImagePreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val res = itemView.context.resources

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        fun bind(
            item: String
        ) {
            binding.vm = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = ItemImagePreviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ViewHolder(binding)
            }
        }
    }

}