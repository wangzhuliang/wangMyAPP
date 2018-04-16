package zl.wang.cn.com.wangmyapp.model;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import zl.wang.cn.com.wangmyapp.model.listener.LoadTasksCallBack;
import zl.wang.cn.com.wangmyapp.bean.CMSBean;
import zl.wang.cn.com.wangmyapp.model.net.GetRequest_Interface;
import zl.wang.cn.com.wangmyapp.model.net.NetTask;

/**
 * Created by 99142 on 2018/2/28.
 * NetTask实现类,获取数据
 */

public class WangTask implements NetTask<String> {

    private static WangTask Instance = null;
    private WangTask() {

    }

    public static WangTask getInstance() {
        if (Instance == null){
            Instance = new WangTask();
        }
        return Instance;
    }

    @Override
    public void execute(String data, final LoadTasksCallBack callBack) {

        //网络请求
        callBack.onStart();
        /**
         * retrofit2网络请求
         */
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        onHttps(builder);
        //创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder() //设置baseUrl
                .baseUrl("https://cms.youlin365.com/ghost/api/v0.1/") // 设置 网络请求 Url//添加Gson转换器
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)//设置OKHttpClient,如果不设置会提供一个默认的
                .client(builder.build())
                .build();

        // 创建 网络请求接口 的实例
        final GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);

        //对 发送请求 进行封装
        Call<CMSBean> call = request.getCall();

        //call只能调用一次。否则会抛 IllegalStateException
        //Call<List<Repo>> clone = call.clone();

        //发送网络请求(异步)
        call.enqueue(new Callback<CMSBean>() {
            //请求成功时候的回调
            @Override
            public void onResponse(Call<CMSBean> call, Response<CMSBean> response) {
                //请求处理,输出结果

                String urlImage = "http://cms.youlin365.com" + response.body().getPosts().get(0).getImage();
                String urlName = "http://cms.youlin365.com" + response.body().getPosts().get(0).getMeta_title().toString();
                String urlText = "http://cms.youlin365.com" + response.body().getPosts().get(0).getTitle();

                callBack.onSuccess(response);
                //callBack.onFailed();
            }

            //请求失败时候的回调
            @Override
            public void onFailure(Call<CMSBean> call, Throwable throwable) {
                callBack.onFailed();
                //System.out.println("连接失败"+throwable.toString());
            }

        });

        // 取消
        //call.cancel();

        //loadTasksCallBack.onStart();
        //loadTasksCallBack.onFinish();


    }


    public static SSLSocketFactory getSSLSocketFactory() throws Exception {
        //创建一个不验证证书链的证书信任管理器。
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext
                .getSocketFactory();
    }


    //使用自定义SSLSocketFactory
    private void onHttps(OkHttpClient.Builder builder) {
        try {
            builder.sslSocketFactory(getSSLSocketFactory())
                    .hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void request() {

        /*//步骤4:创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .build();

        // 步骤5:创建 网络请求接口 的实例
        PostRequest_Interface request = retrofit.create(PostRequest_Interface.class);

        //对 发送请求 进行封装(设置需要翻译的内容)
        Call<Translation1> call = request.getCall("I love you");

        //步骤6:发送网络请求(异步)
        call.enqueue(new Callback<Translation1>() {

            //请求成功时回调
            @Override
            public void onResponse(Call<Translation1> call, Response<Translation1> response) {
                // 请求处理,输出结果
                // 输出翻译的内容
                System.out.println("翻译是："+ response.body().getTranslateResult().get(0).get(0).getTgt());
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<Translation1> call, Throwable throwable) {
                System.out.println("请求失败");
                System.out.println(throwable.getMessage());
            }
        });*/
    }

}
