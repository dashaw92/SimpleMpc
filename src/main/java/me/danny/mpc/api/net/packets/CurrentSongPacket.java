package me.danny.mpc.api.net.packets;

import java.util.List;

import me.danny.mpc.api.CurrentSong;

/**
 * Retrieve info about the currently playing song
 * @see {@link CurrentSong}
 */
public class CurrentSongPacket implements Packet<CurrentSong> {

    @Override
    public String getData() {
        return "currentsong";
    }
    
    @Override
    public CurrentSong mapResponse(List<String> response) {
        CurrentSong song = new CurrentSong();
        for(String line : response) {
            String[] fields = line.split(": ", 2);
            if(fields.length < 2) continue;
            switch(fields[0].toLowerCase()) {
                case "file":
                    song.filename = fields[1];
                    break;
                case "artist":
                    song.artist = fields[1];
                    break;
                case "title":
                    song.title = fields[1];
                    break;
                case "album":
                    song.album = fields[1];
                    break;
                case "id":
                    song.songid = Integer.parseInt(fields[1]);
                    break;
            }
        }
        
        return song;
    }
}