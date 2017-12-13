package example.weisente.top.rxjava.rxjava;

import android.support.annotation.NonNull;

/**
 * Created by san on 2017/12/13.
 */

public class LambdaObserver<T> implements Observer<T> {
    private Consumer<T> onNext;

    public LambdaObserver(Consumer<T> onNext) {
        this.onNext = onNext;
    }

    @Override
    public void onSubscribe() {
        this.onNext = onNext;
    }

    @Override
    public void onNext(@NonNull T item) {
        try {
            onNext.onNext(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
