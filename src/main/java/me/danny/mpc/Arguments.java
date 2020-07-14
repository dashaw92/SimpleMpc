package me.danny.mpc;

import com.sanityinc.jargs.CmdLineParser;
import com.sanityinc.jargs.CmdLineParser.Option;
import com.sanityinc.jargs.CmdLineParser.OptionException;

public final class Arguments {
    
    private static final CmdLineParser parser = new CmdLineParser();
    private static final Options out = new Options();
    
    public static void parse(String[] args) {
        Option<String> host = parser.addStringOption('h', "host");
        Option<Integer> port = parser.addIntegerOption('p', "port");
        
        try {
            parser.parse(args);
        } catch (OptionException ex) {
            out.host = "localhost";
            out.port = 6600;
            return;
        }
        
        out.host = parser.getOptionValue(host, "localhost");
        out.port = parser.getOptionValue(port, 6600);
    }
    
    public static String getHost() {
        return out.host;
    }
    
    public static int getPort() {
        return out.port;
    }
    
    private static final class Options {
        private String host;
        private int port;
    }
}
