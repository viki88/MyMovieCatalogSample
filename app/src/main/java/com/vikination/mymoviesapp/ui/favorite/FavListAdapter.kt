package com.vikination.mymoviesapp.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vikination.mymoviesapp.databinding.ItemListBinding
import com.vikination.mymoviesapp.room.Favorite
import com.vikination.mymoviesapp.utils.Constants

class FavListAdapter(var onClickItemFavListListener: OnClickItemFavListListener) :RecyclerView.Adapter<FavListAdapter.FavViewHolder>(){

    var listFavorite = listOf<Favorite>()

    inner class FavViewHolder(var binding: ItemListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favorite: Favorite){

            binding.titleItem.text = favorite.titleMovie
            binding.descItem.text = favorite.overview

            val urlImage = Constants.URL_BASE_IMAGE + Constants.IMAGE_SIZE_500 + favorite.posterPath

            // load image menggunakan glide
            Glide.with(binding.root.context).load(urlImage).into(binding.thumbnailView)

            // handle click setiap item layout nya
            itemView.setOnClickListener {
                onClickItemFavListListener.onClick(favorite)
            }

            // handle long press , untuk delete
            itemView.setOnLongClickListener {
                onClickItemFavListListener.deleteFav(favorite)
                true
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }

    override fun getItemCount(): Int = listFavorite.size

    fun updateListFavData(listFavorite: List<Favorite>){
        this.listFavorite = listFavorite
        notifyDataSetChanged()
    }
}