package me.danny.mpc.api.net.packets;

import java.util.List;

public final class PreviousSongPacket implements Packet<Void> {

    @Override
    public String getData() {
        return "previous";
    }
    
    @Override
    public Void mapResponse(List<String> response) {
        return null;
    }
    
}
