package me.danny.mpc.api.net.packets;

import java.util.List;

import me.danny.mpc.api.PlaybackState;

/**
 * Toggles the current state of the server
 * @see {@link PlaybackState}
 * @author dasha
 */
public final class TogglePlayPacket implements Packet<PlaybackState> {

    @Override
    public String getData() {
        PlaybackState last = Heartbeat.getLastStatus().getState();
        
        String mode;
        switch(last) {
            case PLAY:
                mode = "1"; //tells the server to pause
                break;
            case PAUSE:
                mode = "0"; //tells the server to resume
                break;
            default: //uh oh
                return "stop";
        }
        
        return "pause " + mode;
    }
    
    @Override
    public PlaybackState mapResponse(List<String> response) {
        return Heartbeat.getLastStatus().getState();
    }
}
