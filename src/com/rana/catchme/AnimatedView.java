package com.rana.catchme;

import com.rana.util.Constants;
import com.rana.util.LOG;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;

public class AnimatedView extends ImageView{
	private Context mContext;
	private Handler handler;
	private final int FRAME_RATE = 300;
	int x = 0, y = 0, speed=5;
	public int xPosition=100,yPosition=100;
	
	public AnimatedView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		handler = new Handler();
	}
	
	private void setChasingPoints() {
		this.xPosition=Constants.xPosition;
		this.yPosition=Constants.yPosition;
	}

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			invalidate();
		}
	};
	
	protected void onDraw(Canvas canvas) {
		BitmapDrawable ball = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.ic_launcher);
		setChasingPoints();
		if (x == xPosition && y < yPosition) {
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
//		else {
//			Toast.makeText(mContext, "game over", Toast.LENGTH_SHORT).show();
//		}
		LOG.v("co ordinates:", "" + x + "_____" + y);
		canvas.drawBitmap(ball.getBitmap(), x, y, null);
		handler.postDelayed(runnable, FRAME_RATE);
	}
}
