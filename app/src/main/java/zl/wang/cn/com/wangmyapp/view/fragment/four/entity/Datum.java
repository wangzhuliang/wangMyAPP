package zl.wang.cn.com.wangmyapp.view.fragment.four.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.picasso.Target;

/**
 * Simple model class
 * One important requirement for DeckView to function
 * is that all items in the dataset *must be* uniquely
 * identifiable. No two items can be such
 * that `item1.equals(item2)` returns `true`.
 * See equals() implementation below.
 * `id` is generated using `DeckViewSampleActivity#generateuniqueKey()`
 * Implementing `Parcelable` serves only one purpose - to persist data
 * on configuration change.
 */
public class Datum implements Parcelable {

//    Bitmap thumbnail,
//    Bitmap bitmap, String iconTitle,
//    String title, String subTitle
//
    public int id;
    public String iconTitle, title, subTitle;

    public Target target_image,target_avatar_icon;
    public String image_url,avatar_url;// 对于上面两个Target.

    public Datum() {
        // Nothing
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public Datum(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        id = in.readInt();
        iconTitle = in.readString();
        title = in.readString();
        subTitle = in.readString();
        image_url = in.readString();
        avatar_url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(iconTitle);
        dest.writeString(title);
        dest.writeString(subTitle);
        dest.writeString(image_url);
        dest.writeString(avatar_url);
    }

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        return ((Datum) o).id == this.id;
    }
}