package me.danny.mpc.api.net;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class ConnectionManager {

    private static final String HEADER = "OK MPD";
    private boolean connected = false;
    private Socket socket;
    private Scanner scanner;
    private DataOutputStream dos;
    
    public ConnectionManager(String address, int port) {
        connectTo(address, port);
    }
    
    public void connectTo(String address, int port) {
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
    
    public boolean isConnected() {
        return connected;
    }
    
    public List<String> sendPacket(Packet packet) {
        if(!isConnected()) throw new IllegalStateException("Not connected to an MPD session!!!");
        
        try {
            dos.write(packet.getData().getBytes());
        } catch(IOException ex) { 
            System.err.println("Failed to write packet " + packet.getData());
            return new ArrayList<>();
        }
    
        List<String> response = new ArrayList<>();
        String line = "";
        while(scanner.hasNext()) {
            line = scanner.nextLine();
            System.out.println(line);
            if(line.equals("OK")) break;
            response.add(line);
        }
        
        return response;
    }
}
