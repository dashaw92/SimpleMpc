package me.danny.mpc.api.net.packets;

import java.util.List;

public final class NextSongPacket implements Packet<Void> {

    @Override
    public String getData() {
        return "next";
    }
    
    @Override
    public Void mapResponse(List<String> response) {
        return null;
    }
    
}
