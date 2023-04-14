package com.gaffaryucel.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gaffaryucel.news.R
import com.gaffaryucel.news.database.NewsDao
import com.gaffaryucel.news.databinding.NewsDetailsRowBinding
import com.gaffaryucel.news.model.Result

class NewsDetailsAdapter(
    private val newsList: List<Result>,
    private val saveClick : SaveClickListener,
    private val deleteClickListener: DeleteClickListener
) : RecyclerView.Adapter<NewsDetailsAdapter.NewsHolder>() {

    var savedList = ArrayList<Result>()
    inner class NewsHolder(val binding: NewsDetailsRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = NewsDetailsRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val news = newsList[position]
        var saved = false
        savedList.forEach {
            if (news == it){
                saved = true
                holder.binding.saveImageView.setImageResource(R.drawable.saved)
            }
        }
        Glide.with(holder.itemView.context)
            .load(news.fields.thumbnail)
            .into(holder.binding.imageView2)
        holder.binding.includetextView.text = news.fields.bodyText
        holder.binding.newsTitleTextView.text = news.fields.headline
        holder.binding.saveImageView.setOnClickListener {
            if (saved){
                holder.binding.saveImageView.setImageResource(R.drawable.save)
                deleteClickListener.onDeleteClick(news)
            }else{
                holder.binding.saveImageView.setImageResource(R.drawable.saved)
                saveClick.onNewsItemClicked(news)
            }
        }
    }
    override fun getItemCount(): Int {
        return newsList.size
    }
}
