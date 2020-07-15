package me.danny.mpc.gui.components.buttons;

import me.danny.mpc.api.PlaybackState;
import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.api.net.packets.Heartbeat;
import me.danny.mpc.api.net.packets.TogglePlayPacket;

@SuppressWarnings("serial")
public final class TogglePlaybackButton extends CommandButton {

    private static String getIcon(PlaybackState state) {
        switch(state) {
            case PLAY:
                return "| |";
            case PAUSE:
                return "▶";
            default:
                return "Stopped";
        }
    }
    
    public TogglePlaybackButton() {
        super(getIcon(Heartbeat.getLastStatus().getState()));
        
        setToolTipText("Toggle playback state");
    }

    @Override
    protected void onClick() {
        ConnectionManager.sendPacket(new TogglePlayPacket());
        redraw();
    }

    @Override
    protected void redraw() {
        setText(getIcon(Heartbeat.getLastStatus().getState().inverse()));
    }
}