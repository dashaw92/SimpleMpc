package me.danny.mpc.api.net.packets;

import java.util.List;

/**
 * Represents a packet that may be sent to the MPD server
 * @author dasha
 * @param <T> The data the server sends back, mapped by the packet
 */
public interface Packet<T> {
    
    /**
     * @return The command this packet represents 
     */
    public String getData();
    /**
     * Take the response from the server and transform it
     * @param response The raw response text from the MPD server
     * @return The transformed data for use in the API
     */
    public T mapResponse(List<String> response);
    
}
