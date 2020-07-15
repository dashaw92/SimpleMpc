package me.danny.mpc.gui.components.fields;

import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public final class NamedInput extends JPanel {

    private final JTextField field;
    
    public NamedInput(String label, String input) {
        setLayout(new FlowLayout(FlowLayout.LEFT));
        
        Font font = new Font("serif", Font.PLAIN, 14);
        
        JLabel name = new JLabel(label);
        name.setFont(font);
        add(name);
        
        field = new JTextField();
        field.setColumns(20);
        field.setFont(font);
        field.setText(input == null || input.trim().isEmpty()? "" : input);
        add(field);
    }
    
    public String getInput() {
        return field.getText();
    }
}
