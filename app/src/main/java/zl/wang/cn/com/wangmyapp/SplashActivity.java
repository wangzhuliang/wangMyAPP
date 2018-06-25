package zl.wang.cn.com.wangmyapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.github.paolorotolo.appintro.AppIntro;

import zl.wang.cn.com.wangmyapp.utils.SampleSlide;

/**
 * Created by hahaha on 2018/3/9.
 * 欢迎页
 */

public class SplashActivity extends AppIntro{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        addSlide(SampleSlide.newInstance(R.layout.fragment_splash_one));
        addSlide(SampleSlide.newInstance(R.layout.fragment_splash_two));
        addSlide(SampleSlide.newInstance(R.layout.fragment_splash_three));
        addSlide(SampleSlide.newInstance(R.layout.fragment_splash_four));


        /*// Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
        addSlide(firstFragment);
        addSlide(secondFragment);
        addSlide(thirdFragment);
        addSlide(fourthFragment);

        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance(title, description, image, backgroundColor));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#3F51B5"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(false);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
    }*/
        setSkipText("跳过");
        setDoneText("完成");

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        startActivity(new Intent(SplashActivity.this, SplashAnoherActivity.class));
        finish();
    }



    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(SplashActivity.this, SplashAnoherActivity.class));
        finish();
    }
}
