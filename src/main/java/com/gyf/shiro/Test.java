package com.gyf.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class Test {

    public static void main(String[] args){

        IniSecurityManagerFactory factory=
                new IniSecurityManagerFactory("classpath:com/gyf/shiro/shiro_myRealm.ini");

        //从工厂里面获取SecurityManager
        SecurityManager securityManager=factory.getInstance();

        //蒋SecurityManager设置到运行环境中去
        SecurityUtils.setSecurityManager(securityManager);

        //创建Subject  ini文件里面的
        Subject subject=SecurityUtils.getSubject();

        //创建token  用户的账号密码   自己输入的
        UsernamePasswordToken  token=new UsernamePasswordToken("user1", "user1");

        /**
         * 如果token和ini文件里面的账号密码匹配，不报错
         * 		如果不匹配，会产生异常： org.apache.shiro.authc.IncorrectCredentialsException
         */
        try{
            subject.login(token);   //从这里开始执行
        }catch (IncorrectCredentialsException e) {
            // TODO: handle exception
            System.out.println("认证失败");
        }

        //是否认证成功
        boolean b=subject.isAuthenticated();
        System.out.println("是否认证成功"+b);


        //退出
        subject.logout();  //清空账号密码
        boolean b2=subject.isAuthenticated();
        System.out.println("退出后是否认证成功"+b2);




    }
}



