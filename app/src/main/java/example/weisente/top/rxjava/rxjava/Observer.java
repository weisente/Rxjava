package example.weisente.top.rxjava.rxjava;

import android.support.annotation.NonNull;

/**
 * Created by san on 2017/12/13.
 * 定义每一个关卡 所需要的处理
 */

public interface Observer<T> {
    void onSubscribe();
    void onNext(@NonNull T item);
    void onError(@NonNull Throwable e);
    void onComplete();
}
