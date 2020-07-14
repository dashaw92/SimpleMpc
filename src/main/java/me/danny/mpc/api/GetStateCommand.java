package me.danny.mpc.api;

public final class GetStateCommand implements MpcCommand<PlaybackState>{

    @Override
    public PlaybackState perform() {
        return NativeCommand.execute("mpc").map(list -> {
            if(list.stream().anyMatch(line -> line.startsWith("[playing]"))) return PlaybackState.PLAYING;
            else return PlaybackState.PAUSED;
        }).orElse(PlaybackState.ERROR);
    }
    
}
