package zl.wang.cn.com.wangmyapp.view.activity;

import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import yanzhikai.textpath.AsyncPathView;
import yanzhikai.textpath.AsyncTextPathView;
import yanzhikai.textpath.SyncPathView;
import yanzhikai.textpath.SyncTextPathView;
import yanzhikai.textpath.painter.FireworksPainter;
import zl.wang.cn.com.wangmyapp.R;

/**
 * Created by lcling on 2018/4/10.
 *
 */

public class TextViewActivity extends AppCompatActivity {

    private SyncTextPathView stpv_2017;
    private AsyncTextPathView atpv_1;
    private SyncPathView spv;
    private AsyncPathView aspv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);

        atpv_1 = findViewById(R.id.atpv_1);
        stpv_2017 = findViewById(R.id.stpv_2017);
        spv = findViewById(R.id.spv);
        aspv = findViewById(R.id.aspv);

        //从无到显示
        atpv_1.startAnimation(0,1);
        //从显示到消失
        stpv_2017.startAnimation(1,0);

        /*atpv_type1.setTypeface(Typeface.SANS_SERIF);
        atpv_type2.setTypeface(Typeface.MONOSPACE);
        stpv_type3.setTypeface(Typeface.DEFAULT_BOLD);*/

        //必须先调用setPath设置路径
        aspv.setPath(new TestPath());

        spv.setPath(new TestPath());
        spv.setPathPainter(new FireworksPainter());

        spv.startAnimation(1,0);
        aspv.startAnimation(0,1);
    }

    public class TestPath extends Path {
        public TestPath(){
            init();
        }

        private void init() {
            addCircle(350,300,150,Direction.CCW);
            addCircle(350,300,100,Direction.CW);
            addCircle(350,300,50,Direction.CCW);
            moveTo(350,300);
            lineTo(550,500);
        }
    }
}
