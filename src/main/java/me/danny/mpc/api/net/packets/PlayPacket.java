package me.danny.mpc.api.net.packets;

import java.util.List;

import me.danny.mpc.api.PlaybackState;

public final class PlayPacket implements Packet<PlaybackState> {

    @Override
    public String getData() {
        return "pause";
    }
    
    @Override
    public PlaybackState mapResponse(List<String> response) {
        return Heartbeat.getLastStatus().getState();
    }
}
