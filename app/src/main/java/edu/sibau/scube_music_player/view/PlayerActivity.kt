package edu.sibau.scube_music_player.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import edu.sibau.scube_music_player.R
import edu.sibau.scube_music_player.databinding.ActivityPlayerBinding

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SCubeMusicPlayer_NoActionBar)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { backToMainActivity() }
        binding.btnAddFavorite.setOnClickListener { dummyText() }
        binding.btnOptions.setOnClickListener { dummyText() }
        binding.songProgress.setOnClickListener{ dummyText() }
        binding.btnPrevious.setOnClickListener { dummyText() }
        binding.btnPlay.setOnClickListener { dummyText() }
        binding.btnNext.setOnClickListener { dummyText() }
        binding.btnRepeat.setOnClickListener { dummyText() }
        binding.btnMove.setOnClickListener { dummyText() }
        binding.btnAddFavorite.setOnClickListener { dummyText() }
        binding.btnDetails.setOnClickListener { dummyText() }
    }

    private fun dummyText() {
        Toast.makeText(this, "To be implemented...", Toast.LENGTH_SHORT).show()
    }

    private fun backToMainActivity() {
        finish()
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