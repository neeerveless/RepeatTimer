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

public class MainActivity extends Activity implements OnClickListener, OnTouchListener {

	private static final int TIMER_TIME = 60;
	private static final int TIMER_ALTER_TIME = 60;

  public static final int START = R.id.textView1;
  public static final int STOP  = R.id.textView2;
  private TimerView mTimerView;
  private TextView starTextView
	private TextView stopTextView;
  private LinearLayout mLinearLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    setContentView(R.layout.activity_main);

		findViewById();
		
  }

	/**
	* findViewById 
	*/
	private void findViewById(){
		starTextView = (TextView)findViewById(START); 
		stopTextView = (TextView)findViewById(STOP);
		startTextView.setOnClickListener(this);
		stopTextView.setOnClickListener(this);
    mLinearLayout = (LinearLayout) findViewById(R.id.relativeLayout1);
    mLinearLayout.setOnTouchListener(this);
    mTimerView = (TimerView) findViewById(R.id.timerView1);
	}

	/**
	* initialize
	*/
	private void init(){
    mTimerView.setSetTime(TIMER_TIME);
    mTimerView.setAlertTime(TIMER_ALERT_TIME);
    mTimerView.setCounting(Counting.UP);
    mTimerView.setRepeatFlag(true);
    mTimerView.init();
 } 
  @Override
  public void onClick(View v) {
    switch (v.getId()) {
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
	public boolean onTouch(View view, MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mTimerView.init();
				break;
		}
		return true;
	}

  @Override
  protected void onPause() {
    mTimerView.stop();
    super.onPause();
  }

	/**
	* classにしろ！
	*/
  public void Log(String str) {
    Log.e("MainActivity", str);
  }


	private void makeToast(String str, int time){
    Toast.makeText(getApplicationContext(), str,
        time).show();
	}

  public void shortToast(String str) {
		makeToast(str, android.widget.Toast.LENGTH_SHORT);
  }
	/**
	* class(ry
	*/
  public void longToast(String str) {
		makeToast(str, android.widget.Toast.LENGTH_LONG);
  }
}
