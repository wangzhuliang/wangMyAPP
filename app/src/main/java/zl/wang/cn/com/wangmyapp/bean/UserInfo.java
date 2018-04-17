package zl.wang.cn.com.wangmyapp.bean;

public class UserInfo {

    UserBaseInfoResponse mBaseInfo;
    UserExtraInfoResponse mExtraInfo;

    public UserInfo(UserBaseInfoResponse baseInfo, UserExtraInfoResponse extraInfo) {
        mBaseInfo = baseInfo;
        mExtraInfo = extraInfo;
    }

}
