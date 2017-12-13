package example.weisente.top.rxjava.rxjava;

/**
 * Created by san on 2017/12/13.
 * 这个方法 的 目的 是进行类型的转换
 */

public interface Function<T,R> {
    R apply(T t) throws Exception;
}
