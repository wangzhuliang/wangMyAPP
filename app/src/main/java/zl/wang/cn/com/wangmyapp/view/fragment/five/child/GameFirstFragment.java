package zl.wang.cn.com.wangmyapp.view.fragment.five.child;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.core.Controller;
import com.app.hubert.guide.listener.OnGuideChangedListener;
import com.app.hubert.guide.listener.OnLayoutInflatedListener;
import com.app.hubert.guide.listener.OnPageChangedListener;
import com.app.hubert.guide.model.GuidePage;
import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.listener.OnBindViewListener;
import com.timmy.tdialog.listener.OnViewClickListener;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.yokeyword.fragmentation.SupportFragment;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.custom_view.HeaderWaveHelper;
import zl.wang.cn.com.wangmyapp.custom_view.HeaderWaveView;
import zl.wang.cn.com.wangmyapp.utils.CheckPermissionUtils;
import zl.wang.cn.com.wangmyapp.utils.ImageUtil;
import zl.wang.cn.com.wangmyapp.view.activity.LikeActivity;
import zl.wang.cn.com.wangmyapp.view.activity.MyQRCodeActivity;
import zl.wang.cn.com.wangmyapp.view.activity.MyUCropActivity;
import zl.wang.cn.com.wangmyapp.view.activity.MyZxingActivity;
import zl.wang.cn.com.wangmyapp.view.activity.PhotoViewActivity;
import zl.wang.cn.com.wangmyapp.view.activity.SlidingUpPanelActivity;
import zl.wang.cn.com.wangmyapp.view.activity.SlidingValidationActivity;
import zl.wang.cn.com.wangmyapp.view.activity.TimerShaftActivity;
import zl.wang.cn.com.wangmyapp.view.fragment.five.GameFragment;
import zl.wang.cn.com.wangmyapp.view.fragment.third.child.MusicFirstFragment;

/**
 * Created by hahaha on 2018/3/13.
 * 自定义view
 */

public class GameFirstFragment extends SupportFragment implements EasyPermissions.PermissionCallbacks{

    @BindView(R.id.tv_like)
    TextView tvLike;
    @BindView(R.id.tv_sliding_validation)
    TextView tvSlidingValidation;
    @BindView(R.id.tv_timer_shaft)
    TextView tvTimerShaft;
    @BindView(R.id.tv_photo_view)
    TextView tv_photo_view;
    @BindView(R.id.tv_sliding_up_panel)
    TextView tv_sliding_up_panel;

    private HeaderWaveHelper mHeaderWaveHelper;

    private Intent intent;

    /**
     * 扫描跳转Activity RequestCode
     */
    public static final int REQUEST_CODE = 111;
    /**
     * 选择系统图片Request Code
     */
    public static final int REQUEST_IMAGE = 112;
    /**
     * 请求CAMERA权限码
     */
    public static final int REQUEST_CAMERA_PERM = 101;

    private static final int REQUEST_SELECT_PICTURE = 0x01;

    private static final String SAMPLE_CROPPED_IMAGE_NAME = "SampleCropImage";

    protected TextView tv_zxing_one;
    protected TextView tv_zxing_two;
    protected TextView tv_zxing_three;
    protected TextView tv_ucrop;
    protected TextView tv_dialog;
    protected TextView tv_evaluate;

    public static GameFirstFragment newInstance() {

        Bundle args = new Bundle();

        GameFirstFragment fragment = new GameFirstFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_game_first, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        initPermission();
        return view;
    }

    private void initView(View view) {

        HeaderWaveView waveView = (HeaderWaveView) view.findViewById(R.id.header_wave_view);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        final ScrollView mScrollView = (ScrollView) view.findViewById(R.id.sv);

        mHeaderWaveHelper = new HeaderWaveHelper(waveView, Color.parseColor("#80FC7A8C"),
                Color.parseColor("#40FB3D53"),imageView);

        //SDK API23以下请自行继承ScrollView实现该方法。
        /*mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {

                if (mScrollView.getScrollY() > 85) {
                    mHeaderWaveHelper.cancel();
                } else {
                    mHeaderWaveHelper.start();
                }

            }
        });*/

        tv_zxing_one = view.findViewById(R.id.tv_zxing_one);
        tv_zxing_two = view.findViewById(R.id.tv_zxing_two);
        tv_zxing_three = view.findViewById(R.id.tv_zxing_three);
        tv_ucrop = view.findViewById(R.id.tv_ucrop);
        tv_dialog = view.findViewById(R.id.tv_dialog);
        tv_evaluate = view.findViewById(R.id.tv_evaluate);

        tv_zxing_one = view.findViewById(R.id.tv_zxing_one);
        tv_zxing_two = view.findViewById(R.id.tv_zxing_two);
        tv_zxing_three = view.findViewById(R.id.tv_zxing_three);
        tv_ucrop = view.findViewById(R.id.tv_ucrop);
        tv_dialog = view.findViewById(R.id.tv_dialog);
        tv_evaluate = view.findViewById(R.id.tv_evaluate);

       /* SlideInUpAnimator animator = new SlideInUpAnimator(new OvershootInterpolator(1f));
        rv_wang.setItemAnimator(animator);*/

        tv_zxing_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cameraTask(R.id.tv_zxing_one);
            }
        });

        tv_zxing_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_IMAGE );
            }
        });

        tv_zxing_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MyQRCodeActivity.class);
                startActivity(intent);
            }
        });

        tv_ucrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                        REQUEST_SELECT_PICTURE);
            }
        });

        tv_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TDialog.Builder(getActivity().getSupportFragmentManager())
                        .setLayoutRes(R.layout.dialog_home_ad)
                        .setScreenHeightAspect(getActivity(), 0.7f)
                        .setScreenWidthAspect(getActivity(), 0.8f)
                        .setOnBindViewListener(new OnBindViewListener() {
                            @Override
                            public void bindView(BindViewHolder viewHolder) {
                                //可对图片进行修改
                            }
                        })
                        .addOnClickListener(R.id.iv_close)
                        .setOnViewClickListener(new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                                tDialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });

        tv_evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TDialog.Builder(getActivity().getSupportFragmentManager())
                        .setLayoutRes(R.layout.dialog_evaluate)
                        .setScreenWidthAspect(getActivity(), 1.0f)
                        .setGravity(Gravity.BOTTOM)
                        .addOnClickListener(R.id.btn_evluate)
                        .setOnBindViewListener(new OnBindViewListener() {
                            @Override
                            public void bindView(BindViewHolder viewHolder) {
                                final EditText editText = viewHolder.getView(R.id.editText);
                                editText.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                        imm.showSoftInput(editText, 0);
                                    }
                                });
                            }
                        })
                        .setOnViewClickListener(new OnViewClickListener() {
                            @Override
                            public void onViewClick(BindViewHolder viewHolder, View view, TDialog tDialog) {
                                EditText editText = viewHolder.getView(R.id.editText);
                                String content = editText.getText().toString().trim();
                                Toast.makeText(getActivity(), "评价内容:" + content, Toast.LENGTH_SHORT).show();
                                tDialog.dismiss();
                            }
                        })
                        .create()
                        .show();
            }
        });


        Animation enterAnimation = new AlphaAnimation(0f, 1f);
        enterAnimation.setDuration(600);
        enterAnimation.setFillAfter(true);

        Animation exitAnimation = new AlphaAnimation(1f, 0f);
        exitAnimation.setDuration(600);
        exitAnimation.setFillAfter(true);

        //新增多页模式，即一个引导层显示多页引导内容
        NewbieGuide.with(this)
                .setLabel("page")//设置引导层标示区分不同引导层，必传！否则报错
                .setOnGuideChangedListener(new OnGuideChangedListener() {
                    @Override
                    public void onShowed(Controller controller) {
                        Log.e("wang", "NewbieGuide onShowed: ");
                        //引导层显示
                    }

                    @Override
                    public void onRemoved(Controller controller) {
                        Log.e("wang", "NewbieGuide  onRemoved: ");
                        //引导层消失（多页切换不会触发）
                    }
                })
                .setOnPageChangedListener(new OnPageChangedListener() {
                    @Override
                    public void onPageChanged(int page) {
                        Log.e("wang", "NewbieGuide  onPageChanged: " + page);
                        //引导页切换，page为当前页位置，从0开始
                    }
                })
                .alwaysShow(true)//是否每次都显示引导层，默认false，只显示一次
                .addGuidePage(//添加一页引导页
                        GuidePage.newInstance()//创建一个实例
                                .addHighLight(tv_zxing_one)//添加高亮的view
                                //.addHighLight(tv_evaluate, HighLight.Shape.RECTANGLE)
                                .setLayoutRes(R.layout.view_guide)//设置引导页布局
                                .setOnLayoutInflatedListener(new OnLayoutInflatedListener() {
                                    @Override
                                    public void onLayoutInflated(View view) {
                                        //引导页布局填充后回调，用于初始化
                                        TextView tv = view.findViewById(R.id.textView2);
                                        tv.setText("这是动态设置的文本");
                                    }
                                })
                                .setEnterAnimation(enterAnimation)//进入动画
                                .setExitAnimation(exitAnimation)//退出动画
                )
                .addGuidePage(
                        GuidePage.newInstance()
                                //.addHighLight(tv_evaluate, HighLight.Shape.RECTANGLE,20)
                                .setLayoutRes(R.layout.view_guide_custom, R.id.iv)//引导页布局，点击跳转下一页或者消失引导层的控件id
                                .setEverywhereCancelable(false)//是否点击任意地方跳转下一页或者消失引导层，默认true
                                .setBackgroundColor(getResources().getColor(R.color.small_blue))//设置背景色，建议使用有透明度的颜色
                                .setEnterAnimation(enterAnimation)//进入动画
                                .setExitAnimation(exitAnimation)//退出动画
                )
                .show();//显示引导层(至少需要一页引导页才能显示)

    }

    @AfterPermissionGranted(REQUEST_CAMERA_PERM)
    public void cameraTask(int viewId) {
        if (EasyPermissions.hasPermissions(getActivity(), Manifest.permission.CAMERA)) {
            // Have permission, do the thing!
            Intent intent = new Intent(getActivity(), MyZxingActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, "需要请求camera权限",
                    REQUEST_CAMERA_PERM, Manifest.permission.CAMERA);
        }
    }

    /**
     * 初始化权限事件
     */
    private void initPermission() {
        //检查权限
        String[] permissions = CheckPermissionUtils.checkPermission(getActivity());
        if (permissions.length == 0) {
            //权限都申请了
            //是否登录
        } else {
            //申请权限
            ActivityCompat.requestPermissions(getActivity(), permissions, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Toast.makeText(getActivity(), "执行onPermissionsGranted()...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(getActivity(), "执行onPermissionsDenied()...", Toast.LENGTH_SHORT).show();
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this, "当前App需要申请camera权限,需要打开设置页面么?")
                    .setTitle("权限申请")
                    .setPositiveButton("确认")
                    .setNegativeButton("取消", null /* click listener */)
                    .setRequestCode(REQUEST_CAMERA_PERM)
                    .build()
                    .show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        /**
         * 处理二维码扫描结果
         */
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

        /**
         * 选择系统图片并解析
         */
        else if (requestCode == REQUEST_IMAGE) {
            if (data != null) {
                Uri uri = data.getData();
                try {
                    CodeUtils.analyzeBitmap(ImageUtil.getImageAbsolutePath(getActivity(), uri), new CodeUtils.AnalyzeCallback() {
                        @Override
                        public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                            Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onAnalyzeFailed() {
                            Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (requestCode == REQUEST_CAMERA_PERM) {
            Toast.makeText(getActivity(), "从设置页面返回...", Toast.LENGTH_SHORT).show();
        }

        /**
         * 裁剪图片
         */
        else if (requestCode == REQUEST_SELECT_PICTURE){
            if (data != null) {
                final Uri selectedUri = data.getData();
                if (selectedUri != null) {
                    startCropActivity(data.getData());
                } else {
                    Toast.makeText(getActivity(), "Cannot retrieve selected image", Toast.LENGTH_SHORT).show();
                }
            }
        }

        else if (requestCode == UCrop.REQUEST_CROP) {
            handleCropResult(data);
        }

    }

    private void startCropActivity(@NonNull Uri uri) {
        String destinationFileName = SAMPLE_CROPPED_IMAGE_NAME;

        destinationFileName += ".png";

        UCrop uCrop = UCrop.of(uri, Uri.fromFile(new File(getActivity().getCacheDir(), destinationFileName)));

        //uCrop = basisConfig(uCrop);
        //uCrop = advancedConfig(uCrop);

        uCrop.start(getActivity(),GameFirstFragment.this);
    }

    private void handleCropResult(@NonNull Intent result) {
        final Uri resultUri = UCrop.getOutput(result);
        if (resultUri != null) {
            MyUCropActivity.startWithUri(getActivity(), resultUri);
        } else {
            Toast.makeText(getActivity(), "Cannot retrieve cropped image", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick({R.id.tv_like, R.id.tv_sliding_validation, R.id.tv_timer_shaft,R.id.tv_photo_view,R.id.tv_sliding_up_panel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_like:
                intent = new Intent(getActivity(), LikeActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_sliding_validation:
                intent = new Intent(getActivity(), SlidingValidationActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_timer_shaft:
                intent = new Intent(getActivity(), TimerShaftActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_photo_view:
                intent = new Intent(getActivity(), PhotoViewActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_sliding_up_panel:
                intent = new Intent(getActivity(), SlidingUpPanelActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mHeaderWaveHelper.cancel();
    }

    @Override
    public void onResume() {
        super.onResume();
        mHeaderWaveHelper.start();
    }
}
