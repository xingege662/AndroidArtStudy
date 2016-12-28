package com.changxin.chapter2.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by changxin on 2016/12/28.
 */

public class User2 implements Parcelable {

    private int age;
    private String name;
    private String sex;

    public User2(int age, String name, String sex) {
        this.age = age ;
        this.name = name;
        this.sex = sex;
    }

    protected User2(Parcel in) {
        age = in.readInt();
        name = in.readString();
        sex = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
        dest.writeString(sex);
    }

    @Override
    public int describeContents() {
        //几乎所有情况下都返回的是0，仅当有文件描述时返回1
        return 0;
    }

    public static final Creator<User2> CREATOR = new Creator<User2>() {
        @Override
        public User2 createFromParcel(Parcel in) {
            return new User2(in);
        }

        @Override
        public User2[] newArray(int size) {
            return new User2[size];
        }
    };
}
