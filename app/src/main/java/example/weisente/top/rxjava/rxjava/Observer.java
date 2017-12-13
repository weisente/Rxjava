package example.weisente.top.rxjava.rxjava;

import android.support.annotation.NonNull;

/**
 * Created by san on 2017/12/13.
 */

public interface Observer<T> {
    void onError(@NonNull Throwable e);
    void onSubscribe();
    void onComplete();
    void onNext(@NonNull T item);
}
