package com.rana.catchme;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.rana.util.Constants;
import com.rana.util.LOG;

/**
 * @author webonise develop
 *
 */
public class MainActivity extends Activity implements View.OnTouchListener {
	private ViewGroup viewGroup;
	private ImageView imageView;
	private int xDelta;
	private int yDelta;
//	private int filesNames [] = {R.drawable.forward_a,
//			R.drawable.forward_b,R.drawable.forward_c,R.drawable.forward_d,
//			R.drawable.forward_e,R.drawable.forward_f};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		viewGroup = (ViewGroup) findViewById(R.id.relativeLayout);
		imageView = new ImageView(this);
		imageView.setBackgroundResource(R.drawable.move_stand);
		imageView.setOnTouchListener(this);
		viewGroup.addView(imageView);
	}

	public boolean onTouch(View view, MotionEvent event) {
		final int X = (int) event.getRawX();
		final int Y = (int) event.getRawY();
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams) view.getLayoutParams();
			xDelta = X - Params.leftMargin;
			yDelta = Y - Params.topMargin;
			Constants.IS_MOVING = true;		
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			showAnimation(event);
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

	void showAnimation(MotionEvent ev) {
		int historicalX, currentX;
		for(int i=0; i<ev.getHistorySize(); i++) {
			historicalX = (int) ev.getHistoricalX(i);
			currentX = (int) ev.getX();
			LOG.v("points", historicalX+":::::::::"+currentX);
			if(historicalX == currentX) {
				imageView.setBackgroundResource(R.drawable.move_stand);
			}else if(historicalX < currentX) {
				imageView.setBackgroundResource(R.drawable.move_forward);
			} else if(historicalX > currentX) {
				imageView.setBackgroundResource(R.drawable.move_backward);
			} 
			AnimationDrawable startAnimation = (AnimationDrawable) imageView.getBackground(); 
			startAnimation.start();
		}
	}

//	private void showAnimationMovement(int direction) {
//		if( direction == Constants.FORWARD) {
//			imageView.setBackgroundResource(R.drawable.move_forward);
//		} else if ( direction == Constants.BACKWARD ) {
//			imageView.setBackgroundResource(R.drawable.move_backward);
//		}
//		AnimationDrawable startAnimation = (AnimationDrawable) imageView.getBackground(); 
//		startAnimation.start();
//	}
}
