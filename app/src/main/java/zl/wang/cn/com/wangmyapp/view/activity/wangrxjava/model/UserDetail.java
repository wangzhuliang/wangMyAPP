package zl.wang.cn.com.wangmyapp.view.activity.wangrxjava.model;

/**
 * Created by amitshekhar on 04/02/17.
 */

public class UserDetail {

    public long id;
    public String firstname;
    public String lastname;

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
