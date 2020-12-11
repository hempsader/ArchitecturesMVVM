package com.example.architecturesmvvm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.architectures.model.Movie
import com.squareup.picasso.Picasso

class ListMovieRecycler (val moviesList: List<Movie>): RecyclerView.Adapter<ListMovieRecycler.MoviesHolder>(){

    inner class MoviesHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private  var image :ImageView
        private  var title: TextView
        private  var year: TextView
        init {
            image = itemView.findViewById(R.id.movie_imageview)
            title = itemView.findViewById(R.id.title_textview)
            year = itemView.findViewById(R.id.release_date_textview)
        }
        fun showMovie(movie: Movie){
           if(movie.posterPath != null) {
               Picasso.get().load(movie.posterPath).into(image)
           }else{
               image.setImageResource(R.drawable.ic_local_movies_gray)
           }
            title.text = movie.title
            year.text = movie.getReleaseYearFromDate()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        return MoviesHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_main,parent,false))
    }

    override fun getItemCount() = moviesList.size

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        val movie = moviesList[position]
        holder.showMovie(movie)
    }
}