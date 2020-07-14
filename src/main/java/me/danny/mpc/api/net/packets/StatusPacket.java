package me.danny.mpc.api.net.packets;

import java.util.List;

import me.danny.mpc.api.PlaybackState;
import me.danny.mpc.api.Status;
import me.danny.mpc.api.Status.Single;

public final class StatusPacket implements Packet<Status> {

    @Override
    public String getData() {
        return "status";
    }
    
    //TODO
    @Override
    public Status mapResponse(List<String> response) {
        Status status = new Status();
        for(String line : response) {
            String[] fields = line.split(": ", 2);
            if(fields.length < 2) continue;
            switch(fields[0].toLowerCase()) {
                case "repeat":
                    status.repeat = fields[1].equals("1");
                    break;
                case "random":
                    status.random = fields[1].equals("1");
                    break;
                case "single":
                    if(fields[1].equals("0")) status.single = Single.FALSE;
                    if(fields[1].equals("1")) status.single = Single.TRUE;
                    if(fields[1].equals("oneshot")) status.single = Single.ONESHOT;
                    break;
                case "consume":
                    status.consume = fields[1].equals("1");
                    break;
                case "playlist":
                    status.playlist = Integer.parseInt(fields[1]);
                    break;
                case "playlistlength":
                    status.playlistlength = Integer.parseInt(fields[1]);
                    break;
                case "state":
                    status.state = PlaybackState.valueOf(fields[1].toUpperCase());
                    break;
                case "song":
                    status.song = Integer.parseInt(fields[1]);
                    break;
                case "nextsong":
                    status.nextsong = Integer.parseInt(fields[1]);
                    break;
                case "songid":
                    status.songid = Integer.parseInt(fields[1]);
                    break;
                case "nextsongid":
                    status.nextsongid = Integer.parseInt(fields[1]);
                    break;
                case "elapsed":
                    status.elapsed = Double.parseDouble(fields[1]);
                    break;
                case "bitrate":
                    status.bitrate = Integer.parseInt(fields[1]);
                    break;
                case "duration":
                    status.duration = Double.parseDouble(fields[1]);
                    break;
            }
        }
        
        return status;
    }
}
