package com.myth.repeattimer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myth.repeattimer.TimerView.Counting;

public class MainActivity extends Activity implements OnClickListener {

  public static final int 
      START = R.id.textView1,
      STOP  = R.id.textView2;
  public TimerView mTimerView;
  public TextView starTextView,stopTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    setContentView(R.layout.activity_main);

    LinearLayout mRelativeLayout = (LinearLayout) findViewById(R.id.relativeLayout1);
    mRelativeLayout.setOnTouchListener(new OnTouchListener() {

      @Override
      public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN:
          mTimerView.init();
          break;
        case MotionEvent.ACTION_MOVE:
          break;
        case MotionEvent.ACTION_UP:
          // if (event.getEventTime()-event.getDownTime() > 1000) {
          // mTimerView.init();
          // Toast("Reset!");
          // }else{
          // if(!mTimerView.isPlaying()){
          // mTimerView.start();
          // Toast("Start!");
          // }else{
          // mTimerView.stop();
          // Toast("Stop!");
          // }
          // }
          break;
        }
        return true;
      }
    });

    mTimerView = (TimerView) findViewById(R.id.timerView1);

    mTimerView.setSetTime(60);
    mTimerView.setAlertTime(20);
    mTimerView.setCounting(Counting.UP);
    // mTimerView.setCounting(Counting.DOWN);
    mTimerView.setRepeatFlag(true);
    mTimerView.init();
    
//    starTextView = (TextView)findViewById(START);
//    stopTextView = (TextView)findViewById(STOP);
    ((TextView)findViewById(START)).setOnClickListener(this);
    ((TextView)findViewById(STOP)).setOnClickListener(this);
    

  }
  
  @Override
  public void onClick(View view) {
    switch (view.getId()) {
    case START:
      if (!mTimerView.isPlaying()) {
        mTimerView.start();
      }
      break;
    case STOP:
      mTimerView.stop();
      break;
    }
  }

  @Override
  protected void onPause() {
    mTimerView.stop();
    super.onPause();
  }

  public void Log(String str) {
    Log.e("MainActivity", str);
  }

  public void Toast(String str) {
    Toast.makeText(getApplicationContext(), str,
        android.widget.Toast.LENGTH_LONG).show();
  }


}
