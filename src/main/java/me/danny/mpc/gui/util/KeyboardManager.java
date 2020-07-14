package me.danny.mpc.gui.util;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import me.danny.mpc.gui.ConnectWindow;

public final class KeyboardManager implements KeyEventDispatcher {

    private static final ConnectWindow window = new ConnectWindow();
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent key) {
        if(!key.isControlDown()) return false;
        if(key.getID() != KeyEvent.KEY_PRESSED) return false;
        
        switch(key.getKeyCode()) {
            case KeyEvent.VK_O:
                window.setVisible(true);
                return true;
            case KeyEvent.VK_Q:
                if(!key.isShiftDown()) return false;
                System.exit(0);
                return true;
        }
        
        return false;
    }
    
}
