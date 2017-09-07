package exam.lawnmower;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GridLineView extends View {

    private int sizeWidth, sizeHeight;
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
        int maxWidth = getWidth();
        int maxHeight = getHeight();
        int unitWidth = maxWidth / sizeWidth;
        int unitHeight = maxHeight / sizeHeight;
        for(int i = 1; i <= sizeWidth - 1; i++) {
            int x = i * unitWidth;
            canvas.drawLine(x, 0, x, maxHeight, paint);
        }
        for(int i = 1; i <= sizeHeight - 1; i++) {
            int y = i * unitHeight;
            canvas.drawLine(0, y, maxWidth, y, paint);
        }
    }

    public void showGrid(int sizeWidth, int sizeHeight) {
        this.sizeWidth = sizeWidth;
        this.sizeHeight = sizeHeight;
        invalidate();
    }
}
