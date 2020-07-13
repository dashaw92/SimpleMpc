package me.danny.mpc.api;

public interface MpcCommand<T> {

    public T perform();
    
}
