package me.danny.mpc.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import me.danny.mpc.api.CurrentSong;
import me.danny.mpc.api.Status;
import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.api.net.packets.CurrentSongPacket;
import me.danny.mpc.api.net.packets.StatusPacket;
import me.danny.mpc.gui.components.BackButton;
import me.danny.mpc.gui.components.SkipButton;
import me.danny.mpc.gui.components.TogglePlaybackButton;

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
        controls.add(new BackButton());
        controls.add(new TogglePlaybackButton());
        controls.add(new SkipButton());

        add(controls);
        pack();
        setLocationRelativeTo(null);
        
        new Thread(this).start();
    }
    
    private void updateTitle() {
        String fmt = "(%s) - [%s - %s]";
        
        Status status = ConnectionManager.sendPacket(new StatusPacket()).orElse(new Status());
        CurrentSong song = ConnectionManager.sendPacket(new CurrentSongPacket()).orElse(new CurrentSong());
        
        setTitle(String.format(fmt, status.state.name(), song.artist, song.title));
    }
    
    @Override
    public void run() {
        while(true) {
            SwingUtilities.invokeLater(this::updateTitle);
            try {
                Thread.sleep(100L);
            } catch(InterruptedException ex) {}
        }
    }

}
