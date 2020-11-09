package com.gyf.pojo;

public class FoodName {

    private String foodName;

    private int classifyId;

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Override
    public String toString() {
        return "FoodName{" +
                "foodName='" + foodName + '\'' +
                ", classifyId=" + classifyId +
                '}';
    }
}
