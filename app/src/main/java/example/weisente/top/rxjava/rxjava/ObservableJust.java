package example.weisente.top.rxjava.rxjava;

/**
 * Created by san on 2017/12/13.
 */

public class ObservableJust<T> extends Observable<T> {

    private T item;

    public ObservableJust(T item) {
        this.item = item;
    }


    @Override
    protected void subscribeActual(Observer<T> observer) {
        ScalarDisposable sd = new ScalarDisposable(observer,item);
        observer.onSubscribe();//第一步
        sd.run();
    }

    private class ScalarDisposable<T>{
        private Observer observer;
        private T item;

        public ScalarDisposable(Observer<T> observer, T item) {
            this.observer = observer;
            this.item = item;
        }

        public void run(){
            try {
                observer.onNext(item);
                observer.onComplete();
            }catch (Exception e){
                observer.onError(e);
            }
        }
    }
}
