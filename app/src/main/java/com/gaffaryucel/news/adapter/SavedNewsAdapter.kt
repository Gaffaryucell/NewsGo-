package com.gaffaryucel.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gaffaryucel.news.R
import com.gaffaryucel.news.databinding.NewsDetailsRowBinding
import com.gaffaryucel.news.databinding.SavedNewsRowBinding
import com.gaffaryucel.news.view.SavedNewsFragmentDirections

class SavedNewsAdapter(
        private val newsList: ArrayList<com.gaffaryucel.news.model.Result>,
        private val deleteClickListener: DeleteClickListener
    ) : RecyclerView.Adapter<SavedNewsAdapter.NewsHolder>() {

    inner class NewsHolder(val binding: SavedNewsRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = SavedNewsRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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
        holder.binding.distext.text = news.fields.bodyText
        holder.binding.newsTitleTextView.text = news.fields.headline

        holder.binding.seemoreText.setOnClickListener{
            val action = SavedNewsFragmentDirections.actionNavGalleryToSavedNewsDetailsFragment(position)
            Navigation.findNavController(it).navigate(action)
        }
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