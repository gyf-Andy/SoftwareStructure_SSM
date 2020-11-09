package com.gyf.controller;

import com.gyf.pojo.Commodity;
import com.gyf.result.ResultObject;
import com.gyf.service.AdminService;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/admin")
@SessionAttributes("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/toLoginPage.action")
    public String toLoginPage() {

        return "/admin/login";
    }

    @RequestMapping("/login.action")
    public String login(HttpServletRequest request, Model model) {
        String account=request.getParameter("account");
        String password=request.getParameter("password");
        //Admin admin=adminService.login(account, password);
        ResultObject resultObject=adminService.login(account,password);
        //若有user则添加到model里并且跳转到成功页面
        if(resultObject.getCode() == 200){
            model.addAttribute("admin",resultObject);
            return "/admin/addCommodity";
        }
        return "/admin/login";
    }

    //查询admin中的所有数据
    @RequestMapping("/searchAdminInfo.action")
    public String searchAdminInfo(Model model){
        ResultObject resultObject=adminService.searchAdminInfo();
        model.addAttribute("admin",resultObject);
        return "/admin/addCommodity";
    }

    //用Ajax实现查看数据库是否有指定账号
    @ResponseBody     //返回的是json数据
    @RequestMapping("/isHadTheUser.action")
    public ResultObject isHadTheUser(String name){
        ResultObject resultObject=adminService.isHadTheUser(name);
        System.out.println(resultObject);
        return  resultObject;

    }

    //查询分类
    @ResponseBody
    @RequestMapping("/searchAllClassify.action")
    public  ResultObject searchAllClassify(){
        ResultObject resultObject=adminService.searchAllClassify();
        //遍历resultObject中data（也就是mybatis传过来的list集合，集合中储存的是Classify对象，对每一个Classify对象的内容进行遍历）
        System.out.println(resultObject);
        return resultObject;
    }

    @ResponseBody
    @RequestMapping("/find.action")
    //根据id获得食物分类
    public ResultObject searchFoodNameById(int classifyId){
        ResultObject resultObject=adminService.searchFoodNameById(classifyId);
        return resultObject;
    }

    @ResponseBody
    @RequestMapping("/getCommodityData.action")
    public ResultObject getCommodityData(){
        ResultObject resultObject=adminService.getCommodityData();
        return  resultObject;
    }

    //查询到commodity表中的所有数据并下载到指定文件夹下
    @ResponseBody
    @RequestMapping("/getCommodityAll.action")
    public ResultObject getCommodityAll() throws IOException {
        ResultObject resultObject=adminService.getCommodityData();
        List<Commodity> commodityList=(List<Commodity>) resultObject.getData();

        HSSFWorkbook hw=new HSSFWorkbook();
        HSSFSheet sheet=hw.createSheet("食品信息");
        HSSFRow r0=sheet.createRow(0);
        r0.createCell(0).setCellValue("食品信息表");

        //表的列名
        HSSFRow r1=sheet.createRow(1);
        r1.createCell(0).setCellValue("商品名");
        r1.createCell(1).setCellValue("商品数量");
        r1.createCell(2).setCellValue("商品价格");

        for (int i=0;i<commodityList.size();i++){

            HSSFRow row=sheet.createRow(i+2);

            row.createCell(0).setCellValue(commodityList.get(i).getCommodityName());
            row.createCell(1).setCellValue(commodityList.get(i).getCommodityNumber());
            row.createCell(2).setCellValue(commodityList.get(i).getCommodityPrice());
        }

        //输出
        OutputStream out=new FileOutputStream("E:\\POI技术输入输出表格实验文件夹\\1.xls");
        hw.write(out);
        //System.out.println(commodityList.get(1));
        return new ResultObject(200,"下载成功",null);
    }
}
