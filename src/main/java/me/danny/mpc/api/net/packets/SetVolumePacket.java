package me.danny.mpc.api.net.packets;

import java.util.List;

public final class SetVolumePacket implements Packet<Void> {

    private final int volume;
    
    public SetVolumePacket(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
    }
    
    @Override
    public String getData() {
        return "setvol " + volume;
    }
    
    @Override
    public Void mapResponse(List<String> response) {
        return null;
    }
}
