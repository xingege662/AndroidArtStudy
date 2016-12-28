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
    private Book book;

    public User2(int age, String name, String sex,Book book) {
        this.age = age ;
        this.name = name;
        this.sex = sex;
        this.book = book;
    }


    protected User2(Parcel in) {
        age = in.readInt();
        name = in.readString();
        sex = in.readString();
        book = in.readParcelable(Book.class.getClassLoader());
    }

    /**
     * 反序列化
     */
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

    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * 序列化
     * @param dest
     * @param flags
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
        dest.writeString(sex);
        dest.writeParcelable(book, flags);
    }
}
