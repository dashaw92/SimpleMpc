package me.danny.mpc.api.net.packets;

import java.util.List;

public final class ListPacket implements Packet<List<String>> {

    @Override
    public String getData() {
        return "list track";
    }

    @Override
    public List<String> mapResponse(List<String> response) {
        return response; //TODO
    }
}
