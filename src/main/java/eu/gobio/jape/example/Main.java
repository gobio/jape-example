package eu.gobio.jape.example;

import eu.gobio.jape.Jape;
import eu.gobio.jape.simple.explainer.Collector;
import eu.gobio.jape.tracker.AbstractTracker;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        Jape.install();
        Class.forName("eu.gobio.jape.tracker.RXJavaTracker");
        startBrowser();
        RXJavaExamples examples = new RXJavaExamples();
        for (int i=0;i<2;i++) {
            new RXJavaExamples().sequentialStream();
            Thread.sleep(2000);
            new RXJavaExamples().concurrentStream1();
            Thread.sleep(2000);
            new RXJavaExamples().concurrentStream2();
            Thread.sleep(2000);
            new RXJavaExamples().concurrentStream3();
            Thread.sleep(2000);
            new RXJavaExamples().concurrentStream4();
            Thread.sleep(2000);
        }
        Collector.instance.clear();
        new RXJavaExamples().sequentialStream();
        Thread.sleep(2000);
        new RXJavaExamples().concurrentStream1();
        Thread.sleep(2000);
        new RXJavaExamples().concurrentStream2();
        Thread.sleep(2000);
        new RXJavaExamples().concurrentStream3();
        Thread.sleep(2000);
        new RXJavaExamples().concurrentStream4();
        Thread.sleep(2000);
    }

    private static void startBrowser() {
        CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Waiting for server...");
                Thread.sleep(3000);
                System.out.println("Opening url http://localhost:5005/ ...");
                Desktop.getDesktop().browse(URI.create("http://localhost:5005/"));
                System.out.println("\n\nPress >> ENTER << to exit\n\n");
                System.in.read();
                System.exit(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
