package me.danny.mpc.api;

public final class GetSongCommand implements MpcCommand<String> {

    @Override
    public String perform() {
        return NativeCommand.execute("mpc").map(list -> {
            if(list.isEmpty()) return "###Error!###";
            
            return list.get(0);
        }).orElse("###Error!###");
    }
    
}
