package example.weisente.top.rxjava.rxjava;

/**
 * Created by san on 2017/12/13.
 * 这是一个源 可以被观察
 */

public interface ObservableSource<T> {
    /**
     * 关注！！
     * @param observer
     */
    void subscribe(Observer<T> observer);
}
