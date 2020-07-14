package me.danny.mpc;

public final class MpcClient {

    public static void main(String[] args) {
//        if(GraphicsEnvironment.isHeadless()) {
//            System.err.println("This program requires a graphics environment to run!!! Just use mpc...");
//            return;
//        }
//        
//        if(!(new CheckMpcCommand().perform())) {
//            System.err.println("mpc was not found on the path! Please fix this, and then try again.");
//            return;
//        }
//        
//        
//        MainMenu gui = new MainMenu();
//        gui.setVisible(true);
        
//        ConnectionManager mgr = new ConnectionManager("localhost", 6600);
        Arguments.parse(args);
        System.out.println(Arguments.getHost());
        System.out.println(Arguments.getPort());
    }
    
}
