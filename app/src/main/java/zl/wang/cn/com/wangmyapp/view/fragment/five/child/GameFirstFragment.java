package zl.wang.cn.com.wangmyapp.view.fragment.five.child;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationManagerCompat;
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
import zl.wang.cn.com.wangmyapp.services.AppDownloadManager;
import zl.wang.cn.com.wangmyapp.services.UpdateIntentService;
import zl.wang.cn.com.wangmyapp.utils.CheckPermissionUtils;
import zl.wang.cn.com.wangmyapp.utils.ImageUtil;
import zl.wang.cn.com.wangmyapp.utils.ToastUtils;
import zl.wang.cn.com.wangmyapp.view.activity.AdsorbActivity;
import zl.wang.cn.com.wangmyapp.view.activity.ChannelActivity;
import zl.wang.cn.com.wangmyapp.view.activity.ClickLocationActivity;
import zl.wang.cn.com.wangmyapp.view.activity.DaggerTwoDemoActivity;
import zl.wang.cn.com.wangmyapp.view.activity.DataBindingActivity;
import zl.wang.cn.com.wangmyapp.view.activity.FaceRecognitionActivity;
import zl.wang.cn.com.wangmyapp.view.activity.FlowLayoutActivity;
import zl.wang.cn.com.wangmyapp.view.activity.GalleryProjectionActivity;
import zl.wang.cn.com.wangmyapp.view.activity.LabelActivity;
import zl.wang.cn.com.wangmyapp.view.activity.LikeActivity;
import zl.wang.cn.com.wangmyapp.view.activity.MoveTextActivity;
import zl.wang.cn.com.wangmyapp.view.activity.MyQRCodeActivity;
import zl.wang.cn.com.wangmyapp.view.activity.MyUCropActivity;
import zl.wang.cn.com.wangmyapp.view.activity.MyZxingActivity;
import zl.wang.cn.com.wangmyapp.view.activity.NavigationAnimationActivity;
import zl.wang.cn.com.wangmyapp.view.activity.PageTurningActivity;
import zl.wang.cn.com.wangmyapp.view.activity.PhotoViewActivity;
import zl.wang.cn.com.wangmyapp.view.activity.PullDownMenuActivity;
import zl.wang.cn.com.wangmyapp.view.activity.PullDownMenuAnotherActivity;
import zl.wang.cn.com.wangmyapp.view.activity.RippleActivity;
import zl.wang.cn.com.wangmyapp.view.activity.SlidingUpPanelActivity;
import zl.wang.cn.com.wangmyapp.view.activity.SlidingValidationActivity;
import zl.wang.cn.com.wangmyapp.view.activity.TagviewgroupActivity;
import zl.wang.cn.com.wangmyapp.view.activity.TextViewActivity;
import zl.wang.cn.com.wangmyapp.view.activity.TimerShaftActivity;
import zl.wang.cn.com.wangmyapp.view.activity.Together.TogetherActivity;
import zl.wang.cn.com.wangmyapp.view.activity.ViewActivity;
import zl.wang.cn.com.wangmyapp.view.activity.wangrxjava.WangRxjavaActivity;
import zl.wang.cn.com.wangmyapp.view.activity.WaterViewActivity;

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
    protected TextView tv_check_updates;
    protected TextView tv_adsorb;
    protected TextView tv_databinding;
    protected TextView tv_channel;
    protected TextView tv_voice;
    private TextView tv_view;
    private TextView tv_water_view;
    private TextView tv_text_view;
    private TextView flowlayout;
    private TextView click_location;
    private TextView dagger_two_demo;
    private TextView move_text;
    private TextView page_turning;
    private TextView ripple;
    private TextView together;
    private TextView gallery_projection;
    private TextView label;
    private TextView tagviewgroup;
    private TextView navigation_animation;
    private TextView pull_down_menu;
    private TextView face_recognition;

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
        tv_check_updates = view.findViewById(R.id.tv_check_updates);
        tv_adsorb = view.findViewById(R.id.tv_adsorb);
        tv_databinding = view.findViewById(R.id.tv_databinding);
        tv_channel = view.findViewById(R.id.tv_channel);
        tv_voice = view.findViewById(R.id.tv_voice);
        tv_view = view.findViewById(R.id.tv_view);
        tv_water_view = view.findViewById(R.id.tv_water_view);
        tv_text_view = view.findViewById(R.id.tv_text_view);
        flowlayout = view.findViewById(R.id.flowlayout);
        click_location = view.findViewById(R.id.click_location);
        dagger_two_demo = view.findViewById(R.id.dagger_two_demo);
        move_text = view.findViewById(R.id.move_text);
        page_turning = view.findViewById(R.id.page_turning);
        ripple = view.findViewById(R.id.ripple);
        together = view.findViewById(R.id.together);
        gallery_projection = view.findViewById(R.id.gallery_projection);
        label = view.findViewById(R.id.label);
        tagviewgroup = view.findViewById(R.id.tagviewgroup);
        navigation_animation = view.findViewById(R.id.navigation_animation);
        pull_down_menu = view.findViewById(R.id.pull_down_menu);
        face_recognition = view.findViewById(R.id.face_recognition);

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

        tv_check_updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog =
                        new AlertDialog.Builder(getActivity());
                dialog.setTitle("版本更新");
                dialog.setMessage("更新至新的版本");
                dialog.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //判断通知权限的有无,后续分状态处理
                                beforeUpdateWork();
                            }
                        });
                dialog.setNegativeButton("关闭",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //...To-do
                            }
                        });
                dialog.show();


            }
        });

        tv_adsorb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdsorbActivity.class);
                startActivity(intent);
            }
        });

        tv_databinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DataBindingActivity.class);
                startActivity(intent);
            }
        });

        tv_channel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChannelActivity.class);
                startActivity(intent);
            }
        });

        tv_voice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WangRxjavaActivity.class);
                startActivity(intent);

                //6.0,7.0,8.0更新
                //showUpdateDialog();
            }
        });

        tv_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewActivity.class);
                startActivity(intent);
            }
        });

        tv_water_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WaterViewActivity.class);
                startActivity(intent);
            }
        });

        flowlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FlowLayoutActivity.class);
                startActivity(intent);
            }
        });

        tv_text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TextViewActivity.class);
                startActivity(intent);
            }
        });

        click_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ClickLocationActivity.class);
                startActivity(intent);
            }
        });

        dagger_two_demo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaggerTwoDemoActivity.class);
                startActivity(intent);
            }
        });

        move_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MoveTextActivity.class);
                startActivity(intent);
            }
        });

        page_turning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PageTurningActivity.class);
                startActivity(intent);
            }
        });

        ripple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RippleActivity.class);
                startActivity(intent);
            }
        });

        together.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TogetherActivity.class);
                startActivity(intent);
            }
        });

        gallery_projection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GalleryProjectionActivity.class);
                startActivity(intent);
            }
        });

        label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LabelActivity.class);
                startActivity(intent);
            }
        });

        tagviewgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TagviewgroupActivity.class);
                startActivity(intent);
            }
        });

        navigation_animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NavigationAnimationActivity.class);
                startActivity(intent);
            }
        });

        pull_down_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getActivity(), PullDownMenuActivity.class);
                //startActivity(intent);
                Intent intent = new Intent(getActivity(), PullDownMenuAnotherActivity.class);
                startActivity(intent);
            }
        });

        face_recognition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FaceRecognitionActivity.class);
                startActivity(intent);
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

    private void beforeUpdateWork() {
        //没有权限的话,新对话框提醒用户
        if (!isEnableNotification()) {
            showNotificationAsk();
            return;
        }
        toIntentServiceUpdate();
    }

    private boolean isEnableNotification() {
        boolean ret = true;
        try {
            NotificationManagerCompat manager = NotificationManagerCompat.from(getActivity());
            ret = manager.areNotificationsEnabled();
        } catch (Exception e) {
            return true;
        }
        return ret;
    }

    private void showNotificationAsk() {
        AlertDialog.Builder dialog =
                new AlertDialog.Builder(getActivity());
        dialog.setTitle("通知权限");
        dialog.setMessage("打开通知权限");
        dialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //打开权限页面,让用户开启通知权限
                        toSetting();
                    }
                });
        dialog.setNeutralButton("跳过", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //用户可以跳过直接执行下载动作,但是不会有通知进度提醒(不过用户已知晓)
                toIntentServiceUpdate();
            }
        });
        dialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        dialog.show();
    }

    private void toSetting() {
        try {
            Intent localIntent = new Intent();
            localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getActivity().getPackageName(), null));
            startActivity(localIntent);
        } catch (Exception e) {

        }
    }

    private void toIntentServiceUpdate() {
        Intent updateIntent = new Intent(getActivity(), UpdateIntentService.class);
        updateIntent.setAction(UpdateIntentService.ACTION_UPDATE);
        updateIntent.putExtra("appName", "wangMyAPP-1.0.1");
        //随便一个apk的url进行模拟
        updateIntent.putExtra("downUrl", "http://gdown.baidu.com/data/wisegame/38cbb321c273886e/YY_30086.apk");
        getActivity().startService(updateIntent);
    }

    private void showUpdateDialog() {
        ToastUtils.showLongToast("暂时更新为适配6.0，7.0，8.0下载更新");
        AppDownloadManager mDownloadManager = new AppDownloadManager(getActivity());
        mDownloadManager.setUpdateListener(new AppDownloadManager.OnUpdateListener() {
            @Override
            public void update(int currentByte, int totalByte) {

            }
        });

        mDownloadManager.downloadApk("http://gdown.baidu.com/data/wisegame/38cbb321c273886e/YY_30086.apk", "wang", "下下下");
    }
}
