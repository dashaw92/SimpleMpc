package me.danny.mpc.gui.components.buttons;

import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.api.net.packets.PreviousSongPacket;

@SuppressWarnings("serial")
public final class BackButton extends CommandButton {

    public BackButton() {
        super("<<");
        
        setToolTipText("Previous song");
    }
    
    @Override
    protected void onClick() {
        ConnectionManager.sendPacket(new PreviousSongPacket());
    }
}
