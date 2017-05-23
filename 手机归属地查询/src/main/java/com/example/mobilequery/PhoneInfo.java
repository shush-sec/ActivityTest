package com.example.mobilequery;

/**
 * Created by shush on 2017/5/23.
 */

public class PhoneInfo {
    String success;
    String phone;
    String area;
    String postno;      //邮编
    String att;
    String ctype;       //手机号类型
    String par;         //部分手机号
    String prefix;
    String operators;
    String style_simcall;
    String style_citynm;

    @Override
    public String toString() {
        return "号码：" + phone + "\r\n" + "地区：" + att +
                "\r\n" + "卡类型：" + ctype +
                "\r\n" + "运营商：" + operators + "\r\n";
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPostno() {
        return postno;
    }

    public void setPostno(String postno) {
        this.postno = postno;
    }

    public String getAtt() {
        return att;
    }

    public void setAtt(String att) {
        this.att = att;
    }

    public String getCtype() {
        return ctype;
    }

    public void setCtype(String ctype) {
        this.ctype = ctype;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getOperators() {
        return operators;
    }

    public void setOperators(String operators) {
        this.operators = operators;
    }

    public String getStyle_simcall() {
        return style_simcall;
    }

    public void setStyle_simcall(String style_simcall) {
        this.style_simcall = style_simcall;
    }

    public String getStyle_citynm() {
        return style_citynm;
    }

    public void setStyle_citynm(String style_citynm) {
        this.style_citynm = style_citynm;
    }
}
