package example.weisente.top.rxjava.rxjava;

import io.reactivex.annotations.NonNull;

/**
 * Created by san on 2017/12/13.
 */

public class ObservableMap<T,R> extends Observable<R>  {

    final Observable<T> source;// 前面的 Observable
    final Function<T, R> function;// 当前转换

    public ObservableMap(Observable<T> source, Function<T, R> function) {
        this.source = source;
        this.function = function;
    }




    @Override
    protected void subscribeActual(Observer<R> observer) {
        // 第一步
        // 对 observer 包裹了一层
        source.subscribe(new MapObserver(observer,function));
    }

    private class MapObserver<T> implements Observer<T>{
        final Observer<R> observer;
        final Function<T, R> function;

        public MapObserver(Observer<R> source, Function<T, R> function) {
            this.observer = source;
            this.function = function;
        }
        @Override
        public void onSubscribe() {
            observer.onSubscribe();;
        }

        @Override
        public void onNext(@NonNull T item) {
            try {
                //进行类型的转换
                R applyR = function.apply(item);
                observer.onNext(applyR);
            } catch (Exception e) {
                e.printStackTrace();
                observer.onError(e);
            }
        }

        @Override
        public void onError(@NonNull Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }
    }
}
