package com.gaffaryucel.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gaffaryucel.news.R
import com.gaffaryucel.news.databinding.NewsDetailsRowBinding
import com.gaffaryucel.news.model.Result

class SavedNewsDetailsAdapter (
    private val newsList: ArrayList<Result>,
    private val deleteClickListener: DeleteClickListener
) : RecyclerView.Adapter<SavedNewsDetailsAdapter.NewsHolder>() {

    var savedList = ArrayList<Result>()
    inner class NewsHolder(val binding: NewsDetailsRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = NewsDetailsRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.binding.saveImageView.setImageResource(
            R.drawable.saved
        )
        val news = newsList[position]
        Glide.with(holder.itemView.context)
            .load(news.fields.thumbnail)
            .into(holder.binding.imageView2)
        holder.binding.includetextView.text = news.fields.bodyText
        holder.binding.newsTitleTextView.text = news.fields.headline

        holder.binding.saveImageView.setOnClickListener {
            newsList.removeAt(position)
            deleteClickListener.onDeleteClick(news)
            notifyItemRemoved(position)
            holder.binding.saveImageView.setImageResource(
                R.drawable.save
            )
        }

    }
    override fun getItemCount(): Int {
        return newsList.size
    }
}