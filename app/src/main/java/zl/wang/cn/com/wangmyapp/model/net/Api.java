package zl.wang.cn.com.wangmyapp.model.net;


import io.reactivex.Maybe;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;
import zl.wang.cn.com.wangmyapp.bean.BaseResponse;
import zl.wang.cn.com.wangmyapp.bean.LoginRequest;
import zl.wang.cn.com.wangmyapp.bean.LoginResponse;
import zl.wang.cn.com.wangmyapp.bean.RegisterRequest;
import zl.wang.cn.com.wangmyapp.bean.RegisterResponse;
import zl.wang.cn.com.wangmyapp.bean.UserBaseInfoRequest;
import zl.wang.cn.com.wangmyapp.bean.UserBaseInfoResponse;
import zl.wang.cn.com.wangmyapp.bean.UserExtraInfoRequest;
import zl.wang.cn.com.wangmyapp.bean.UserExtraInfoResponse;

/**
 * 随后定义Api接口
 */
public interface Api {

    @GET
    Observable<LoginResponse> login(@Body LoginRequest request);

    @GET
    Observable<RegisterResponse> register(@Body RegisterRequest request);

    @GET
    Observable<UserBaseInfoResponse> getUserBaseInfo(@Body UserBaseInfoRequest request);

    @GET
    Observable<UserExtraInfoResponse> getUserExtraInfo(@Body UserExtraInfoRequest request);

    @GET("v2/movie/top250")
    Observable<Response<ResponseBody>> getTop250(@Query("start") int start, @Query("count") int count);

    @GET
    Maybe<BaseResponse> getSomeThing(@Url String url);
}
