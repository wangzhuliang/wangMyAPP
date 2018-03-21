package zl.wang.cn.com.wangmyapp.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CodeUtils;

import zl.wang.cn.com.wangmyapp.R;

/**
 * Created by hahaha on 2018/3/9.
 * 生成二维码
 */

public class MyQRCodeActivity extends AppCompatActivity {

    protected EditText editText;
    protected Button button;
    protected Button button_logo;
    protected ImageView imageView;

    public Bitmap mBitmap = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qr_code);

        initView();
    }

    private void initView() {

        editText = findViewById(R.id.edit_content);
        button_logo = findViewById(R.id.button_logo);
        button = findViewById(R.id.button);
        imageView = findViewById(R.id.image_content);

        /**
         * 生成二维码图片
         */
        button_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textContent = editText.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(MyQRCodeActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                editText.setText("");
                mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                imageView.setImageBitmap(mBitmap);
            }
        });

        /**
         * 生成不带logo的二维码图片
         */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textContent = editText.getText().toString();
                if (TextUtils.isEmpty(textContent)) {
                    Toast.makeText(MyQRCodeActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                editText.setText("");
                mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
                imageView.setImageBitmap(mBitmap);
            }
        });
    }
}
