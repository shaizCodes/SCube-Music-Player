package edu.sibau.scube_music_player.view

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat.*
import androidx.recyclerview.widget.LinearLayoutManager
import edu.sibau.scube_music_player.R
import edu.sibau.scube_music_player.databinding.ActivityMainBinding
import edu.sibau.scube_music_player.model.Song
import java.io.File
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navToggle: ActionBarDrawerToggle
    private lateinit var songsAdapter: SongsAdapter

    companion object{
        lateinit var songsList : ArrayList<Song>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestRuntimePermission()

        setTheme(R.style.Theme_SCubeMusicPlayer)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navToggle = ActionBarDrawerToggle(this, binding.root, R.string.open, R.string.close)
        binding.root.addDrawerListener(navToggle)
        navToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.songsList.setHasFixedSize(true)
        binding.songsList.setItemViewCacheSize(15)
        binding.songsList.layoutManager = LinearLayoutManager(this@MainActivity)

        // Uncomment these lines when running app on an emulator to fill recyclerView with dummy data
//        val song = Song(id="123221", title="Mockingbird", artist="Eminem", album="N/A", duration=213, path="N/A", artUri="N/A")
//        songsList = ArrayList<Song>()
//        songsList.add(song)
//        songsList.add(song)
//        songsList.add(song)
//        songsList.add(song)
//        songsList.add(song)
//        songsList.add(song)
//        songsList.add(song)
//        songsList.add(song)
//        songsList.add(song)
//        songsList.add(song)
//        songsList.add(song)
//        songsList.add(song)
//        songsList.add(song)
//        songsList.add(song)
//        songsList.add(song)

        // Comment these two lines when working on emulator, else download some music files in emulator
        if(checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            songsList = retrieveSongs()
            songsAdapter = SongsAdapter(this@MainActivity, songsList)
            binding.songsList.adapter = songsAdapter
            Log.i("PERMISSION", "Songs")
        }
        else{
            binding.songsList.visibility = View.INVISIBLE
        }
        if(songsList.isEmpty()){
            binding.bottomBar.visibility = View.INVISIBLE
        }
        binding.navigation.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.item_sign_in -> dummyMessage()
                R.id.item_settings -> dummyMessage()
                R.id.item_about -> dummyMessage()
                R.id.item_exit -> exitProcess(1)
            }
            true
        }
        binding.btnRecent.setOnClickListener { openRecent() }
        binding.btnFavorites.setOnClickListener { openFavorites() }
        binding.btnPlaylist.setOnClickListener { openPlaylists() }
        binding.songTitle.setOnClickListener { openPlayer() }
        binding.songDescription.setOnClickListener { openPlayer() }
        binding.songThumbnail.setOnClickListener { openPlayer() }
        binding.btnPrevious.setOnClickListener { dummyMessage() }
        binding.btnPlay.setOnClickListener { dummyMessage() }
        binding.btnNext.setOnClickListener { dummyMessage() }
    }

    private fun openRecent() {
        val intent = Intent(this@MainActivity, RecentActivity::class.java)
        startActivity(intent)
    }

    private fun openFavorites() {
        val intent = Intent(this@MainActivity, FavoritesActivity::class.java)
        startActivity(intent)
    }

    private fun openPlaylists() {
        val intent = Intent(this@MainActivity, PlaylistsActivity::class.java)
        startActivity(intent)
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       if(navToggle.onOptionsItemSelected(item))
           return true
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("Range")
    private fun retrieveSongs() : ArrayList<Song>{
        val songs = ArrayList<Song>()
        val selection = MediaStore.Audio.Media.IS_MUSIC + " != 0"
        val projection = arrayOf(
            MediaStore.Audio.Media._ID,
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM,
            MediaStore.Audio.Media.ALBUM_ID,
            MediaStore.Audio.Media.DURATION,
            MediaStore.Audio.Media.DATA,
            MediaStore.Audio.Media.DATE_ADDED,
            )
        val cursor = this.contentResolver.query(
            MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
            projection,
            selection,
            null,
            MediaStore.Audio.Media.DATE_ADDED + " DESC",
            null
        )
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                    val title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                    val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                    val album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM))
                    val albumId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID))
                    val duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                    val data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA))
                    val dateAdded = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATE_ADDED))
                    val uri = Uri.parse("content://media/external/audio/albumart")
                    val artUri = Uri.withAppendedPath(uri, albumId).toString()
                    val song = Song(id=id, title=title, artist=artist, album=album, duration=duration, path=data, artUri=artUri)
                    val file = File(song.path)
                    if(file.exists()) {
                        Log.i("TEST", "FILE EXISTS")
                        songs.add(song)
                    }
                     Log.i("TEST *", songs.joinToString(separator = "]"))
                }while(cursor.moveToNext())
                cursor.close()
            }
        }
        Log.i("RETURN: ", songs.joinToString(separator = ", "))
        return songs
    }
}