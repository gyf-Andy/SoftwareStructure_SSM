package com.gyf.mapper;

import com.gyf.pojo.Admin;
import com.gyf.pojo.Classify;
import com.gyf.pojo.Commodity;
import com.gyf.pojo.FoodName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {

    public Admin findAdminByAccountAndPwd(@Param("account")String account, @Param("password")String password);
    public List<Admin> searchAdminInfo();
    public int isHadTheUser(String name);
    public List<Classify> searchAllClassify();
    public List<FoodName> searchFoodNameById(int classifyId);
    public List<Commodity> getCommodityData();

}
