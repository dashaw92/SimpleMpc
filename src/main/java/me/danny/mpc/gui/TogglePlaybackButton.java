package me.danny.mpc.gui;

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
    
    private final MainMenu menu;
    
    public TogglePlaybackButton(MainMenu menu) {
        super(
                getIcon(
                    ConnectionManager.sendPacket(new StatusPacket())
                        .map(state -> state.state)
                        .orElse(PlaybackState.STOP)
                )
        );
        this.menu = menu;
        
        setToolTipText("Toggle playback state");
    }

    @Override
    protected void onClick() {
        PlaybackState state = ConnectionManager.sendPacket(new PlayPacket())
                .orElse(PlaybackState.STOP);
        setText(getIcon(state));
        
        menu.updateTitle();
    }

}
