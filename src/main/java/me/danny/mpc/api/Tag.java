package me.danny.mpc.api;

import me.danny.mpc.api.net.packets.ListPacket;

/**
 * All the different ways to list the MPD database with a {@link ListPacket}
 * @author dasha
 */
public enum Tag {

    /**
     * Query artists
     */
    ARTIST,
    /**
     * Query artists (sorted)
     */
    ARTIST_SORT,
    /**
     * Query albums
     */
    ALBUM,
    /**
     * Query albums (sorted)
     */
    ALBUM_SORT,
    /**
     * Query by albums and artists (sorted)
     */
    ALBUM_ARTIST_SORT,
    /**
     * Query by title
     */
    TITLE,
    /**
     * Query by track
     */
    TRACK,
    /**
     * Query by label the songs appear on
     */
    LABEL,
    
}
