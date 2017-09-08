package exam.lawnmower;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.View;

public class MowerView extends View {

	private final long UPDATE_TIME = 20L;
	private Mower mower;

	public MowerView(Context context) {
		super(context);
	}

	public void start(Mower mower) {
		this.mower = mower;
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				handler.postDelayed(this, UPDATE_TIME);
				invalidate();
			}
		}, UPDATE_TIME);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int maxWidth = canvas.getWidth();
		int maxHeight = canvas.getHeight();
		mower.move(maxWidth, maxHeight);
		canvas.rotate(mower.getDegree(), mower.centerX(), mower.centerY());
		canvas.drawBitmap(mower.getBitmap(), mower.getX(), mower.getY(), null);
	}
}
