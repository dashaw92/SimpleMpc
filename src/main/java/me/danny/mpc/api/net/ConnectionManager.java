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

/**
 * A singleton connection to an MPD server<br>
 * Useful methods in this class:<br>
 * <ul>
 * <li> {@link #isConnected()}<br>
 * <li> {@link #connectTo(String, int)}<br>
 * <li> {@link #sendPacket(Packet)}<br>
 * </ul>
 * @see {@link Packet}
 * @author dasha
 */
public final class ConnectionManager {
    
    private ConnectionManager() {}

    private static final String HEADER = "OK MPD";    
    private static boolean connected = false;
    private static Socket socket;
    private static Scanner scanner;
    private static DataOutputStream dos;
    
    private static String currentHost;
    private static int currentPort;
    
    /**
     * Connect to an arbitrary host.<br>
     * If the host is invalid, or the connection is not to an MPD server,<br>
     * {@link #isConnected()} will return false
     * @param address The address of the MPD server
     * @param port The port of the MPD server
     */
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
            
            currentHost = address;
            currentPort = port;
            connected = true;
        } catch(IOException ex) {} //ignore, `connected` manages status reporting
    }
    
    /**
     * @return The address provided to the last successful {@link #connectTo(String, int)}
     */
    public static String getCurrentHost() {
        return currentHost;
    }
    
    /**
     * @return The port provided to the last successful {@link #connectTo(String, int)}
     */
    public static int getCurrentPort() {
        return currentPort;
    }
    
    /**
     * Used to check the status of the connection
     * @return True if this is connected to an MPD server
     */
    public static boolean isConnected() {
        return connected;
    }
    
    /**
     * Send a {@link Packet} to the connected server
     * @param <T> The type of data the packet will map the server's response to
     * @param packet The packet instance to send
     * @return The mapped response wrapped in {@link Optional}, or {@link Optional#empty()}
     */
    public synchronized static <T> Optional<T> sendPacket(Packet<T> packet) {
        Objects.requireNonNull(packet, "Packet cannot be null");
        if(!isConnected()) return Optional.empty();
        
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
