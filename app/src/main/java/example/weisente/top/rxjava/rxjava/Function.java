package example.weisente.top.rxjava.rxjava;

/**
 * Created by san on 2017/12/13.
 */

public interface Function<T,R> {
    R apply(T t) throws Exception;
}
