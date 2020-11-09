package com.gyf.pojo;

public class Classify {
    int id;
    String classifyName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "id=" + id +
                ", classifyName='" + classifyName + '\'' +
                '}';
    }
}
