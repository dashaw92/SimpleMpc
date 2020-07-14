package me.danny.mpc.api.net.packets;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import me.danny.mpc.api.Tag;

/**
 * Query the server about the database of loaded songs with a {@link Tag}
 */
public final class ListPacket implements Packet<List<String>> {

    private final Tag tag;
    public ListPacket(Tag tag) {
        Objects.requireNonNull(tag, "Tag cannot be null");
        this.tag = tag;
    }
    
    @Override
    public String getData() {
        return "list " + toFmt(tag);
    }

    @Override
    public List<String> mapResponse(List<String> response) {
        return response.stream()
                .map(line -> line.substring(toFmt(tag).length() + 2))
                .filter(line -> !line.trim().isEmpty())
                .collect(Collectors.toList());
    }
    
    private static String toFmt(Tag tag) {
        return tag.name().replace("_", "").toLowerCase();
    }
}
