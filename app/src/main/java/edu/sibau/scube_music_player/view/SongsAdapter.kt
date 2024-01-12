package edu.sibau.scube_music_player.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import edu.sibau.scube_music_player.R
import edu.sibau.scube_music_player.databinding.FragmentSongsListItemBinding
import edu.sibau.scube_music_player.model.Song


class SongsAdapter(private val context: Context, private val songs: ArrayList<Song>)
    : RecyclerView.Adapter<SongsAdapter.ViewHolder>(){
        class ViewHolder (binding: FragmentSongsListItemBinding)
            : RecyclerView.ViewHolder(binding.root){
                val title = binding.songTitle
                val description = binding.songDescription
                val thumbnail = binding.songThumbnail
                val root = binding.root
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(FragmentSongsListItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = songs[position].title
        holder.description.text = songs[position].artist + " - " + songs[position].album
        Glide.with(context)
            .load(songs[position].artUri)
            .apply(RequestOptions().placeholder(R.drawable.splash_screen).centerCrop())
            .into(holder.thumbnail)
        holder.root.setOnClickListener {
            val intent = Intent(context, PlayerActivity::class.java)
            intent.putExtra("index", position)
            intent.putExtra("class", "MainActivity")
            ContextCompat.startActivity(context, intent, null)
        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }

}