package com.vikination.mymoviesapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vikination.mymoviesapp.databinding.ItemListBinding
import com.vikination.mymoviesapp.response.Movie
import com.vikination.mymoviesapp.utils.Constants

class MovieListAdapter(var onClickItemListMovieListener: OnClickItemListMovieListener) :RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>(){

    var listMovies = listOf<Movie>()

    // class untuk mapping data Movie ke item_list layout
    inner class MovieViewHolder(var binding :ItemListBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(movie :Movie){
            binding.titleItem.text = movie.title
            binding.descItem.text = movie.overview

            val urlImage = Constants.URL_BASE_IMAGE + Constants.IMAGE_SIZE_500 + movie.poster_path

            // load image menggunakan glide
            Glide.with(binding.root.context).load(urlImage).into(binding.thumbnailView)

            // handle click setiap item layout nya
            itemView.setOnClickListener {
                onClickItemListMovieListener.onClickMovieItem(movie)
            }
        }
    }

    // mapping layout ke view holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    // set item layout base on position movie list
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovies[position])
    }

    // adapter perlu tau isi keseluruhan item movie
    override fun getItemCount(): Int = listMovies.size

    fun updateDataMovies(listMovies :List<Movie>){
        this.listMovies = listMovies
        // notif ke si adapater bahwa ada data baru
        notifyDataSetChanged()
    }

}