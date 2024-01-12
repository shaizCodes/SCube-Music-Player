package edu.sibau.scube_music_player.view

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import edu.sibau.scube_music_player.R
import edu.sibau.scube_music_player.databinding.ActivityPlayerBinding
import edu.sibau.scube_music_player.model.Song
import java.util.concurrent.TimeUnit

class PlayerActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPlayerBinding

    companion object {
        lateinit var songsList : ArrayList<Song>
        var songPosition : Int = 0
        var mediaPlayer : MediaPlayer ?= null
        var isPlaying : Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SCubeMusicPlayer_NoActionBar)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadCurrentSong()

        binding.btnBack.setOnClickListener { backToMainActivity() }

        binding.btnAddFavorite.setOnClickListener { dummyText() }
        binding.btnOptions.setOnClickListener { dummyText() }
        binding.songProgress.setOnClickListener{ dummyText() }

        binding.btnPrevious.setOnClickListener { playPreviousSong() }
        binding.btnPlay.setOnClickListener { playOrPauseSong() }
        binding.btnNext.setOnClickListener { playNextSong() }

        binding.btnRepeat.setOnClickListener { dummyText() }
        binding.btnMove.setOnClickListener { dummyText() }
        binding.btnAddFavorite.setOnClickListener { dummyText() }
        binding.btnDetails.setOnClickListener { dummyText() }
    }

    private fun loadCurrentSong() {
        songPosition = intent.getIntExtra("index", 0)
        when(intent.getStringExtra("class")) {
            "MainActivity" -> {
                songsList = ArrayList()
                songsList.addAll(MainActivity.songsList)
                setLayout()
                createMediaPlayer()
            }
        }
    }

    private fun playOrPauseSong() {
        if(isPlaying){
            pauseCurrentSong()
        }
        else{
            playCurrentSong()
        }
    }

    private fun dummyText() {
        Toast.makeText(this, "To be implemented...", Toast.LENGTH_SHORT).show()
    }

    private fun backToMainActivity() {
        finish()
    }

    private fun setLayout(){
        Glide.with(this)
            .load(songsList[songPosition].artUri)
            .apply(RequestOptions().placeholder(R.drawable.splash_screen).centerCrop())
            .into(binding.songThumbnail)
        binding.songTitle.text = songsList[songPosition].title
        Glide.with(this)
            .load(songsList[songPosition].artUri)
            .apply(RequestOptions().placeholder(R.drawable.splash_screen).centerCrop())
            .into(binding.songThumbnail)
        val duration = songsList[songPosition].duration
        val minutes = TimeUnit.MINUTES.convert(duration, TimeUnit.MILLISECONDS)
        val seconds = TimeUnit.SECONDS.convert(duration, TimeUnit.MILLISECONDS) - minutes*60
        binding.txtSongFullDuration.text = String.format("%02d:%02d", minutes, seconds)
    }

    private fun createMediaPlayer() {
        try {
            if(mediaPlayer == null) {
                mediaPlayer = MediaPlayer()
            }
            mediaPlayer!!.reset()
            mediaPlayer!!.setDataSource(songsList[songPosition].path)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
            isPlaying = true
            binding.btnPlay.setImageDrawable(resources.getDrawable(R.drawable.pause64))
        }catch (e: Exception){
            return
        }
    }

    fun addToFavorites() {
        //TODO: implementation required...
    }

    fun changeDuration() {
        //TODO: implementation required...
    }
    fun playPreviousSong() {
        if(songsList.isNotEmpty()){
            if(songPosition>0){
                songPosition--
            }
            else{
                songPosition = songsList.size-1
            }
            setLayout()
            createMediaPlayer()
        }
    }
    fun playCurrentSong() {
        binding.btnPlay.setImageDrawable(resources.getDrawable(R.drawable.pause64))
        isPlaying = true
        mediaPlayer!!.start()
    }

    fun pauseCurrentSong() {
        binding.btnPlay.setImageDrawable(resources.getDrawable(R.drawable.play64))
        isPlaying = false
        mediaPlayer!!.pause()
    }
    fun playNextSong() {
        if(songsList.isNotEmpty()){
            if(songPosition < songsList.size-1){
                songPosition++
            }
            else{
                songPosition = 0
            }
            setLayout()
            createMediaPlayer()
        }
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