package exam.lawnmower;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MowerView extends View {

    private final long UPDATE_TIME = 20L;

    private int sizeWidth, sizeHeight, mowerCount;
    private ArrayList<Mower> mowerList;
    private Context context;
    private Bitmap bitmap;

    public MowerView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        mowerList = new ArrayList<>();
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
        for(int i = 0; i < mowerCount; i++) {
            Mower mower = mowerList.get(i);
            mower.move(maxWidth, maxHeight);
            canvas.rotate(mower.getDegree(), mower.centerX(), mower.centerY());
            canvas.drawBitmap(bitmap, mower.getX(), mower.getY(), null);
        }
    }

    public void setInputs(int sizeWidth, int sizeHeight, int maxWidth, int maxHeight,
                          int mowerCount) {
        this.mowerCount = mowerCount;
        this.sizeWidth = sizeWidth;
        this.sizeHeight = sizeHeight;
        this.bitmap = getBitmap(maxWidth, maxHeight);
        int unitWidth = maxWidth / sizeWidth;
        int unitHeight = maxHeight / sizeHeight;
        int unitArea = unitWidth * unitHeight;
        int sizeArea = sizeWidth * sizeHeight;
        int msArea = sizeArea / mowerCount;
        long totalArea = maxWidth * maxHeight;
        long mtArea = (totalArea / mowerCount) - unitArea;
        Mower previous = null;
        for(int i = 0; i < mowerCount; i++) {
            Mower current = new Mower();
            current.setWidth(unitWidth);
            current.setHeight(unitHeight);
            current.setMTArea(mtArea);
            current.setMSArea(msArea);
            if(previous != null) {
                computePosition(previous, current);
            }
            mowerList.add(current);
            previous = current;
        }
        invalidate();
    }

    private void computePosition(Mower previous, Mower current) {
        int progress = 0;
        int maxArea = previous.getMSArea();
        loop:
        for(int x = previous.getCX(); x < sizeWidth; x++) {
            for(int y = previous.getCY(); y < sizeHeight; y++) {
                if(progress >= maxArea - 1) {
                    int cx = x;
                    int cy = maxArea % sizeHeight;
                    if(x % 2 != 0) {
                        cy = sizeHeight - cy;
                        current.setDirection(Mower.NORTH);
                    }
                    else {
                        current.setDirection(Mower.SOUTH);
                    }
                    current.setCX(cx);
                    current.setCY(cy);
                    current.setX(cx * current.getWidth());
                    current.setY(cy * current.getHeight());
                    break loop;
                }
                else {
                    progress++;
                }
            }
        }
    }

    private Bitmap getBitmap(int maxWidth, int maxHeight) {
        int unitWidth = maxWidth / sizeWidth;
        int unitHeight = maxHeight / sizeHeight;
        Resources res = context.getResources();
        Bitmap src = BitmapFactory.decodeResource(res, R.drawable.ic_mower);
        return Bitmap.createScaledBitmap(src, unitWidth, unitHeight, false);
    }
}
