package me.danny.mpc.api;

import me.danny.mpc.api.net.packets.CurrentSongPacket;

/**
 * Represents the current song response from a {@link CurrentSongPacket}
 * @author dasha
 */
public final class CurrentSong {

    /**
     * The currently playing song's filename
     */
    public String filename = "<Unknown File>";
    /**
     * The artist of the currently playing song
     */
    public String artist = "<No Artist>";
    /**
     * The album the current song appears on
     */
    public String album = "<No Album>";
    /**
     * The song's title, as set in the file's metadata
     */
    public String title = "<No Song>";
    /**
     * The position of the current song in the playlist
     */
    public int track = -1;
    /**
     * The ID of the song
     */
    public int songid = -1;
    
}
