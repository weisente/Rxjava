package example.weisente.top.rxjava.rxjava;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by san on 2017/12/13.
 */

public abstract class Schedulers {
    //两种不一样的模式
    static Schedulers MAIN_THREAD;
    static Schedulers IO;

    static {
        IO = new IOSchedulers();
        MAIN_THREAD = new MainSchedulers(new Handler(Looper.getMainLooper()));
    }


    public static Schedulers mainThread() {
        return MAIN_THREAD;
    }
    public abstract void scheduleDirect(Runnable runnable);
    public static Schedulers io() {
        return IO;
    }

    private static class IOSchedulers extends Schedulers {
        ExecutorService service;
        public IOSchedulers(){
            service = Executors.newScheduledThreadPool(1, new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    return new Thread(r);
                }
            });
        }

        @Override
        public void scheduleDirect(Runnable runnable) {
            service.execute(runnable);
        }
    }

    private static class MainSchedulers extends Schedulers {
        private Handler handler;
        public MainSchedulers(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void scheduleDirect(Runnable runnable) {
            Message message = Message.obtain(handler,runnable);
            handler.sendMessage(message);
        }
    }
}
