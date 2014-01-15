package com.rana.catchme;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rana.util.Constants;

public class MainActivity extends Activity implements View.OnTouchListener {
	private ViewGroup viewGroup;
	private ImageView imageView;
	private int xDelta;
	private int yDelta;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewGroup = (ViewGroup) findViewById(R.id.relativeLayout);
		imageView = new ImageView(this);
		imageView.setImageResource(R.drawable.ic_launcher);
		imageView.setOnTouchListener(this);
		viewGroup.addView(imageView);
	}

	public boolean onTouch(View view, MotionEvent event) {
		final int X = (int) event.getRawX();
		final int Y = (int) event.getRawY();
//		LOG.v("co ordinates:",""+X+"_____"+Y);
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams) view.getLayoutParams();
			xDelta = X - Params.leftMargin;
			yDelta = Y - Params.topMargin;
			Constants.IS_MOVING = true;		
//			LOG.v("co ordinates:",""+xDelta+"_____"+yDelta);
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			Constants.xPosition = X;
			Constants.yPosition = Y;
			RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
			layoutParams.leftMargin = X - xDelta;
			layoutParams.topMargin = Y - yDelta;
			layoutParams.rightMargin = -50;
			layoutParams.bottomMargin = -50;
			view.setLayoutParams(layoutParams);
			if(!Constants.IS_MOVING) { 
				finish();
			}
			break;
		}
		viewGroup.invalidate();
		return true;
	}
}
