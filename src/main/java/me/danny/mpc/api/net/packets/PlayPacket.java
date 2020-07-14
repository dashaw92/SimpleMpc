package me.danny.mpc.api.net.packets;

import java.util.List;

import me.danny.mpc.api.PlaybackState;
import me.danny.mpc.api.net.ConnectionManager;

public final class PlayPacket implements Packet<PlaybackState> {

    @Override
    public String getData() {
        return "pause";
    }
    
    @Override
    public PlaybackState mapResponse(List<String> response) {
        PlaybackState state = ConnectionManager.sendPacket(new StatusPacket())
                .map(status -> status.state)
                .orElse(PlaybackState.STOP);
        
        return state;
    }
}
