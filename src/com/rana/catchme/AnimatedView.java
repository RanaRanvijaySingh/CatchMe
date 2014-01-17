package com.rana.catchme;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.rana.util.Constants;

public class AnimatedView extends ImageView{
	private Context mContext;
	private Handler handler;
	private final int FRAME_RATE = 300;
	int x, y, speed=5;
	public int xPosition=0,yPosition=0;
	private InputStream stream = null;
	
	public AnimatedView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		x = getResources().getDisplayMetrics().widthPixels - 100;
		y = getResources().getDisplayMetrics().heightPixels - 100 ;
		handler = new Handler();
		try {
			stream = context.getAssets().open("ace.gif");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setChasingPoints() {
		this.xPosition=Constants.xPosition;
		this.yPosition=Constants.yPosition;
	}

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			if (Constants.IS_MOVING) {
				invalidate();
			}
		}
	};
	
	protected void onDraw(Canvas canvas) {
		BitmapDrawable ball = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.ic_launcher);
		setChasingPoints();
		if ( isAboutToCollide() ) {
			Toast.makeText(mContext, "game over", Toast.LENGTH_SHORT).show();
			Constants.IS_MOVING = false;
			handler.removeCallbacks(runnable);
		}
		else if (x == xPosition && y < yPosition) {
			y += speed;
		} else if (x == xPosition && y > yPosition) {
			y -= speed;
		} else if (x < xPosition && y == yPosition) {
			x += speed;
		} else if (x > xPosition && y == yPosition) {
			x -= speed;
		} else if (x < xPosition && y < yPosition) {
			x += speed;
			y += speed;
		} else if (x < xPosition && y > yPosition) {
			x += speed;
			y -= speed;
		} else if (x > xPosition && y < yPosition) {
			x -= speed;
			y += speed;
		} else if (x > xPosition && y > yPosition) {
			x -= speed;
			y -= speed;
		} 
//		Log.v("co rodinates", x+":::::::::"+y);
		canvas.drawBitmap(ball.getBitmap(), x, y, null);
		handler.postDelayed(runnable, FRAME_RATE);
	}

	private boolean isAboutToCollide() {
		if ( ( x <= xPosition + Constants.BORDER_LIMIT && x >= xPosition - Constants.BORDER_LIMIT )
				&& ( y <= yPosition + Constants.BORDER_LIMIT && y >= yPosition - Constants.BORDER_LIMIT )) {
			return true;
		}
		else { 
			return false;
		}
	}
}
