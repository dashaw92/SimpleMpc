package me.danny.mpc.api;

/**
 * Models the state of playback an MPD server may have
 * @author dasha
 */
public enum PlaybackState {

    /**
     * Songs are playing
     */
    PLAY,
    /**
     * Playback is paused
     */
    PAUSE,
    /**
     * The server is stopped, or there was an error.<br>
     * This state is returned when an error occurred between the client<br>
     * and the connection to the server, i.e. the client is not connected.
     */
    STOP;
    
    /**
     * Utility for inversing the state.
     * @return The inverse state: <br>
     * {@link #PLAY} -> {@link #PAUSE}<br>
     * {@link #PAUSE} -> {@link #PLAY}<br>
     * {@link #STOP} -> {@link #STOP}
     */
    public PlaybackState inverse() {
        switch(this) {
            case PLAY:
                return PlaybackState.PAUSE;
            case PAUSE:
                return PlaybackState.PLAY;
            default:
                return this;
        }
    }
}
