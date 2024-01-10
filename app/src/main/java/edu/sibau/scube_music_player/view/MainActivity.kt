package edu.sibau.scube_music_player.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat.*
import edu.sibau.scube_music_player.R
import edu.sibau.scube_music_player.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        TO ASK FOR EXTERNAL STORAGE PERMISSION
        requestRuntimePermission()
        setTheme(R.style.Theme_SCubeMusicPlayer)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRecent.setOnClickListener { dummyMessage() }
        binding.btnFavorites.setOnClickListener { dummyMessage() }
        binding.btnPlaylist.setOnClickListener { dummyMessage() }
        binding.songThumbnail.setOnClickListener { openPlayer() }
        binding.btnPrevious.setOnClickListener { dummyMessage() }
        binding.btnPlay.setOnClickListener { dummyMessage() }
        binding.btnNext.setOnClickListener { dummyMessage() }
    }

    private fun openPlayer() {
        val intent = Intent(this@MainActivity, PlayerActivity::class.java)
        startActivity(intent)
    }

    private fun dummyMessage() {
        Toast.makeText(this@MainActivity, "To be implemented...", Toast.LENGTH_SHORT).show()
    }

    private fun requestRuntimePermission(){
        if(checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
            requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 0){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                dummyMessage()
            }
            else{
                requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 0)
            }
        }
    }
}