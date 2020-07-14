package me.danny.mpc;

import java.awt.GraphicsEnvironment;

import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.gui.MainMenu;

public final class MpcClient {

    public static void main(String[] args) {
        if(GraphicsEnvironment.isHeadless()) {
            System.err.println("This program requires a graphics environment to run!!! Just use mpc...");
            return;
        }  
      
        Arguments.parse(args);
        ConnectionManager.connectTo(Arguments.getHost(), Arguments.getPort());
        MainMenu gui = new MainMenu();
        gui.setVisible(true);
    }
    
}
