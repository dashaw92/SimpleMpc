package me.danny.mpc.gui;

import me.danny.mpc.api.GetStateCommand;
import me.danny.mpc.api.PlaybackState;
import me.danny.mpc.api.TogglePlaybackCommand;

@SuppressWarnings("serial")
public final class TogglePlaybackButton extends CommandButton<PlaybackState> {

    private static String getIcon(PlaybackState state) {
        switch(state) {
            case PLAYING:
                return "||";
            case PAUSED:
                return "▶";
            default:
            case ERROR:
                return "ERROR";
        }
    }
    
    private final MainMenu menu;
    
    public TogglePlaybackButton(MainMenu menu) {
        super(getIcon(new GetStateCommand().perform()), new TogglePlaybackCommand());
        this.menu = menu;
        
        setToolTipText("Toggle playback state");
    }

    @Override
    protected void onClick() {
        PlaybackState state = getCommand().perform();
        setText(getIcon(state));
        
        menu.updateTitle();
    }

}
