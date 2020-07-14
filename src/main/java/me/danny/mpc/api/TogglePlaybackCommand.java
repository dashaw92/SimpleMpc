package me.danny.mpc.api;

public final class TogglePlaybackCommand implements MpcCommand<PlaybackState> {

    public PlaybackState perform() {
        return NativeCommand.execute("mpc", "toggle").map(list -> {
            if(list.stream().anyMatch(line -> line.startsWith("[playing]"))) return PlaybackState.PLAYING;
            else return PlaybackState.PAUSED;
        }).orElse(PlaybackState.ERROR);
    }
}
