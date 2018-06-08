package eu.gobio.jape.example;

import eu.gobio.jape.Track;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class AsyncComputation {

    @Track
    public void computeAsync() {
        fetchData();

        makeComputation();

        notifyListeners();
    }

    @Track
    private void fetchData() {
        beBusy();
    }

    @Track
    private void makeComputation() {
        prepareComputingStream().observeOn(Schedulers.computation()).reduce(this::reduction).blockingGet();
    }

    @Track
    private void notifyListeners() {
        beBusy();
        prepareStream().observeOn(Schedulers.io()).subscribe(this::asyncNotification);
    }

    private void beBusy() {
        long duration = (long) (Math.random() * 200) + 600;
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < duration) ;
    }

    private Observable<Double> prepareComputingStream() {
        return prepareStream().map(this::expensiveComputation);
    }

    private Observable<Double> prepareStream() {
        return Observable.fromCallable(Math::random).repeat(4);
    }

    @Track
    private void asyncNotification(Double aDouble) {
        beBusy();
    }

    @Track
    private double reduction(double x, double y) {
        beBusy();
        return x + y;
    }

    @Track
    private double expensiveComputation(double x) {
        beBusy();
        return 2 * x;
    }

}