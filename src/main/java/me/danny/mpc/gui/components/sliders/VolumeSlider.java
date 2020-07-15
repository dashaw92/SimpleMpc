package me.danny.mpc.gui.components.sliders;

import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.api.net.packets.Heartbeat;
import me.danny.mpc.api.net.packets.SetVolumePacket;

@SuppressWarnings("serial")
public final class VolumeSlider extends Scrubber {

    public VolumeSlider() {
        super(0, 100);
        setValue(Heartbeat.getLastStatus().getVolume());
    }

    @Override
    protected void onChange() {
        int newVol = getValue();
        ConnectionManager.sendPacket(new SetVolumePacket(newVol));
    }
    
}
