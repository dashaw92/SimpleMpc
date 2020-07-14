package me.danny.mpc.gui;

import me.danny.mpc.api.SkipCommand;

@SuppressWarnings("serial")
public final class SkipButton extends CommandButton<Void> {

    private final MainMenu menu;
    
    public SkipButton(MainMenu menu) {
        super(">>", new SkipCommand());
        this.menu = menu;
        
        setToolTipText("Next song");
    }
    
    @Override
    protected void onClick() {
        getCommand().perform();
        
        menu.updateTitle();
    }
    
}
