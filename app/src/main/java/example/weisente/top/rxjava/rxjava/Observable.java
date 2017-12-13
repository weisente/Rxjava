package example.weisente.top.rxjava.rxjava;



/**
 * Created by san on 2017/12/13.
 */

public abstract  class Observable<T> implements ObservableSource<T> {
//
//    public static <T> ObservableSource<T> just(T item){
//        // new出来 ObservableJust 然后代理一下
//        return onAssembly(new ObservableJust<T>(item));
//    }
//
//    public static <T> ObservableSource<T> onAssembly(ObservableJust<T> source){
//        return source;
//    }
//
//
//
//    @Override
//    public void subscribe(Observer<T> observer) {
//        subscribeActual(observer);
//    }
//
//
////    public void subscribe(Consumer<T> onNext){
////        subscribe(onNext,null,null);
////    }
//
//
//    protected abstract void subscribeActual(Observer<T> observer);
//
//    private void subscribe(Consumer<T> onNext, Consumer<T> error, Consumer<T> complete) {
//        subscribe(new LambdaObserver<T>(onNext));
//    }
//
//
//
//    //这里是尝试这类型转换
//    public <R> Observable<R> map(Function<T, R> function) {
//
//        return onAssembly(new ObservableMap<>(this,function));
//    }
//
//    public <R> Observable<R> map(Function<T, R> function) {
//        return onAssembly(new ObservableMap<>(this,function));
//    }

//    protected abstract <R> Observable<R> onAssembly(ObservableMap<T, R> trObservableMap);


    public static <T> Observable<T> just(T item) {
        return onAssembly(new ObservableJust<T>(item));
    }

    private static <T> Observable<T> onAssembly(Observable<T> source) {
        // 留出来了
        return source;
    }

    @Override
    public void subscribe(Observer<T> observer) {
        subscribeActual(observer);
    }

    public void subscribe(Consumer<T> onNext){
        subscribe(onNext,null,null);
    }

    private void subscribe(Consumer<T> onNext, Consumer<T> error, Consumer<T> complete) {
        subscribe(new LambdaObserver<T>(onNext));
    }

    protected abstract void subscribeActual(Observer<T> observer);

    public <R> Observable<R> map(Function<T, R> function) {
        return onAssembly(new ObservableMap<>(this,function));
    }

}
