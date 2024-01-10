package edu.sibau.scube_music_player.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.sibau.scube_music_player.R
import edu.sibau.scube_music_player.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SCubeMusicPlayer)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun addToFavorites() {
        //TODO: implementation required...
    }

    fun changeDuration() {
        //TODO: implementation required...
    }
    fun playPreviousSong() {
        //TODO: implementation required...
    }
    fun playCurrentSong() {
        //TODO: implementation required...
    }
    fun playNextSong() {
        //TODO: implementation required...
    }
    fun repeatCurrentSong() {
        //TODO: implementation required...
    }
    fun moveCurrentSong() {
        //TODO: implementation required...
    }
    fun shareCurrentSong() {
        //TODO: implementation required...
    }
}