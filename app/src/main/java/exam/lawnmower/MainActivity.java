package exam.lawnmower;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

public class MainActivity extends Activity implements OnClickListener {

    private EditText etWidthMain, etHeightMain;
    private FrameLayout flLawnMain;
    private Button btnExecuteMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
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
                if(!w.isEmpty() && !h.isEmpty()) {
                    int width = Integer.parseInt(w);
                    int height = Integer.parseInt(h);
                    GridLineView grid = new GridLineView(this);
                    grid.showGrid(width, height);
                    flLawnMain.removeAllViews();
                    flLawnMain.addView(grid);
                }
                break;
        }
    }
}
