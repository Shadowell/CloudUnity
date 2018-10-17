package com.shadowell.cloud.securityServer.Dao;

import com.shadowell.cloud.securityServer.Model.SysUser;


public interface AuthService {

    SysUser register(SysUser userToAdd);
    String login(String username, String password);
    String refresh(String oldToken);

}
