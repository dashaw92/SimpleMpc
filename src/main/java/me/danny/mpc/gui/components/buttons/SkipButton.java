package me.danny.mpc.gui.components.buttons;

import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.api.net.packets.NextSongPacket;

@SuppressWarnings("serial")
public final class SkipButton extends CommandButton {

    public SkipButton() {
        super(">>");
        
        setToolTipText("Next song");
    }
    
    @Override
    protected void onClick() {
        ConnectionManager.sendPacket(new NextSongPacket());
    }
}
