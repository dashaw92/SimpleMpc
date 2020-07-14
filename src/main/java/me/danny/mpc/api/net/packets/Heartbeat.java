package me.danny.mpc.api.net.packets;

import java.util.List;

import me.danny.mpc.api.PlaybackState;
import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.api.net.packets.Heartbeat.Status.Single;

/**
 * Sends a status packet to the server every 50ms
 * Last status is accessible through {@link #getLastStatus()}
 * @see {@link Status}
 * 
 * @author dasha
 */
public final class Heartbeat implements Runnable {
    
    /**
     * Get the last status response packet data
     * @return The last status response
     */
    public static Status getLastStatus() {
        return instance.lastResponse;
    }
    
    private Heartbeat() {
        new Thread(this, "MpcHeartbeat").start();
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
    
    /**
     * Represents the status response from MPD
     * @author dasha
     */
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

        /**
         * Is the MPD server in repeat mode?
         * @return true if it's repeat mode
         */
        public boolean isRepeat() {
            return repeat;
        }

        /**
         * Is the MPD server in random (shuffle) mode?
         * @return true if it's in random mode
         */
        public boolean isRandom() {
            return random;
        }

        /**
         * Are songs consumed after being played?
         * @return true if it's in consume mode
         */
        public boolean isConsume() {
            return consume;
        }

        /**
         * Get the single mode of the server
         * @return The approriate {@link Single} 
         * @see {@link Single}
         */
        public Single getSingle() {
            return single;
        }

        /**
         * Get the current playlist ID
         * @return The ID of the current playlist
         */
        public int getPlaylist() {
            return playlist;
        }

        /**
         * Get the length of the current playlist
         * @return The length of the current playlist
         */
        public int getPlaylistlength() {
            return playlistlength;
        }

        /**
         * Get the current {@link PlaybackState} of the server
         * @return The state of the server or {@link PlaybackState#STOP} if not connected
         */
        public PlaybackState getState() {
            return state;
        }

        /**
         * Get the position of the current song
         * @return The currently playing song's position in the playlist
         */
        public int getSong() {
            return song;
        }

        /**
         * Get the position of the next song
         * @return The position of the next song in the playlist
         */
        public int getNextSong() {
            return nextsong;
        }

        /**
         * Get the ID of the currently playing song
         * @return The currently playing song's ID
         */
        public int getSongID() {
            return songid;
        }

        /**
         * Get the ID of the next song in the playlist
         * @return The ID of the next song in the playlist
         */
        public int getNextSongID() {
            return nextsongid;
        }

        /**
         * Get the time elapsed in the playback of the current song
         * @return How long the song has been playing for
         */
        public double getElapsed() {
            return elapsed;
        }

        /**
         * Get the bitrate of the current song
         * @return The bitrate of the current song
         */
        public int getBitrate() {
            return bitrate;
        }

        /**
         * Get the total length of the song
         * @return How long the song goes for
         */
        public double getDuration() {
            return duration;
        }

        /**
         * Represents the {@code single} field in the {@link Status} packet
         */
        public static enum Single {

            /**
             * Single mode is off
             */
            FALSE,
            /**
             * Single mode is on
             */
            TRUE,
            /**
             * Single mode turns off after the current song ends
             */
            ONESHOT,
            
        }
        
    }
}
