package me.danny.mpc.gui.components;

import me.danny.mpc.api.PlaybackState;
import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.api.net.packets.PlayPacket;
import me.danny.mpc.api.net.packets.StatusPacket;

@SuppressWarnings("serial")
public final class TogglePlaybackButton extends CommandButton {

    private static String getIcon(PlaybackState state) {
        switch(state) {
            case PLAY:
                return "| |";
            case PAUSE:
                return "▶";
            default:
            case STOP:
                return "Stopped";
        }
    }
    
    public TogglePlaybackButton() {
        super(
                getIcon(
                    ConnectionManager.sendPacket(new StatusPacket())
                        .map(state -> state.state)
                        .orElse(PlaybackState.STOP)
                )
        );
        
        setToolTipText("Toggle playback state");
    }

    @Override
    protected void onClick() {
        PlaybackState state = ConnectionManager.sendPacket(new PlayPacket())
                .orElse(PlaybackState.STOP);
        setText(getIcon(state));
    }

}
