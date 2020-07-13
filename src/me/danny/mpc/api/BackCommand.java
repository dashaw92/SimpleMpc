package me.danny.mpc.api;

public final class BackCommand implements MpcCommand<Void> {

    @Override
    public Void perform() {
        NativeCommand.execute("mpc", "prev");
        return null;
    }
    
}
