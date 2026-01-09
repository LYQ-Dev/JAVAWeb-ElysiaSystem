package com.lyq.utils;


//定义ThreadLocal操作的工具类，用于操作当前登录员工ID
public class CurrentHolder {

    //创建ThreadLocal对象，用于保存当前线程的ID
    private static final ThreadLocal<Integer> CURRENT_LOCAL = new ThreadLocal<>();

    //设置当前线程的线程局部变量的值
    public static void setCurrentId(Integer employeeId) {
        CURRENT_LOCAL.set(employeeId);
    }

    //返回当前线程所对应的线程局部变量的值
    public static Integer getCurrentId() {
        return CURRENT_LOCAL.get();
    }

    //移除当前线程所对应的线程局部变量
    public static void remove() {
        CURRENT_LOCAL.remove();
    }
}