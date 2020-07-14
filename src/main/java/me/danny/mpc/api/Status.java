package me.danny.mpc.api;

public final class Status {

    public boolean repeat = false, random = false, consume = false;
    public Single single = Single.FALSE;
    public int playlist = -1, playlistlength = 0;
    public PlaybackState state = PlaybackState.STOP;
    public int song = -1, nextsong = -1;
    public int songid = -1, nextsongid = -1;
    public double elapsed = -1d;
    public int bitrate = 0x0;
    public double duration = -1d;
    
    public static enum Single {

        TRUE,
        FALSE,
        ONESHOT,
        
    }
    
}
