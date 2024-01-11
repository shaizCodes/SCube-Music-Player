package edu.sibau.scube_music_player.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import edu.sibau.scube_music_player.R
import edu.sibau.scube_music_player.databinding.ActivityRecentBinding

class RecentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRecentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SCubeMusicPlayer_NoActionBar)
        binding = ActivityRecentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { backToMainActivity() }
        binding.btnOptions.setOnClickListener { dummyText() }
        binding.btnPrevious.setOnClickListener { dummyText() }
        binding.btnPlay.setOnClickListener { dummyText() }
        binding.btnNext.setOnClickListener { dummyText() }
        // Write Event Listeners for RecyclerView Items as well
    }

    private fun dummyText() {
        Toast.makeText(this, "To be implemented...", Toast.LENGTH_SHORT).show()
    }

    private fun backToMainActivity() {
        val intent = Intent(this@RecentActivity, MainActivity::class.java)
        startActivity(intent)
    }
}