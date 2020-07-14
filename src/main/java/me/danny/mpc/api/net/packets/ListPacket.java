package me.danny.mpc.api.net.packets;

import java.util.List;
import java.util.Objects;

import me.danny.mpc.api.Tag;

public final class ListPacket implements Packet<List<String>> {

    private final Tag tag;
    public ListPacket(Tag tag) {
        Objects.requireNonNull(tag, "Tag cannot be null");
        this.tag = tag;
    }
    
    @Override
    public String getData() {
        return "list " + tag.name().replace("_", "").toLowerCase();
    }

    @Override
    public List<String> mapResponse(List<String> response) {
        return response; //TODO
    }
}
