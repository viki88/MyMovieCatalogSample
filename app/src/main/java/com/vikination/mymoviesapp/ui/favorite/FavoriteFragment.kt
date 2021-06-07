package com.vikination.mymoviesapp.ui.favorite

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.vikination.mymoviesapp.databinding.FragmentFavoriteBinding
import com.vikination.mymoviesapp.response.Movie
import com.vikination.mymoviesapp.room.Favorite
import com.vikination.mymoviesapp.ui.detailmovie.DetailMovieActivity

class FavoriteFragment :Fragment(), OnClickItemFavListListener{

    lateinit var binding :FragmentFavoriteBinding
    lateinit var viewModel :FavoriteViewModel
    lateinit var adapter :FavListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupList()

        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        viewModel.favListLiveData.observe(viewLifecycleOwner){
            adapter.updateListFavData(it)
        }

        viewModel.getAllDataFav()
    }

    private fun setupList(){
        adapter = FavListAdapter(this)
        binding.rvFavMovie.adapter = adapter
        binding.rvFavMovie.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun deleteFav(favorite: Favorite) {
        AlertDialog.Builder(requireContext())
            .setTitle("Delete Favorite Movie")
            .setMessage("Apakah anda yakin akan menghapus data \"${favorite.titleMovie}\" ?")
            .setPositiveButton("Hapus"){ dialog,_ ->
                dialog.dismiss()
                // hapus data ke db
                viewModel.deleteDataFav(favorite.idMovie)
            }.setNegativeButton("Batal"){dialog,_ ->
                dialog.dismiss()
            }.create().show()
    }

    override fun onClick(favorite: Favorite) {

        val movie = Movie(
            title = favorite.titleMovie,
            poster_path = favorite.posterPath,
            overview = favorite.overview,
            release_date = favorite.releaseDate,
            vote_average = favorite.rating.toDouble()
        )

        val intent = Intent(requireContext(), DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.MOVIE_KEY, movie)
        startActivity(intent)

    }
}