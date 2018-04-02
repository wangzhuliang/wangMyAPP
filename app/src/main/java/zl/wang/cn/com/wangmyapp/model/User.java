package zl.wang.cn.com.wangmyapp.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import zl.wang.cn.com.wangmyapp.BR;

/**
 * Created by lcling on 2018/3/28.
 * DataBinding可以实时动态刷新数据。
 * 这是通过继承DataBinding的BaseObservable来做到的。
 */

public class User extends BaseObservable{

    private String firstName;
    private String lastName;
    private String displayName;
    private int age;
    private String update;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName, String lastName, int age) {
        this(firstName, lastName);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDisplayName() {
        return firstName + " " + lastName;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }
}
