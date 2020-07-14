package me.danny.mpc.api.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

import me.danny.mpc.api.net.packets.Packet;

public final class ConnectionManager {
    
    private ConnectionManager() {}

    private static final String HEADER = "OK MPD";    
    private static boolean connected = false;
    private static Socket socket;
    private static Scanner scanner;
    private static DataOutputStream dos;
    
    public static void connectTo(String address, int port) {
        try {
            if(isConnected() && socket != null) {
                socket.close();
                connected = false;
            }
            
            socket = new Socket(address, port);
            dos = new DataOutputStream(socket.getOutputStream());
            scanner = new Scanner(socket.getInputStream());
            scanner.useDelimiter("\n");
            
            String response = scanner.nextLine();
            if(!response.startsWith(HEADER)) return;
            connected = true;
        } catch(IOException ex) {} //ignore, `connected` manages status reporting
    }
    
    public static boolean isConnected() {
        return connected;
    }
    
    public static <T> Optional<T> sendPacket(Packet<T> packet) {
        Objects.requireNonNull(packet, "Packet cannot be null");
        if(!isConnected()) throw new IllegalStateException("Not connected to an MPD session!!!");
        
        try {
            String cmd = packet.getData() + "\n";
            dos.write(cmd.getBytes());
        } catch(IOException ex) { 
            System.err.println("Failed to write packet " + packet.getData());
            return Optional.empty();
        }
    
        List<String> response = new ArrayList<>();
        String line = "";
        while(scanner.hasNext()) {
            line = scanner.nextLine();
            if(line.equals("OK")) break;
            response.add(line);
        }
        
        return Optional.ofNullable(packet.mapResponse(response));
    }
}
