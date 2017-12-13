package example.weisente.top.rxjava.rxjava;



/**
 * Created by san on 2017/12/13.
 */

public abstract class Observable<T> implements ObservableSource<T> {


    public static <T> ObservableSource<T> just(T item){
        return onAssembly(new ObservableJust<T>(item));
    }

    private static <T> ObservableSource<T> onAssembly(ObservableJust<T> source) {
        // 留出来了
        return source;
    }

    protected abstract void subscribeActual(Observer<T> observer);

    @Override
    public void subscribe(Observer<T> observer){
        subscribeActual(observer);
    }
}
