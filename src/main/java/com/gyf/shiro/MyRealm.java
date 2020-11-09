package com.gyf.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyRealm extends AuthorizingRealm {

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证的
    @Override                                             //authenticationToken 里面保存的是用户输入的账号和密码
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //在这里广联数据库
        //1.获取到用户输入的账号
        String username= (String) authenticationToken.getPrincipal();

        //2.在数据库里查询账号是否存在
        /*
        if(false){
            return null;
        }
         */
        //2.2查询成功-说明账号是存在的
        //根据账号去查询密码
        String password="pwd";
        //然后把长啊后密码保存到info里面
        //SimpleAuthenticationInfo是AuthenticationInfo的子类
                                            //账号      密码      realm的名字-随便写
        return new SimpleAuthenticationInfo(username,password,"myRealm");
        
    }
}
