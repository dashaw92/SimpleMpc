package me.danny.mpc.api.net.packets;

import java.util.List;

public interface Packet<T> {
    
    public String getData();
    public T mapResponse(List<String> response);
    
}
