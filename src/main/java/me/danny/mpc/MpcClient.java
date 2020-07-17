package me.danny.mpc;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.danny.mpc.api.net.ConnectionManager;
import me.danny.mpc.api.net.packets.Heartbeat;

public final class MpcClient extends Application {

    public static void main(String[] args) {
        Arguments.parse(args);
        ConnectionManager.connectTo(Arguments.getHost(), Arguments.getPort());
        Heartbeat.getLastStatus();
        
        launch();
    }

    @SuppressWarnings("exports")
    @Override
    public void start(Stage stage) throws Exception {
        Platform.setImplicitExit(true);
        
        Parent player = FXMLLoader.load(getClass().getResource("/simple.fxml"));
        stage.setTitle("me.danny.mpc.MpcClient");
        stage.setScene(new Scene(player, 600, 400));
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        stage.show();
    }
    
}
