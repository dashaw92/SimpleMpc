package me.danny.mpc.gui;

import java.awt.Font;

import javax.swing.JButton;

import me.danny.mpc.api.MpcCommand;

@SuppressWarnings("serial")
public abstract class CommandButton<T> extends JButton {

    private final MpcCommand<T> cmd;
    
    public CommandButton(String name, MpcCommand<T> cmd) {
        setFont(new Font("serif", Font.PLAIN, 18));
        setText(name);
        addActionListener((event) -> onClick());
        this.cmd = cmd;
    }
    
    protected final MpcCommand<T> getCommand() {
        return cmd;
    }
    
    protected abstract void onClick();
}
