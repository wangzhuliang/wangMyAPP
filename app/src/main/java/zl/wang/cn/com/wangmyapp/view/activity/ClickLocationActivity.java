package zl.wang.cn.com.wangmyapp.view.activity;


import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;

import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.adapter.ClickLocationAdapter;

public class ClickLocationActivity extends AppCompatActivity implements ClickLocationAdapter.ClickLocationAdapterListener{

    private List<String> list = new ArrayList<>();
    private RecyclerView rv_click_location;
    private ClickLocationAdapter clickLocationAdapter;
    private int previousKeyboardHeight = -1;
    private Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_location);

        rv_click_location = findViewById(R.id.rv_click_location);

        list.add("");
        list.add("世界眼中的中国两会，是一道集聚全国人民磅礴之力的风景线，不仅展现着新时代中国的意气风发，而且增添着世界的生机和动能");
        list.add("中国智慧、中国方略、中国成就集中展现，来自世界的热评此起彼伏。“中国两会对全球事务有着巨大影响力”“是时候对中国的突飞猛进有清醒的认识了”“中国方案为世界提供借鉴”……来自世界的这些叙述，折射着探寻成功秘诀的目光。各国媒体纷纷向北京增派记者，3000多名中外记者报名采访中国两会。世界瞩目中国两会，期待进一步读懂新时代的中国。");
        list.add("作为世界最大发展中国家和第二大经济体，中国不断创造着人类发展史上惊天动地的奇迹。中国提供着最高的对世界经济增长的贡献率、最高的对世界减贫事业的贡献率，中国建成了世界上最大的社会保障网、高速铁路网，中国科技创新在诸多领域实现并跑、领跑……国际舆论普遍认为，着眼自身治理能力现代化的中国，日益为全球治理作出重要贡献。");
        list.add("世界经济复苏发展，中国功不可没。中国国家统计局最新数据显示，2017年，中国国内生产总值增速达6.9%，占世界经济的比重达15%，稳居世界第二。“中国的成功故事与世界经济的命运紧密相连。”国际货币基金组织第一副总裁戴维·利普顿这样评价。“世行对中国经济发展有信心，对中国持续成为全球经济增长引擎有信心。”世界银行发展预测局局长阿伊汗·高斯为中国点赞");
        list.add("国外不少人感叹，世界太需要像中国这样不断书写成功故事。但是，环顾当下的世界，一些国家深陷矛盾丛生、乱象频发的怪圈。在今年年初的达沃斯论坛年会和慕尼黑安全会议上，来自西方一些国家的人士纷纷大谈对社会分裂、政治极化、民粹主义盛行之忧。国际政治、社会领域诸多乱象，从若干侧面说明了失却方向之痛、道路探索之难。与此形成鲜明反差的是，中国的稳步发展和繁荣景象，无疑给不乏深忧的世界注入了昂扬振奋的力量。");
        list.add("中国的成功故事，揭示了中国道路自信、理论自信、制度自信、文化自信之源。中共十九大的胜利召开，为中国建设现代化强国指明了方向，为中华民族伟大复兴做了全面谋划。世界瞩目，十九大后的首次中国两会将如何围绕民众普遍关心的反腐倡廉、社会保障、教育公平、医疗改革、脱贫攻坚、依法治国、改革开放等一系列议题谋篇布局，中国智慧如何为破解各国面临的共同难题提供启示。");
        list.add("“立治有体，施治有序。”欧洲智库专家认为，中国在国家治理和推进改革方面展现的强大领导力，堪为世界各国推进改革的重要借鉴。不少报道过中国两会的外国记者表示，人大代表和政协委员积极为国家发展履职尽责、建言献策的情景让他们深刻体会到，中国的人民代表大会制度和中国共产党领导的多党合作和政治协商制度具有明显优越性，与西方一些执政党和反对党为了各自政治利益争论不休的场面有天壤之别。");
        list.add("站在新的历史起点上，中国蓄势待发。世界眼中的中国两会，是一道集聚全国人民磅礴之力的风景线，不仅展现着新时代中国的意气风发，而且增添着世界的生机和动能。");
        list.add("完完完完完完完完完完完完完完完完完完完完完完完完完完完完完完完完完完完完完完完完完");

        //确保RecyclerView的尺寸是一个常数。RecyclerView 的Item宽或者高不会变
        rv_click_location.setHasFixedSize(true);
        rv_click_location.setLayoutManager(new LinearLayoutManager(this));
        clickLocationAdapter = new ClickLocationAdapter(list,this);
        rv_click_location.setAdapter(clickLocationAdapter);

        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int displayHeight = rect.bottom - rect.top;
                int height = getWindow().getDecorView().getHeight();
                int keyboardHeight = height - displayHeight;
                if (previousKeyboardHeight != keyboardHeight) {
                    boolean hide = (double)displayHeight / height > 0.8;
                    if (hide) {
                        if (list.get(list.size()-1).equals("a")) {
                            list.remove(list.size() -1);
                            clickLocationAdapter.notifyDataSetChanged();
                        }
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }
                }
            }
        });
    }


    @Override
    public void showInputComment(View view, final int i) {
        // RV中评论区起始Y的位置
        final int rvInputY = getY(view);
        final int rvInputHeight = view.getHeight();

        dialog = new Dialog(this,android.R.style.Theme_Translucent_NoTitleBar);
        dialog.setContentView(R.layout.dialog_comment);
        dialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //对话框中输入框Y的位置
                int dialogY = getY(dialog.findViewById(R.id.dialog_layout_comment));

                if (i == list.size()-1){
                    list.add("a");
                    clickLocationAdapter.setHeight(dialog.findViewById(R.id.dialog_layout_comment).getHeight());
                    clickLocationAdapter.notifyDataSetChanged();
                }

                rv_click_location.smoothScrollBy(0,rvInputY - (dialogY - rvInputHeight));
            }
        },300);
    }

    private int getY(View view) {
        int[] rect = {0,1};
        view.getLocationOnScreen(rect);
        return rect[1];

    }


}
