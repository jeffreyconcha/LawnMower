package exam.lawnmower;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.ArrayList;

public class MainActivity extends Activity implements OnClickListener {

    private EditText etWidthMain, etHeightMain, etNoOfMowersMain;
    private FrameLayout flLawnMain;
    private Button btnStartMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        etNoOfMowersMain = (EditText) findViewById(R.id.etNoOfMowersMain);
        etHeightMain = (EditText) findViewById(R.id.etHeightMain);
        etWidthMain = (EditText) findViewById(R.id.etWidthMain);
        flLawnMain = (FrameLayout) findViewById(R.id.flLawnMain);
        btnStartMain = (Button) findViewById(R.id.btnStartMain);
        btnStartMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnStartMain:
                String w = etWidthMain.getText().toString();
                String h = etHeightMain.getText().toString();
                String n = etNoOfMowersMain.getText().toString();
                if(!w.isEmpty() && !h.isEmpty()) {
                    int sizeWidth = Integer.parseInt(w);
                    int sizeHeight = Integer.parseInt(h);
                    int maxWidth = flLawnMain.getWidth();
                    int maxHeight = flLawnMain.getHeight();
                    int mowerCount = Integer.parseInt(n);
                    final GridLineView grid = new GridLineView(this);
                    grid.showGrid(sizeWidth, sizeHeight);
                    flLawnMain.removeAllViews();
                    flLawnMain.addView(grid);
                    ArrayList<Mower> mowerList = getMowerList(sizeWidth, sizeHeight,
                            maxWidth, maxHeight, mowerCount);
                    for(Mower mower : mowerList) {
                        MowerView view = new MowerView(this);
                        view.start(mower);
                        flLawnMain.addView(view);
                    }
                }
                break;
        }
    }

    public ArrayList<Mower> getMowerList(int sizeWidth, int sizeHeight, int maxWidth, int maxHeight,
                                         int mowerCount) {
        ArrayList<Mower> mowerList = new ArrayList<>();
        Bitmap bitmap = getBitmap(maxWidth, maxHeight, sizeWidth, sizeHeight);
        int unitWidth = maxWidth / sizeWidth;
        int unitHeight = maxHeight / sizeHeight;
        int unitArea = unitWidth * unitHeight;
        int sizeArea = sizeWidth * sizeHeight;
        int msArea = sizeArea / mowerCount;
        int excessArea = sizeArea % mowerCount;
        ArrayList<Position> positionList = getPositions(sizeWidth, sizeHeight);
        for(int i = 0; i < mowerCount; i++) {
            int index = msArea * i;
            msArea = i != mowerCount - 1 ? msArea :
                    msArea + excessArea;
            long mtArea = (msArea * unitArea) - unitArea;
            Position position = positionList.get(index);
            int direction = position.getDegree();
            int x = position.getCx() * unitWidth;
            int y = position.getCy() * unitHeight;
            if(direction == Mower.NORTH) {
                bitmap = rotateBitmap(bitmap, 0);
            }
            Mower current = new Mower();
            current.setBitmap(bitmap);
            current.setWidth(unitWidth);
            current.setHeight(unitHeight);
            current.setMTArea(mtArea);
            current.setMSArea(msArea);
            current.setDirection(direction);
            current.setDx(position.getCx());
            current.setX(x);
            current.setY(y);
            mowerList.add(current);
        }
        return mowerList;
    }

    private ArrayList<Position> getPositions(int sizeWidth, int sizeHeight) {
        ArrayList<Position> positionList = new ArrayList<>();
        for(int x = 0; x < sizeWidth; x++) {
            if(x % 2 == 0) {
                for(int y = 0; y < sizeHeight; y++) {
                    positionList.add(new Position(x, y, Mower.SOUTH));
                }
            }
            else {
                for(int y = sizeHeight - 1; y >= 0; y--) {
                    positionList.add(new Position(x, y, Mower.NORTH));
                }
            }
        }
        return positionList;
    }

    private Bitmap getBitmap(int maxWidth, int maxHeight, int sizeWidth, int sizeHeight) {
        int unitWidth = maxWidth / sizeWidth;
        int unitHeight = maxHeight / sizeHeight;
        Bitmap src = BitmapFactory.decodeResource(getResources(), R.drawable.ic_mower);
        return Bitmap.createScaledBitmap(src, unitWidth, unitHeight, false);
    }

    public Bitmap rotateBitmap(Bitmap src, float degrees) {
        if(src != null) {
            Matrix matrix = new Matrix();
            matrix.postRotate(degrees);
            return Bitmap.createBitmap(src, 0, 0, src.getWidth(),
                    src.getHeight(), matrix, true);
        }
        return null;
    }
}
