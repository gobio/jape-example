package eu.gobio.jape.example;

import eu.gobio.jape.Jape;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) throws InterruptedException, ClassNotFoundException, IOException {
        Jape.install();
        startBrowser();
        AsyncComputation asyncComputation = new AsyncComputation();
        SyncComputation syncComputation = new SyncComputation();
        for (int i = 0; i < 5; i++) {
            syncComputation.compute();
            asyncComputation.computeAsync();
        }
    }

    private static void startBrowser() {
        CompletableFuture.runAsync(()-> {
            try {
                System.out.println("Waiting for server...");
                Thread.sleep(3000);
                System.out.println("Starting browser (http://localhost:5005/)");
                Desktop.getDesktop().browse(URI.create("http://localhost:5005/"));
            } catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}