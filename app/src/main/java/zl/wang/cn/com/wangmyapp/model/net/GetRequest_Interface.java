package zl.wang.cn.com.wangmyapp.model.net;

import retrofit2.Call;
import retrofit2.http.GET;
import zl.wang.cn.com.wangmyapp.bean.CMSBean;

/**
 * Created by Carson_Ho on 17/3/20.
 * http
 */

public interface GetRequest_Interface {


    @GET("posts/" + "?" +
            "client_id=ghost-frontend" + "&" +
            "client_secret=122ca884710f"+"&"+"filter=tags:ying-yang-wen-zhang"+"&"+
            "limit=10" + "&" +
            "page=1")
    Call<CMSBean> getCall();
    /*一样
    @HTTP(method = "get",path = "posts/" + "?" +
            "client_id=ghost-frontend" + "&" +
            "client_secret=122ca884710f"+"&"+"filter=tags:ying-yang-wen-zhang"+"&"+
            "limit=4" + "&" +
            "page=1",hasBody = false)*/
    //Call<CMSBean> iswang(@Path("user") String user);
    // 注解里传入 网络请求 的部分URL地址
    // getCall()是接受网络请求数据的方法
    //@Url：使用全路径复写baseUrl，适用于非统一baseUrl的场景。
    //@Streaming:用于下载大文件
    //@Path：URL占位符，用于替换和动态更新,相应的参数必须使用相同的字符串被@Path进行注释
    /**@GET("group/{id}/users")
    Call<List<User>> groupList(@Path("id") int groupId);*/
    //@Query,@QueryMap:查询参数，用于GET查询,需要注意的是@QueryMap可以约定是否需要encode
    /** @GET("group/users")
    Call<List<User>> groupList(@Query("id") int groupId);*/
    //@Body:用于POST请求体，将实例对象根据转换方式转换为对应的json字符串参数，
    //这个转化方式是GsonConverterFactory定义的。
    /**
     * @POST("add")
    Call<List<User>> addUser(@Body User user);*/
    //@Field，@FieldMap:Post方式传递简单的键值对,
    //需要添加@FormUrlEncoded表示表单提交
    /** @FormUrlEncoded
    @POST("user/edit")
    Call<User> updateUser(@Field("first_name") String first, @Field("last_name") String last);*/
    //@Part，@PartMap：用于POST文件上传
    //其中@Part MultipartBody.Part代表文件，@Part("key") RequestBody代表参数
    //需要添加@Multipart表示支持文件上传的表单，Content-Type: multipart/form-data
    /** @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);*/

    //@Header：header处理，不能被互相覆盖，用于修饰参数，
    //动态设置Header值
    /** @GET("user")
    Call<User> getUser(@Header("Authorization") String authorization)*/
    //等同于 :
    //静态设置Header值
    /** @Headers("Authorization: authorization")//这里authorization就是上面方法里传进来变量的值
    @GET("widget/list")
    Call<User> getUser()*/
    //@Headers 用于修饰方法,用于设置多个Header值：
    /*@Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })*/
    /** @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);*/


}
