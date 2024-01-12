package edu.sibau.scube_music_player.model

data class Song(
    val id : String,
    val title : String,
    val artist : String,
    val album : String,
    val duration : Long = 0,
    val path : String,
    val artUri : String
){
    override fun toString(): String {
        return super.toString()+
                "\nID: "+id+
                "\nTITLE: "+title+
                "\nARTIST: "+artist+
                "\nALBUM: "+album+
                "\nDURATION: "+duration+
                "\nPATH: "+path+
                "\nART URI: "+artUri
    }
}