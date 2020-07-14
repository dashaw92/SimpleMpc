package me.danny.mpc.api.net.packets;

import java.util.List;

import me.danny.mpc.api.PlaybackState;
import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.api.net.packets.Heartbeat.Status.Single;

public final class Heartbeat implements Runnable {
    
    public static Status getLastStatus() {
        return instance.lastResponse;
    }
    
    private Heartbeat() {
        new Thread(this).start();
    }
    
    private static final Heartbeat instance = new Heartbeat();
    private Status lastResponse = new Status();
    
    public void run() {
        while(true) {
            lastResponse = ConnectionManager.sendPacket(new StatusPacket()).orElse(new Status());
            try {
                Thread.sleep(50L);
            } catch(InterruptedException ex) {}
        }
    }
    
    private static final class StatusPacket implements Packet<Status> {
        
        @Override
        public String getData() {
            return "status";
        }
        
        @Override
        public Status mapResponse(List<String> response) {
            Status status = new Status();
            for(String line : response) {
                String[] fields = line.split(": ", 2);
                if(fields.length < 2) continue;
                switch(fields[0].toLowerCase()) {
                    case "repeat":
                        status.setRepeat(fields[1].equals("1"));
                        break;
                    case "random":
                        status.setRandom(fields[1].equals("1"));
                        break;
                    case "single":
                        Single single = Single.FALSE;
                        if(fields[1].equals("1")) single = Single.TRUE;
                        if(fields[1].equals("oneshot")) single = Single.ONESHOT;
                        status.setSingle(single);
                        break;
                    case "consume":
                        status.setConsume(fields[1].equals("1"));
                        break;
                    case "playlist":
                        status.setPlaylist(Integer.parseInt(fields[1]));
                        break;
                    case "playlistlength":
                        status.setPlaylistlength(Integer.parseInt(fields[1]));
                        break;
                    case "state":
                        status.setState(PlaybackState.valueOf(fields[1].toUpperCase()));
                        break;
                    case "song":
                        status.setSong(Integer.parseInt(fields[1]));
                        break;
                    case "nextsong":
                        status.setNextSong(Integer.parseInt(fields[1]));
                        break;
                    case "songid":
                        status.setSongID(Integer.parseInt(fields[1]));
                        break;
                    case "nextsongid":
                        status.setNextSongID(Integer.parseInt(fields[1]));
                        break;
                    case "elapsed":
                        status.setElapsed(Double.parseDouble(fields[1]));
                        break;
                    case "bitrate":
                        status.setBitrate(Integer.parseInt(fields[1]));
                        break;
                    case "duration":
                        status.setDuration(Double.parseDouble(fields[1]));
                        break;
                }
            }
            
            return status;
        }
    }
    
    public static final class Status {

        private boolean repeat = false, random = false, consume = false;
        private  Single single = Single.FALSE;
        private  int playlist = -1, playlistlength = 0;
        private PlaybackState state = PlaybackState.STOP;
        private int song = -1, nextsong = -1;
        private int songid = -1, nextsongid = -1;
        private double elapsed = -1d;
        private int bitrate = 0x0;
        private double duration = -1d;
        
        private void setRepeat(boolean repeat) {
            this.repeat = repeat;
        }

        private void setRandom(boolean random) {
            this.random = random;
        }

        private void setConsume(boolean consume) {
            this.consume = consume;
        }

        private void setSingle(Single single) {
            this.single = single;
        }

        private void setPlaylist(int playlist) {
            this.playlist = playlist;
        }

        private void setPlaylistlength(int playlistlength) {
            this.playlistlength = playlistlength;
        }

        private void setState(PlaybackState state) {
            this.state = state;
        }

        private void setSong(int song) {
            this.song = song;
        }

        private void setNextSong(int nextsong) {
            this.nextsong = nextsong;
        }

        private void setSongID(int songid) {
            this.songid = songid;
        }

        private void setNextSongID(int nextsongid) {
            this.nextsongid = nextsongid;
        }

        private void setElapsed(double elapsed) {
            this.elapsed = elapsed;
        }

        private void setBitrate(int bitrate) {
            this.bitrate = bitrate;
        }

        private void setDuration(double duration) {
            this.duration = duration;
        }

        public boolean isRepeat() {
            return repeat;
        }

        public boolean isRandom() {
            return random;
        }

        public boolean isConsume() {
            return consume;
        }

        public Single getSingle() {
            return single;
        }

        public int getPlaylist() {
            return playlist;
        }

        public int getPlaylistlength() {
            return playlistlength;
        }

        public PlaybackState getState() {
            return state;
        }

        public int getSong() {
            return song;
        }

        public int getNextsong() {
            return nextsong;
        }

        public int getSongid() {
            return songid;
        }

        public int getNextsongid() {
            return nextsongid;
        }

        public double getElapsed() {
            return elapsed;
        }

        public int getBitrate() {
            return bitrate;
        }

        public double getDuration() {
            return duration;
        }

        public static enum Single {

            TRUE,
            FALSE,
            ONESHOT,
            
        }
        
    }
}
