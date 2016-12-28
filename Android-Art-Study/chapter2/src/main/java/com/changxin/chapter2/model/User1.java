package com.changxin.chapter2.model;

import java.io.Serializable;

/**
 * Created by changxin on 2016/12/28.
 */

public class User1 implements Serializable{

    private static final long serialVersionUID = -8275660818528058225L;

    //假如transient关键字的字段不会序列化，静态成员变量不会被序列化，因为它不属于对象
    private transient int age;
    private String name;
    private String sex;

}
