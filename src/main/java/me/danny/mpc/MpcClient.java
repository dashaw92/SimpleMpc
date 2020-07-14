package me.danny.mpc;

import java.awt.GraphicsEnvironment;
import java.awt.KeyboardFocusManager;

import javax.swing.UIManager;

import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.gui.PlayerWindow;
import me.danny.mpc.gui.util.KeyboardManager;

public final class MpcClient {

    public static void main(String[] args) {
        if(GraphicsEnvironment.isHeadless()) {
            System.err.println("This program requires a graphics environment to run!!! Just use mpc...");
            return;
        }
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored) {}
      
        Arguments.parse(args);
        ConnectionManager.connectTo(Arguments.getHost(), Arguments.getPort());
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyboardManager());
        PlayerWindow gui = new PlayerWindow();
        gui.setVisible(true);
    }
    
}
