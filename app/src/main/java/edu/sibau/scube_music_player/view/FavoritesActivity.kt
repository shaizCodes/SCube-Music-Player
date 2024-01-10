package edu.sibau.scube_music_player.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.sibau.scube_music_player.R
import edu.sibau.scube_music_player.databinding.ActivityFavoritesBinding

class FavoritesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFavoritesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SCubeMusicPlayer)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}