package me.danny.mpc.api;

public final class CheckMpcCommand implements MpcCommand<Boolean> {

    @Override
    public Boolean perform() {
        try {
            if(NativeCommand.execute("mpc").isPresent()) return true;
        } catch(Exception e) {}
        return false;
    }
    
}
