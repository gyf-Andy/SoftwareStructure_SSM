package com.gyf.service.impl;

import com.gyf.mapper.AdminMapper;
import com.gyf.pojo.Admin;
import com.gyf.pojo.Classify;
import com.gyf.pojo.Commodity;
import com.gyf.pojo.FoodName;
import com.gyf.result.ResultObject;
import com.gyf.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminMapper adminMapper;
	
	public ResultObject login(String account,String password) {

		Admin admin=adminMapper.findAdminByAccountAndPwd(account, password);
		if (admin!=null&&admin.getAccount().equals(account)&&admin.getPassword().equals(password)) {
			return new ResultObject(200,"登陆成功",admin);
		}
		return null;
	}


	public ResultObject searchAdminInfo() {
		List<Admin> list=new ArrayList<Admin>();
		list=adminMapper.searchAdminInfo();
		return new ResultObject(200,"查询到信息",list);
	}

	public ResultObject isHadTheUser(String name) {

		int account=adminMapper.isHadTheUser(name);
		if (account!=0) {
			return new ResultObject(200, "用户存在", null);
		}else{
			return new ResultObject(201,"用户不存在",null);
		}

	}

	public  ResultObject searchAllClassify(){
		List<Classify> classifyList=adminMapper.searchAllClassify();
		return new ResultObject(200,"查询成功", classifyList);
	}

	public ResultObject searchFoodNameById(int classifyId) {
		List<FoodName> foodNameList=adminMapper.searchFoodNameById(classifyId);
		return new ResultObject(200,"成功获得食物的数据",foodNameList);
	}

	public ResultObject getCommodityData() {
		List<Commodity> commodityList=adminMapper.getCommodityData();
		return new ResultObject(200,"获得数据",commodityList);
	}


}
