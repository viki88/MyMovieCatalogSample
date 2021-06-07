package com.vikination.mymoviesapp.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.vikination.mymoviesapp.R
import com.vikination.mymoviesapp.databinding.ActivityMainBinding
import com.vikination.mymoviesapp.ui.favorite.FavoriteFragment
import com.vikination.mymoviesapp.ui.trending.TrendingFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding :ActivityMainBinding

    private lateinit var fragmentTrending :TrendingFragment
    private lateinit var fragmentFavorite :FavoriteFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentTrending = TrendingFragment()
        fragmentFavorite = FavoriteFragment()

        replaceFragment(fragmentTrending)
        binding.bottomNavView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_trending -> {
                    replaceFragment(fragmentTrending)
                }
                R.id.menu_favorite_bottom -> {
                    replaceFragment(fragmentFavorite)
                }
            }
            true
        }

    }

    // replace fragment on container
    private fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_fragment, fragment)
            .commit()
    }
}