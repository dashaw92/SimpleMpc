package me.danny.mpc.gui;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import me.danny.mpc.api.GetSongCommand;
import me.danny.mpc.api.GetStateCommand;
import me.danny.mpc.api.MpcCommand;
import me.danny.mpc.api.PlaybackState;

@SuppressWarnings("serial")
public final class MainMenu extends JFrame {

    private static final MpcCommand<PlaybackState> queryState = new GetStateCommand();
    private static final MpcCommand<String> querySong = new GetSongCommand();
    
    public MainMenu() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(Exception ignored) {}
        
        setPreferredSize(new Dimension(800, 200));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(true);
        setLayout(new GridLayout(2, 1));
        
        JPanel labels = new JPanel();
        labels.add(CurrentSongLabel.start(this));
        
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(1, 3));
        controls.add(new BackButton(this));
        controls.add(new TogglePlaybackButton(this));
        controls.add(new SkipButton(this));

        add(labels);
        add(controls);
        pack();
        updateTitle();
        setLocationRelativeTo(null);
    }
    
    protected void updateTitle() {
        String fmt = "(%s) - [%s]";
        
        PlaybackState state = queryState.perform();
        String song = querySong.perform();
        
        setTitle(String.format(fmt, state.name(), song));
    }
}
