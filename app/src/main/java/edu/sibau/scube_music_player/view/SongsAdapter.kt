package edu.sibau.scube_music_player.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
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
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsAdapter.ViewHolder {
        return ViewHolder(FragmentSongsListItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: SongsAdapter.ViewHolder, position: Int) {
        holder.title.text = songs[position].title
        holder.description.text = songs[position].artist + " - " + songs[position].album
//        holder.thumbnail.setImageDrawable(songs[position].path)
        Glide.with(context)
            .load(songs[position].artUri)
            .apply(RequestOptions().placeholder(R.drawable.splash_screen).centerCrop())
            .into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return songs.size
    }

}