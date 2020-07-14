package me.danny.mpc.api.net;

public abstract class Packet {

    private final String data;
    
    protected Packet(String data) {
        this.data = data;
    }
    
    public final String getData() {
        return data + "\n";
    }
    
}
