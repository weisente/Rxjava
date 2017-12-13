package example.weisente.top.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import example.weisente.top.rxjava.rxjava.Consumer;
import example.weisente.top.rxjava.rxjava.Function;
import example.weisente.top.rxjava.rxjava.Observable;
import example.weisente.top.rxjava.rxjava.Schedulers;


public class MainActivity extends AppCompatActivity {
    private ImageView mImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImage = (ImageView) findViewById(R.id.image);
        Log.e("特别","？？");

        Observable.just("http://img.taopic.com/uploads/allimg/130331/240460-13033106243430.jpg")// ObservableJust
                .map(new Function<String, Bitmap>() { // 事件变换 // ObservableMap  source -> ObservableJust
                    @Override
                    public Bitmap apply(@NonNull String urlPath) throws Exception {
                        Log.e("apply1", Thread.currentThread().getName());
                        URL url = new URL(urlPath);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream inputStream = urlConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        return bitmap;
                    }
                })
                .map(new Function<Bitmap, Bitmap>() { // ObservableMap
                    @Override
                    public Bitmap apply(@NonNull Bitmap bitmap) throws Exception {
                        bitmap = createWatermark(bitmap, "RxJava2.0");
                        return bitmap;
                    }
                })
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(Bitmap bitmap) throws Exception {
                        Log.e("apply2", Thread.currentThread().getName());
                        return bitmap;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observerOn(Schedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() { // ObservableMap
                    @Override
                    public void onNext(Bitmap bitmap) throws Exception {
                        Log.e("onNext", Thread.currentThread().getName());// 子线程 or 主线程 ？ 1 2
                        mImage.setImageBitmap(bitmap);
                    }
                });
    }

    private Bitmap createWatermark(Bitmap bitmap, String mark) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint p = new Paint();
        // 水印颜色
        p.setColor(Color.parseColor("#C5FF0000"));
        // 水印字体大小
        p.setTextSize(150);
        //抗锯齿
        p.setAntiAlias(true);
        //绘制图像
        canvas.drawBitmap(bitmap, 0, 0, p);
        //绘制文字
        canvas.drawText(mark, 0, h / 2, p);
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        return bmp;
    }
}
