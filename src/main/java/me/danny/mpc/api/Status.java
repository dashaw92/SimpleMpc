package me.danny.mpc.api;

public final class Status {

    public boolean repeat, random, consume;
    public Single single = null;
    public int playlist, playlistlength;
    public PlaybackState state = null;
    public int song, nextsong;
    public int songid, nextsongid;
    public double elapsed;
    public int bitrate;
    public double duration;
    
    public static enum Single {

        TRUE,
        FALSE,
        ONESHOT,
        
    }
    
}
