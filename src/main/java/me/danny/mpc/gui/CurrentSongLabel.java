package me.danny.mpc.gui;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import me.danny.mpc.api.GetSongCommand;
import me.danny.mpc.api.MpcCommand;

@SuppressWarnings("serial")
public final class CurrentSongLabel extends JLabel implements Runnable {

    public static CurrentSongLabel start(MainMenu menu) {
        CurrentSongLabel label = new CurrentSongLabel(menu);
        new Thread(label).start();
        return label;
    }
    
    private final MainMenu menu;
    private final MpcCommand<String> querySong = new GetSongCommand();
    
    private CurrentSongLabel(MainMenu menu) {
        this.menu = menu;
        updateText();
    }
    
    @Override
    public void run() {
        while(true) {
            updateText();
            try {
                Thread.sleep(1000L);
            } catch(InterruptedException ex) {}
        }
    }
    
    private void updateText() {
        String playing = querySong.perform();
        
        SwingUtilities.invokeLater(() -> {
            setText(playing);
            menu.updateTitle();
        });
    }
}
