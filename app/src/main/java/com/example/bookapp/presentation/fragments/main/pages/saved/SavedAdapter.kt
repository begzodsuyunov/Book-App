package com.example.bookapp.presentation.fragments.main.pages.saved

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookapp.R
import com.example.bookapp.data.local.Entity.BookEntity
import com.example.bookapp.databinding.ListItemSavedBinding
import com.example.bookapp.utils.inflate

class SavedAdapter : ListAdapter<BookEntity, SavedAdapter.ViewHolder>(itemBookCallback){

    private var itemClickListener: ((BookEntity) -> Unit)? = null

    fun setItemClickListener(block: (BookEntity) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ListItemSavedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(getItem(absoluteAdapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun onBind() {

            val data = getItem(absoluteAdapterPosition)
            binding.apply {
                tvBookAuthor.text = data.author
                tvBookName.text = data.name
                tvBookName.setSingleLine()
                tvBookPage.text = "${data.pages} page"
                tvBookReadPage.text = "${data.currentPage} page read"
            }
            Glide.with(binding.imageBook.context)
                .load(data.imageUrl)
                .placeholder(R.drawable.ic_group)
                .into(binding.imageBook)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =ViewHolder(
        ListItemSavedBinding.bind(parent.inflate(R.layout.list_item_saved))
    )


    override fun onBindViewHolder(holder: SavedAdapter.ViewHolder, position: Int) = holder.onBind()
}

private val itemBookCallback = object : DiffUtil.ItemCallback<BookEntity>() {
    override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean =
        oldItem.pages == newItem.pages &&
                oldItem.author == newItem.author &&
                oldItem.name == newItem.name &&
                oldItem.currentPage == newItem.currentPage &&
                oldItem.download == newItem.download

}