package me.danny.mpc.gui.components.sliders;

import javax.swing.JSlider;

@SuppressWarnings("serial")
public abstract class Scrubber extends JSlider {

    public Scrubber(int min, int max) {
        setMinimum(min);
        setMaximum(max);
        addChangeListener(event -> this.onChange());
    }
    
    protected abstract void onChange();
}
