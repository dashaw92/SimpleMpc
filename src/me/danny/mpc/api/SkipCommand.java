package me.danny.mpc.api;

public final class SkipCommand implements MpcCommand<Void> {

    @Override
    public Void perform() {
        NativeCommand.execute("mpc", "next");
        return null;
    }
    
}
