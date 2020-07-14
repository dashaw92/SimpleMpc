package me.danny.mpc.gui;

import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.api.net.packets.NextSongPacket;

@SuppressWarnings("serial")
public final class SkipButton extends CommandButton {

    private final MainMenu menu;
    
    public SkipButton(MainMenu menu) {
        super(">>");
        this.menu = menu;
        
        setToolTipText("Next song");
    }
    
    @Override
    protected void onClick() {
        ConnectionManager.sendPacket(new NextSongPacket());
        menu.updateTitle();
    }
    
}
