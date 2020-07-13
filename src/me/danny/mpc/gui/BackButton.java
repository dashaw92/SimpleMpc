package me.danny.mpc.gui;

import me.danny.mpc.api.BackCommand;

@SuppressWarnings("serial")
public final class BackButton extends CommandButton<Void> {

    private final MainMenu menu;
    
    public BackButton(MainMenu menu) {
        super("<<", new BackCommand());
        this.menu = menu;
        
        setToolTipText("Previous song");
    }
    
    @Override
    protected void onClick() {
        getCommand().perform();
        menu.updateTitle();
    }
    
}
