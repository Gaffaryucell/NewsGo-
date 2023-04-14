package com.gaffaryucel.news.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gaffaryucel.news.R
import com.gaffaryucel.news.databinding.NewsRowBinding
import com.gaffaryucel.news.model.News
import com.gaffaryucel.news.view.NewsFragmentDirections
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.load.DataSource
import android.graphics.drawable.Drawable
import android.view.View


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    class NewsHolder(val binding : NewsRowBinding) : RecyclerView.ViewHolder(binding.root)

    var newsList = ArrayList<com.gaffaryucel.news.model.Result>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val binding = NewsRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        var news = newsList.get(position)
        holder.binding.imageView.visibility = View.INVISIBLE

        Glide.with(holder.itemView.context)
            .load(news.fields.thumbnail)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    return false
                }
                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    // Perform actions when the image loading is completed
                    holder.binding.imageProgress.visibility = View.INVISIBLE
                    holder.binding.imageView.visibility = View.VISIBLE
                    return false
                }
            })
            .into(holder.binding.imageView)

        holder.binding.titleText.text = news.fields.headline
        holder.binding.distext.text = news.fields.bodyText
        holder.itemView.setOnClickListener {
            val action = NewsFragmentDirections.actionNavHomeToDetailsFragment2(position)
            Navigation.findNavController(it).navigate(action)
        }
    }
    override fun getItemCount(): Int {
       return newsList.size
    }

}
fun placeHolderProgressBar(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        setColorSchemeColors(R.color.black)
        start()
    }
}
