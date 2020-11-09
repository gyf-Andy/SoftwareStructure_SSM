package com.gyf.service;


import com.gyf.result.ResultObject;

public interface AdminService {

	public ResultObject login(String account, String admin);

	public ResultObject searchAdminInfo();

	public ResultObject isHadTheUser(String name);

	public ResultObject searchAllClassify();

	//试一下用List集合返回数据给前端  正常情况下要用自定义的ResultObject来接收数据并返回
	//public List<FoodName> searchFoodNameById(int classifyId);

	public ResultObject searchFoodNameById(int classifyId);

	public ResultObject getCommodityData();

}
