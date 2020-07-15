package me.danny.mpc.gui.components.buttons;

import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public abstract class CommandButton extends JButton  {
    
    public CommandButton(String name) {
        setFont(new Font("serif", Font.PLAIN, 18));
        setText(name);
        addActionListener((event) -> onClick());
    }
    
    protected abstract void onClick();
    protected void redraw() {}
}
