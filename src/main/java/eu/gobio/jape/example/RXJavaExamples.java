package eu.gobio.jape.example;

import eu.gobio.jape.Track;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class RXJavaExamples {
    @Track
    public void sequentialStream() {
        Observable.range(0,5).map(this::map).filter(this::filter).scan(this::sum).subscribe(this::subscribe);
    }

    @Track
    public void concurrentStream1() {
        Observable.range(0,5)
                  .observeOn(Schedulers.computation())
                  .map(this::map)
                  .filter(this::filter)
                  .scan(this::sum)
                  .subscribe(this::subscribe);
    }


    @Track
    public void concurrentStream2() {
        Observable.range(0,5)
                  .observeOn(Schedulers.computation())
                  .map(this::map)
                  .observeOn(Schedulers.computation())
                  .filter(this::filter)
                  .scan(this::sum)
                  .subscribe(this::subscribe);
    }

    @Track
    public void concurrentStream3() {
        Observable.range(0,5)
                  .observeOn(Schedulers.computation())
                  .map(this::map)
                  .observeOn(Schedulers.computation())
                  .filter(this::filter)
                  .observeOn(Schedulers.computation())
                  .scan(this::sum)
                  .subscribe(this::subscribe);
    }

    @Track
    public void concurrentStream4() {
        Observable.range(0,5)
                  .observeOn(Schedulers.computation())
                  .map(this::map)
                  .observeOn(Schedulers.computation())
                  .filter(this::filter)
                  .observeOn(Schedulers.computation())
                  .scan(this::sum)
                  .observeOn(Schedulers.computation())
                  .subscribe(this::subscribe);
    }


    @Track
    private int sum(int prev, int current) {
        delay();
        return prev + current;

    }


    @Track
    private int map(int value) {
        delay();
        return value + 1;
    }

    @Track
    private boolean filter(int value) {
        delay();
        return true;
    }

    @Track
    private void subscribe(int value){
        delay();
    }

    private void delay() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignore) {
        }
    }
}
