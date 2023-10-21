package com.example.bookapp.presentation.fragments.main.pages.home

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookapp.R
import com.example.bookapp.data.local.Entity.BookEntity
import com.example.bookapp.databinding.ListItemBooksBinding
import com.example.bookapp.utils.inflate

class BooksAdapter : ListAdapter<BookEntity, BooksAdapter.ViewHolder>(itemBookCallback) {

    private var itemClickListener: ((BookEntity) -> Unit)? = null


    fun setItemClickListener(block: (BookEntity) -> Unit) {
        itemClickListener = block
    }

    inner class ViewHolder(private val binding: ListItemBooksBinding) :
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
                tvBookName.setSingleLine()
                tvBookName.text = data.name
//                tvBookPage.text = "${data.pages} pages"
            }
            Glide.with(binding.imageBook.context)
                .load(data.imageUrl)
                .placeholder(R.drawable.ic_group)
                .into(binding.imageBook)

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
      ListItemBooksBinding.bind(parent.inflate(R.layout.list_item_books))
    )

    override fun onBindViewHolder(holder: BooksAdapter.ViewHolder, position: Int) = holder.onBind()
}

private val itemBookCallback = object : DiffUtil.ItemCallback<BookEntity>() {
    override fun areItemsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: BookEntity, newItem: BookEntity): Boolean =
        oldItem.pages == newItem.pages &&
                oldItem.author == newItem.author &&
                oldItem.name == newItem.name

}