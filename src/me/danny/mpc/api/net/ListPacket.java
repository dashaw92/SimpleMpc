package me.danny.mpc.api.net;

public final class ListPacket extends Packet {

    public ListPacket(Tag tag) {
        super("list " + tag.name());
    }
}
