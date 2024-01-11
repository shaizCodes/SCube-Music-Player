package edu.sibau.scube_music_player.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import edu.sibau.scube_music_player.R
import edu.sibau.scube_music_player.databinding.ActivityPlaylistsBinding

class PlaylistsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlaylistsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SCubeMusicPlayer_NoActionBar)
        binding = ActivityPlaylistsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { backToMainActivity() }
        binding.btnOptions.setOnClickListener { dummyText() }

        binding.songTitle.setOnClickListener { openPlayer() }
        binding.songDescription.setOnClickListener { openPlayer() }
        binding.songThumbnail.setOnClickListener { openPlayer() }

        binding.btnPrevious.setOnClickListener { dummyText() }
        binding.btnPlay.setOnClickListener { dummyText() }
        binding.btnNext.setOnClickListener { dummyText() }
        // Write Event Listeners for RecyclerView Items as well
    }

    private fun dummyText() {
        Toast.makeText(this, "To be implemented...", Toast.LENGTH_SHORT).show()
    }

    private fun backToMainActivity() {
        val intent = Intent(this@PlaylistsActivity, MainActivity::class.java)
        startActivity(intent)
    }

    private fun openPlayer() {
        val intent = Intent(this@PlaylistsActivity, PlayerActivity::class.java)
        startActivity(intent)
    }

}