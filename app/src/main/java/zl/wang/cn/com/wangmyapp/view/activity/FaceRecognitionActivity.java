package zl.wang.cn.com.wangmyapp.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.RequestHandler;

import org.xutils.common.Callback;
import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.http.body.FileBody;
import org.xutils.x;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.http.HTTP;
import zl.wang.cn.com.wangmyapp.R;
import zl.wang.cn.com.wangmyapp.bean.BaseBean;
import zl.wang.cn.com.wangmyapp.bean.UploadMultiFile;
import zl.wang.cn.com.wangmyapp.bean.face;
import zl.wang.cn.com.wangmyapp.utils.Sign;

public class FaceRecognitionActivity extends AppCompatActivity {

    private Button btn_face;
    private Button btn_pic;
    //请求识别码(分别为本地相册、相机、图片裁剪)
    private static final int CODE_PHOTO_REQUEST = 1;
    private static final int CODE_CAMERA_REQUEST = 2;
    private static final int CODE_PHOTO_CLIP = 3;
    protected Uri photoURI;
    private File file;
    private String a = "1";
    private TextView tv_aaa;
    private Button btn_face2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_recognnition);

        File appDir = new File(Environment.getExternalStorageDirectory(), "NewImage");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".png";
        file = new File(appDir, fileName);

        btn_face = findViewById(R.id.btn_face);
        btn_pic = findViewById(R.id.btn_pic);
        tv_aaa = findViewById(R.id.tv_aaa);
        btn_face2 = findViewById(R.id.btn_face2);

        btn_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = "1";
                new AlertDialog.Builder(FaceRecognitionActivity.this).setItems(R.array.str_body, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] aryshop = getResources().getStringArray(R.array.str_body);
                        if (aryshop[which].toString() == aryshop[0].toString()) {
                            //调用获取本地图片的方法
                            getPicFromLocal();
                        } else {
                            //调用相机拍照的方法
                            getPicFromCamera();
                        }
                    }
                }).show();
            }
        });

        btn_face.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = "2";
                new AlertDialog.Builder(FaceRecognitionActivity.this).setItems(R.array.str_body, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] aryshop = getResources().getStringArray(R.array.str_body);
                        if (aryshop[which].toString() == aryshop[0].toString()) {
                            //调用获取本地图片的方法
                            getPicFromLocal();
                        } else {
                            //调用相机拍照的方法
                            getPicFromCamera();
                        }
                    }
                }).show();
            }
        });

        btn_face2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = "3";
                new AlertDialog.Builder(FaceRecognitionActivity.this).setItems(R.array.str_body, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] aryshop = getResources().getStringArray(R.array.str_body);
                        if (aryshop[which].toString() == aryshop[0].toString()) {
                            //调用获取本地图片的方法
                            getPicFromLocal();
                        } else {
                            //调用相机拍照的方法
                            getPicFromCamera();
                        }
                    }
                }).show();
            }
        });
    }

    /**
     * 从本机相册获取图片
     */
    private void getPicFromLocal() {
        ActivityCompat.requestPermissions(FaceRecognitionActivity.this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_PHOTO_REQUEST);
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, CODE_PHOTO_REQUEST);
    }

    /**
     * 通过相机拍摄获取图片，
     * 并存入设置的路径中
     */
    private void getPicFromCamera() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            //权限还没有授予，需要在这里写申请权限的代码
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CODE_CAMERA_REQUEST);
        } else {
            takePhoto();
        }

    }


    private void takePhoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            //创建一个File
            //photoFile = ImageUtil.createImageFile();
            if (file != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    //如果是7.0及以上的系统使用FileProvider的方式创建一个Uri
                    Log.e("wang", "Build.VERSION.SDK_INT >= Build.VERSION_CODES.N");
                    photoURI = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);
                    takePictureIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    takePictureIntent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                } else {
                    //7.0以下使用这种方式创建一个Uri
                    photoURI = Uri.fromFile(file);
                }
                //将Uri传递给系统相机
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CODE_CAMERA_REQUEST);
            }
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 用户没有进行有效的设置操作，返回
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(FaceRecognitionActivity.this, "取消", Toast.LENGTH_LONG).show();
            return;
        }
        switch (requestCode) {
            case CODE_CAMERA_REQUEST:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    photoClip(photoURI);
                }else {
                    photoClip(Uri.fromFile(file));
                }
                break;
            case CODE_PHOTO_REQUEST:
                if (data != null) {
                    photoClip(data.getData());
                }
                break;
            case CODE_PHOTO_CLIP:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    if (extras != null) {
                        Bitmap photo = extras.getParcelable("data");
                        try {
                            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                            photo.compress(Bitmap.CompressFormat.PNG, 100, bos);
                            bos.flush();
                            bos.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        //转成二进制
                        /*ByteArrayOutputStream out = new ByteArrayOutputStream();
                        photo.compress(Bitmap.CompressFormat.PNG, 100, out);
                        try {
                            out.flush();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        byte[] a = out.toByteArray();
                        toDo(a);*/
                    }
                    uploadImage(file);
                }
                break;
        }
    }

    public void uploadImage(File file) {
        RequestParams params = new RequestParams("https://daolpu.youlin365.com/upload");
        // 添加到请求body体的参数, 只有POST, PUT, PATCH, DELETE请求支持.
        params.addQueryStringParameter("wd", "xUtils");
        // 使用multipart表单上传文件
        params.setMultipart(true);
        //params.addBodyParameter("file1", file, "multipart/form-data"); // 如果文件没有扩展名, 最好设置contentType参数.
        params.addBodyParameter("file", file, "image/png");
        x.http().post(params, new org.xutils.common.Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                Log.e("aa", "post请求成功" + result);
                Gson gson = new Gson();
                UploadMultiFile uploadMultiFile = gson.fromJson(result, UploadMultiFile.class);
                String userFace;
                if (uploadMultiFile.getUrls().substring(0, 4).equals("http")) {
                    userFace = uploadMultiFile.getUrls();
                } else {
                    userFace = "http://pic.youlin365.com" + uploadMultiFile.getUrls();
                }

                if (a.equals("1")) {
                    toDoFace(userFace);
                }else if (a.equals("2")) {
                    toDoAnother(userFace);
                }else {
                    toDoAnotherA(userFace);
                }

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    private void toDo(byte[] a) {
        String encodedstr = android.util.Base64.encodeToString(a, android.util.Base64.DEFAULT);
        /*RequestParams params = new RequestParams("http://service.image.myqcloud.com/v1/detection/imagetag_detect");

        params.addHeader("host", "service.image.myqcloud.com");
        params.addHeader("content-type", "multipart/form-data");


        Map<String, String> maps = new HashMap<String, String>();
        maps.put("appid", "1256833797");
        try {
            encodedstr = URLEncoder.encode(encodedstr, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        maps.put("image", encodedstr);

        if (null != maps && !maps.isEmpty()) {
            for (Map.Entry<String, String> entry : maps.entrySet()) {
                params.addBodyParameter(entry.getKey(), entry.getValue());
            }
        }

        x.http().post(params, new Callback.CommonCallback<String>() {


            @Override
            public void onSuccess(String result) {
                Log.e("aa", "post请求成功" + result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("aa", "错误" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });*/

        OkHttpClient client = new OkHttpClient();

        RequestBody requestBodyPost = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("appid", "1256833797")
                .addFormDataPart("image", encodedstr)
                .build();
        String aa = null;
        try {

            aa = Sign.appSign(1256833797,"AKIDStFToX151oHJG8IzwHmbKsFpKtQxPhMu","lMYbtHAuUt74rW3PvIe0DGiMKDzdcUBh"
                    ,"",0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Request requestPost = new Request.Builder()
                .url("http://service.image.myqcloud.com/v1/detection/imagetag_detect")
                .header("host", "service.image.myqcloud.com")
                .addHeader("authorization",aa)
                .post(requestBodyPost)
                .build();
        client.newCall(requestPost).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("aa", "错误" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("aa", "post请求成功" + response.message()+call);
                Gson gson = new Gson();
                face a = gson.fromJson(response.message(), face.class);

                tv_aaa.setText("年龄"+a.getData().getFace().get(0).getAge()
                +"/r/n"+"颜值"+a.getData().getFace().get(0).getBeauty());
            }
        });
    }

    private void toDoAnother(String url) {

        String aa = null;
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(logInterceptor).build();
        BaseBean book = new BaseBean();
        book.setAppid("1256833797");
        book.setUrl(url);
        Gson gson = new Gson();
        String json = gson.toJson(book);
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        String id = "AKIDStFToX151oHJG8IzwHmbKsFpKtQxPhMu";
        String key = "lMYbtHAuUt74rW3PvIe0DGiMKDzdcUBh";

        try {
            aa = Sign.appSign(1256833797, id, key,"",2592000);
        } catch (Exception e) {
            e.printStackTrace();
        }
         RequestBody requestBodyPost = new FormBody.Builder()
                .add("appid", "1256833797")
                .add("url", url)
                .build();
        Request requestPost = new Request.Builder()
                .url("http://service.image.myqcloud.com/v1/detection/imagetag_detect")
                .header("host", "service.image.myqcloud.com")
                .addHeader("content-type", "application/json")
                .addHeader("authorization",aa)
                .post(requestBody)
                .build();
        client.newCall(requestPost).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("aa", "错误" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("aa", "post请求成功" + response.message());
                //tv_aaa.setText(response.message());
            }
        });
    }

    public class HttpLogger implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            Log.d("HttpLogInfo", message);
        }
    }

    private void toDoAnotherA(String url) {

        String aa = null;
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(logInterceptor).build();
        BaseBean book = new BaseBean();
        book.setAppid("10134773");
        book.setUrl(url);
        Gson gson = new Gson();
        String json = gson.toJson(book);
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        String id = "AKID0sa6VVSO8h9aqtuFyd8SddhaIzazlI9p";
        String key = "vvxlSxIVXNDuTu9II7lDdsvoV6WkmeLq";

        try {
            aa = Sign.appSign(10134773, id, key,"",2592000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        RequestBody requestBodyPost = new FormBody.Builder()
                .add("appid", "1256833797")
                .add("url", url)
                .build();
        Request requestPost = new Request.Builder()
                .url("http://api.youtu.qq.com/youtu/imageapi/fooddetect")
                .header("host", "api.youtu.qq.com")
                .addHeader("content-type", "application/json")
                .addHeader("authorization",aa)
                .post(requestBody)
                .build();
        client.newCall(requestPost).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("aa", "错误" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("aa", "post请求成功" + response.message());
                //tv_aaa.setText(response.message());
            }
        });
    }




    private void toDoFace(String url) {

        String aa = null;
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(logInterceptor).build();
        BaseBean book = new BaseBean();
        book.setAppid("1256833797");
        book.setUrl(url);
        Gson gson = new Gson();
        String json = gson.toJson(book);
        //MediaType  设置Content-Type 标头中包含的媒体类型值
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        String id = "AKIDStFToX151oHJG8IzwHmbKsFpKtQxPhMu";
        String key = "lMYbtHAuUt74rW3PvIe0DGiMKDzdcUBh";

        try {
            aa = Sign.appSign(1256833797, id, key,"",2592000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Request requestPost = new Request.Builder()
                .url("http://recognition.image.myqcloud.com/face/detect")
                .header("host", "recognition.image.myqcloud.com")
                .addHeader("content-type", "application/json")
                .addHeader("authorization",aa)
                .post(requestBody)
                .build();
        client.newCall(requestPost).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("aa", "错误" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("aa", "post请求成功" + response.message());

            }
        });
    }


    /**
     * 图片裁剪
     *
     * @param uri
     */
    private void photoClip(Uri uri) {

        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        /*outputX outputY 是裁剪图片宽高这里仅仅是头像展示，不建议将值设置过高
        否则超过binder机制的缓存大小的1M限制报TransactionTooLargeException*/
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        // 不知道有啥用。。可能会保存一个比例值 需要相关文档啊
        intent.putExtra("scale", true);
        // true的话直接返回bitmap，可能会很占内存 不建议
        intent.putExtra("return-data", true);
        // 上面设为false的时候将MediaStore.EXTRA_OUTPUT即"output"关联一个Uri
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        //intent.putExtra("output", Uri.fromFile(outputImage));
        intent.putExtra("output", Uri.fromFile(file));
        // 看参数即可知道是输出格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        // 面部识别 这里用不上
        intent.putExtra("noFaceDetection", true);
        //intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, CODE_PHOTO_CLIP);
    }
}
