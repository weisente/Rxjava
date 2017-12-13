package example.weisente.top.rxjava.rxjava;

import android.graphics.Bitmap;

/**
 * Created by san on 2017/12/13.
 */

public abstract class Observable<T> implements ObservableSource<T> {


    public static <T>Observable<T> just(T item){
        return onAssembly(new ObservableJust<T>(item));
    }

    /**
     * 是集装箱的意思  可以返回不同的对象  主要还是看需求
     * @param source
     * @param <T>
     * @return
     */
    private static <T> Observable<T> onAssembly(Observable<T> source) {
        // 留出来了
        return source;
    }


    /**
     * 这个是必须实现的 因为是所有的观察源必须具有的  也是为什么 我要继承ObservableSource
     * @param observer
     */
    @Override
    public void subscribe(Observer<T> observer) {

        subscribeActual(observer);
    }
    protected abstract void subscribeActual(Observer<T> observer);


    //加入map 加入map操作符
    public <R> Observable<R> map(Function<T, R> function) {
        return onAssembly(new ObservableMap<>(this,function));
    }

    public  void subscribe(Consumer<T> onNext){
        subscribe(onNext,null,null);
    }

    private void subscribe(Consumer<T> onNext, Consumer<T> error, Consumer<T> complete) {
        subscribe(new LambdaObserver<T>(onNext));
    }
    //添加切换线程的功能
    public Observable<Bitmap> subscribeOn(Schedulers schedulers) {
        return onAssembly(new ObservableSchedulers(this,schedulers));
    }
    public Observable<T> observerOn(Schedulers schedulers) {
        return onAssembly(new ObserverOnObservable(this,schedulers));
    }

}
