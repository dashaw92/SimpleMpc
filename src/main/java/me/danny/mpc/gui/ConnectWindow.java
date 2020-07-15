package me.danny.mpc.gui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.gui.components.buttons.CommandButton;
import me.danny.mpc.gui.components.fields.NamedInput;

@SuppressWarnings("serial")
public final class ConnectWindow extends JFrame {

    private final NamedInput hostInput = new NamedInput("Host Address: ", ConnectionManager.getCurrentHost());
    private final NamedInput portInput = new NamedInput("Port: ", "" + ConnectionManager.getCurrentPort());
    
    public ConnectWindow() {
        setTitle("New Connection");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setLayout(new GridLayout(3, 1, 0, 10));
        
        add(hostInput);
        add(portInput);
        add(new ConnectButton(this));
        
        setLocationRelativeTo(null);
    }
    
    private void showError(String msg) {
        JOptionPane.showConfirmDialog(this, msg, "Error connecting", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
    }
    
    private final class ConnectButton extends CommandButton {
        
        private final JFrame parent;
        
        public ConnectButton(JFrame parent) {
            super("Connect");
            this.parent = parent;
        }
        
        @Override
        protected void onClick() {
            String host = hostInput.getInput().trim();
            String portStr = portInput.getInput().trim();
            
            int port;
            try {
                port = Integer.parseInt(portStr);
            } catch(Exception ignored) { 
                showError("Invalid port: " + portStr);
                return;
            }
            
            ConnectionManager.connectTo(host, port);
            
            if(!ConnectionManager.isConnected()) {
                showError("Could not connect to " + host + ":" + port);
                return;
            }
            
            parent.dispose();
        }

        @Override
        public void redraw() {}
    }
}