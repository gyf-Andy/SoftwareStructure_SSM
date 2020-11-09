package com.gyf.poiAndFastDFS;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.IOException;

public class FastDFSTest {
    public static void main(String[] args) throws IOException, MyException {

        //1.加载配置文件
        ClientGlobal.init("C:\\Users\\22413\\IdeaProjects\\SoftwareStructure_SSM\\src\\main\\resources\\fastdfs_client.properties");
        //2.创建管理端对象
        TrackerClient trackerClient=new TrackerClient();
        //3.通过管理端对象获取链接
        TrackerServer connection=trackerClient.getConnection();
        //4.创建储存对象
        StorageClient1 storageClient1=new StorageClient1(connection,null);

        //创建文件属性对象信息数组
        NameValuePair[] meta_list=new NameValuePair[1];
        meta_list[0] =new NameValuePair("filename","王冉旭秀发.PNG");

        //5.上传文件
        String path=storageClient1.upload_file1("C:\\Users\\22413\\Pictures\\王冉旭秀发.PNG" ,"PNG",meta_list);
        System.out.println("====="+path);
    }
}
