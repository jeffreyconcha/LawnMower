package exam.lawnmower;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class MainActivity extends Activity implements OnClickListener {

    private EditText etWidthMain, etHeightMain, etNoOfMowersMain;
    private FrameLayout flLawnMain;
    private Button btnExecuteMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        etNoOfMowersMain = (EditText) findViewById(R.id.etNoOfMowersMain);
        etHeightMain = (EditText) findViewById(R.id.etHeightMain);
        etWidthMain = (EditText) findViewById(R.id.etWidthMain);
        flLawnMain = (FrameLayout) findViewById(R.id.flLawnMain);
        btnExecuteMain = (Button) findViewById(R.id.btnExecuteMain);
        btnExecuteMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnExecuteMain:
                String w = etWidthMain.getText().toString();
                String h = etHeightMain.getText().toString();
                String n = etNoOfMowersMain.getText().toString();
                if(!w.isEmpty() && !h.isEmpty()) {
                    int sizeWidth = Integer.parseInt(w);
                    int sizeHeight = Integer.parseInt(h);
                    int maxWidth = flLawnMain.getWidth();
                    int maxHeight = flLawnMain.getHeight();
                    int mowerCount = Integer.parseInt(n);
                    GridLineView grid = new GridLineView(this);
                    grid.showGrid(sizeWidth, sizeHeight);
                    MowerView mower = new MowerView(this);
                    mower.setInputs(sizeWidth, sizeHeight, maxWidth, maxHeight, mowerCount);
                    flLawnMain.removeAllViews();
                    flLawnMain.addView(grid);
                    flLawnMain.addView(mower);
                }
                break;
        }
    }
}
