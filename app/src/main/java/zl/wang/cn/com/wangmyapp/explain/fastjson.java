package zl.wang.cn.com.wangmyapp.explain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hahaha on 2018/3/7.
 */

public class fastjson {

    /**
     * 实体类转换成json
     */
    public static void voToJson() {

        /*UserInfo user = new UserInfo();
        user.setName("张三");
        user.setCar(null);
        user.setLike(new String[] { "吃", "喝" });
        // 注意：UserInfo中所有的属性都会显示出来，没有set的以默认值的方式显示（值为null的除外）
        String jsonstr = JSON.toJSONString(user);
        System.out.println("实体类转json格式字符串 :" + jsonstr);*/
    }

    /**
     * list集合的转换
     */
    /*public static void listToJson() {

        List<UserInfo> list = new ArrayList<UserInfo>();

        UserInfo userinfo1 = new UserInfo();
        UserInfo userinfo2 = new UserInfo();
        userinfo1.setAge(12);
        userinfo2.setAge(20);

        list.add(userinfo1);
        list.add(userinfo2);

        String json = JSON.toJSONString(list, true);
        System.out.println("List集合转json格式字符串 :" + json);
    }*/

    /**
     * 字符数组JSON转化为数组
     */
    /*public static void StringArrayToJSON() {

        String s = "[{\"name\":\"aa\",\"age\":10},{\"name\":\"bb\",\"age\":20}]";

        List<UserInfo> list = JSON.parseArray(s, UserInfo.class);
        for (UserInfo ui:list) {
            System.out.println(ui.getName());
        }
    }*/


    /**
     * 复杂数据类型
     */
    /*public static void Complexdata() {
        //map集合1
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("username", "zhangsan");
        map.put("age", 24);
        map.put("sex", "男");


        // map集合2
        HashMap<String, Object> temp = new HashMap<String, Object>();
        temp.put("name", "xiaohong");
        temp.put("age", "23");

        //map集合2装map集合1中
        map.put("girlInfo", temp);

        // list集合
        List<String> list = new ArrayList<String>();
        list.add("爬山");
        list.add("骑车");
        list.add("旅游");
        //map集合1装了List集合
        map.put("hobby", list);

        String jsonString = JSON.toJSONString(map);
        System.out.println("复杂数据类型:" + jsonString);
    }*/


    /**
     * JSON转成实体类
     */
    /*public static void Deserialization() {
        String json = "{\"name\":\"cc\",\"age\":24}";
        UserInfo userInfo = JSON.parseObject(json, UserInfo.class);
        System.out.println("姓名是:" + userInfo.getName() + ", 年龄是:"
                + userInfo.getAge());
    }*/


    /**
     * 格式化日期
     */
    /*public static void DateFormate() {
        Date d = new Date();
        System.out.println("时间：" + d);
        System.out.println("输出毫秒值：" + JSON.toJSONString(d));
        System.out.println("默认格式为:"
                + JSON.toJSONString(d,
                SerializerFeature.WriteDateUseDateFormat));
        System.out.println("自定义日期："
                + JSON.toJSONStringWithDateFormat(d, "yyyy-MM-dd",
                SerializerFeature.WriteDateUseDateFormat));
    }*/


    /**
     * Json转为实体
     */
    /*public static void Json2Eetity() {
        String json = "{\"name\":\"cc\",\"age\":24}";
        UserInfo userInfo = JSON.parseObject(json, UserInfo.class);
        System.out.println("输出对象的地址：" + userInfo.toString());
        System.out.println("输出对象的名字：" + userInfo.getName());
    }*/
}
