package sbingo.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import sbingo.freeradiogroup.FloatingLinearlayout;
import sbingo.main.R;

public class MainActivity extends AppCompatActivity {


    private FloatingLinearlayout floatingLinearlayout;
    private TextView textView;
    private TextView textView1;
    private boolean flag;

    private long currentMS;
    WindowManager windowManager;
    private FloatingLinearlayout floatingLinearlayout1;
    private int locationX;
    private int locationY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int height = windowManager.getDefaultDisplay().getHeight();
        int width = windowManager.getDefaultDisplay().getWidth();
        Log.i("Main", height + "...." + width);
        locationX = width - 135;
        locationY = height / 3 * 2 - 300;

        floatingLinearlayout = (FloatingLinearlayout) findViewById(R.id.fll);
        floatingLinearlayout1 = (FloatingLinearlayout) findViewById(R.id.fll1);

        textView = (TextView) findViewById(R.id.rb_1);
        textView1 = (TextView) findViewById(R.id.rb_2);

        setMargins(floatingLinearlayout1, locationX, locationY, 0, 0);

        floatingLinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        floatingLinearlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        currentMS = System.currentTimeMillis();//long currentMS     获取系统时间
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        long moveTime = System.currentTimeMillis() - currentMS;//移动时间
                        //判断是否继续传递信号
                        if (moveTime > 100) {
                            return true;
                        }
                        break;
                }
                return false;
            }
        });

        floatingLinearlayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        floatingLinearlayout1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        if (locationX > 0) {
                            locationX = 0;
                            setMargins(floatingLinearlayout1, 0, 0, 0, 0);
                        }
                        currentMS = System.currentTimeMillis();//long currentMS     获取系统时间
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                        long moveTime = System.currentTimeMillis() - currentMS;//移动时间
                        //判断是否继续传递信号
                        if (moveTime > 100) {
                            return true;
                        }
                        break;
                }
                return false;
            }
        });

    }

    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

}
