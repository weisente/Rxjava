package example.weisente.top.rxjava.rxjava;

/**
 * Created by san on 2017/12/13.
 */

public interface Consumer<T> {
    void onNext(T item) throws Exception;
}
