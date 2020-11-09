package com.gyf.pojo;

public class Goods {
    private Integer g_id;
    private String g_name;
    private String g_img;

    public Goods(Integer g_id,String g_name,String g_img) {
        super();
        this.g_id = g_id;
        this.g_name = g_name;
        this.g_img = g_img;
    }

    public Goods(String g_name,String g_img){
        super();
        this.g_img = g_img;
        this.g_name = g_name;
    }

    public Goods(){
        super();
    }

    public Integer getG_id() {
        return g_id;
    }

    public void setG_id(Integer g_id) {
        this.g_id = g_id;
    }

    public String getG_img() {
        return g_img;
    }

    public void setG_img(String g_img) {
        this.g_img = g_img;
    }

    public String getG_name() {
        return g_name;
    }

    public void setG_name(String g_name) {
        this.g_name = g_name;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "g_id=" + g_id +
                ", g_name='" + g_name + '\'' +
                ", g_img='" + g_img + '\'' +
                '}';
    }
}

