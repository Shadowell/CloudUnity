package com.shadowell.cloud.securityServer.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by fengj on 2018/10/15.
 */
public enum SysRole{
    /**
     * ROLE_USER:普通用户
     */
    ROLE_USER ,
    /**
     * ROLE_ADMIN:管理员用户
     */
    ROLE_ADMIN
}
//@Entity
//public class SysRole {
//
//    @Id
//    @GeneratedValue
//    private Long id;
//    private String name;
//
//    public SysRole(String name){
//        this.name = name;
//    }
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//}
