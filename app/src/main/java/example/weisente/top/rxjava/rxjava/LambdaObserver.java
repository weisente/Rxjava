package example.weisente.top.rxjava.rxjava;

import io.reactivex.annotations.NonNull;

/**
 * Created by san on 2017/12/13.
 */

public class LambdaObserver<T> implements Observer<T> {
    private Consumer<T> onNext;
    //一个接一个！！！
    public LambdaObserver(Consumer<T> onNext) {
        this.onNext = onNext;
    }
    @Override
    public void onSubscribe() {

    }

    @Override
    public void onNext(@NonNull T item) {
        onNext.onNext(item);
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
