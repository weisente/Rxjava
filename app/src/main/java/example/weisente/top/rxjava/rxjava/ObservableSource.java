package example.weisente.top.rxjava.rxjava;

/**
 * Created by san on 2017/12/13.
 */

public interface ObservableSource<T> {
    void subscribe(Observer<T> observer);
}
