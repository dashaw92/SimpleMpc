package me.danny.mpc.gui;

import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.api.net.packets.PreviousSongPacket;

@SuppressWarnings("serial")
public final class BackButton extends CommandButton {

    private final MainMenu menu;
    
    public BackButton(MainMenu menu) {
        super("<<");
        this.menu = menu;
        
        setToolTipText("Previous song");
    }
    
    @Override
    protected void onClick() {
        ConnectionManager.sendPacket(new PreviousSongPacket());
        menu.updateTitle();
    }
    
}
