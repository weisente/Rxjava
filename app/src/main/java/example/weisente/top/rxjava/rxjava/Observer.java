package example.weisente.top.rxjava.rxjava;

import io.reactivex.annotations.NonNull;

/**
 * Created by san on 2017/12/13.
 * 给观测者的一系列的操作
 */

public interface Observer<T> {

    void onSubscribe();
    void onNext(@NonNull T item);
    void onError(@NonNull Throwable e); // 一个异常
    void onComplete();

}
