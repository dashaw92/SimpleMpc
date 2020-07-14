package me.danny.mpc.api.net.packets;

import java.util.List;

/**
 * Tell the server to go back and play the previous song in the playlist
 * @author dasha
 */
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
