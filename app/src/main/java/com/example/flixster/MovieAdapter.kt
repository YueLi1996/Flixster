package com.example.flixster

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixster.models.Movie

const val MOVIE_EXTRA = "MOVIE_EXTRA"
class MovieAdapter(private val context: Context, private val movies: MutableList<Movie>)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)
        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(movie:Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            var imageURL:String = ""
            val orientation = context.resources.configuration.orientation
            if(orientation == Configuration.ORIENTATION_PORTRAIT) {
                imageURL = movie.posterImageURL
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageURL = movie.backdropImageURL
            }

            Glide.with(context)
                .load(imageURL)
                .placeholder(R.drawable.placeholder)
                .into(ivPoster)

        }

        override fun onClick(v: View?) {
            val movie = movies[adapterPosition]

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(MOVIE_EXTRA, movie) // make movie model Parcelize
            context.startActivity(intent)

        }
    }

}
