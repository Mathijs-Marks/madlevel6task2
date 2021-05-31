package com.example.madlevel6task2.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.madlevel6task2.R
import com.example.madlevel6task2.databinding.ItemMovieBinding
import com.example.madlevel6task2.model.MovieItem

class MovieAdapter(private val movies: List<MovieItem>, private val onClick: (MovieItem) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    /**
     * This class takes the data from the data model and arranges them in the correct order in the
     * MovieListFragment class. An OnClickListener is added to enable clicking on each movie in the
     * list.
     */

    private lateinit var context: Context

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClick(movies[adapterPosition]) }
        }

        private val binding = ItemMovieBinding.bind(itemView)

        // Take each image Url and use Glide to load them into the adapter.
        fun bind(movieItem: MovieItem) {
            Glide.with(context).load(movieItem.getPosterPathUrl()).into(binding.ivMoviePosterItem)
            binding.tvMovieNumber.text = (movies.indexOf(movieItem) + 1).toString()
        }
    }

    // When the ViewHolder is created, load the necessary movie items.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        context = parent.context

        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        )
    }

    // The amount of movies to put into the RecyclerView is equal to the size of the movies list.
    override fun getItemCount(): Int = movies.size

    // When the ViewHolder is bound, put the movie items into the correct position in the RecyclerView.
    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) = holder.bind(movies[position])


}