package me.danny.mpc.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public final class NativeCommand {

    protected static Optional<List<String>> execute(String... commands) {
        List<String> out = new ArrayList<>();
        try {
            Process cmd = new ProcessBuilder(commands).start();
            Scanner scanner = new Scanner(cmd.getInputStream());
            scanner.useDelimiter("\n");
            scanner.forEachRemaining(out::add);
            scanner.close();
            cmd.destroyForcibly();
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            return Optional.empty();
        }
        
        return Optional.of(out);
    }
    
}
