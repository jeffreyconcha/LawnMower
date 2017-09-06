package exam.lawnmower;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GridLineView extends View {

    private int height = 0;
    private int width = 0;
    private Paint paint;

    public GridLineView(Context context) {
        super(context);
        init();
    }

    public GridLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1F);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int mWidth = getWidth();
        int mHeight = getHeight();
        int w = mWidth / width;
        int h = mHeight / height;
        for(int i = 1; i <= width - 1; i++) {
            int x = i * w;
            canvas.drawLine(x, 0, x, mHeight, paint);
        }
        for(int i = 1; i <= height - 1; i++) {
            int y = i * h;
            canvas.drawLine(0, y, mWidth, y, paint);
        }
    }

    public void showGrid(int width, int height) {
        this.width = width;
        this.height = height;
        invalidate();
    }
}
