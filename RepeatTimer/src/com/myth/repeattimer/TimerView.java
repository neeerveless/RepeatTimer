package com.myth.repeattimer;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.widget.TextView;

public class TimerView extends TextView{
  public enum Counting{
    UP,
    DOWN,
  }
  
	private static final int DEFAULT_VALUE = 0;
  public Context mContext;
  public TextView mTextView;
  public Counting mCounting;
  public int setTime;
  public int alertTime;
  public boolean repeatFlag = false;
  public int time = DEFAULT_VALUE; 
  public int mLaptime = DEFAULT_VALUE ;
  public boolean playing = false;
  public Timer mTimer = null;
  public Handler mHandler = new Handler();
  public Vibrator vibrator;
  
  public TimerView(Context context) {
    super(context);
    this.mContext = context;
  }

  public TimerView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    this.mContext = context;
  }

  public TimerView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.mContext = context;
  }

  public void init(){
    time = DEFAULT_VALUE ;
    mLaptime = DEFAULT_VALUE ;
    setText(String.format("%1$02d",(mCounting == Counting.UP?0:setTime)));
  }
  
  public void setSetTime(int setTime){
    this.setTime = setTime;
  }
  

  public void setCounting(Counting counting){
    this.mCounting = counting;
  }

  public void setRepeatFlag(boolean flag){
    this.repeatFlag = flag;
  }
  
  public void setAlertTime(int alertTime){
    this.alertTime = alertTime;
  }
  
  public boolean isPlaying(){
    return playing;
  }
  
  public void vaibrate(final int vaibrateTime, final int count, final int interval){
    new Thread() {
      public void run() {
        for (int i = 0; i < count; i++) {
          vibrator.vibrate(vaibrateTime);
          try {
						Thread.sleep(vaibrateTime + interval);
					} catch (InterruptedException e) {}
        }
      }
    }.start();
  }
  
	/**
	* startTimer()
	* timer start method
	* magic number
	*/
  public void start(){
    vibrator = (Vibrator)mContext.getSystemService(Context.VIBRATOR_SERVICE);
    playing = true;
    mTimer = new Timer(true);
    mTimer.scheduleAtFixedRate( new TimerTask(){
      @Override
      public void run() {
          mHandler.post( new Runnable() {
              public void run() {
                switch (mCounting) {
                case UP:
                  time = mLaptime;
                  break;
                case DOWN:
                  time = setTime - mLaptime;
                  break;
                }
                if((setTime - mLaptime) == alertTime)
                  vaibrate(600, 2, 500);
                mLaptime++;
                setText(String.format("%1$02d",time));
                if(mLaptime > setTime){
                  vaibrate(1000, 0, 0);
                  if(repeatFlag){
                    mLaptime = 0;
                  }else{
                    stop();
                  }
                }
              }
          });
      }
    }, 1000, 1000);
  }

	/**
	* 何を
	*/
  public void stop(){
    playing = false;
    if(mTimer != null){
      mTimer.cancel();
      mTimer = null;
    }
  }
}
