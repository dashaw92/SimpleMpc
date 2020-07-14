package me.danny.mpc.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import me.danny.mpc.api.CurrentSong;
import me.danny.mpc.api.PlaybackState;
import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.api.net.packets.CurrentSongPacket;
import me.danny.mpc.api.net.packets.StatusPacket;

@SuppressWarnings("serial")
public final class MainMenu extends JFrame implements Runnable {
    
    public MainMenu() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored) {}
        
        setPreferredSize(new Dimension(800, 200));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(1, 3));
        controls.add(new BackButton(this));
        controls.add(new TogglePlaybackButton(this));
        controls.add(new SkipButton(this));

        add(controls);
        pack();
        updateTitle();
        setLocationRelativeTo(null);
        
        new Thread(this).start();
    }
    
    protected void updateTitle() {
        String fmt = "(%s) - [%s - %s]";
        
        PlaybackState state = ConnectionManager.sendPacket(new StatusPacket()).map(status -> status.state).orElse(PlaybackState.STOP);
        CurrentSong song = ConnectionManager.sendPacket(new CurrentSongPacket()).orElse(new CurrentSong());
        
        setTitle(String.format(fmt, state.name(), song.artist, song.title));
    }
    
    @Override
    public void run() {
        while(true) {
            SwingUtilities.invokeLater(this::updateTitle);
            try {
                Thread.sleep(1000L);
            } catch(InterruptedException ex) {}
        }
    }

}
