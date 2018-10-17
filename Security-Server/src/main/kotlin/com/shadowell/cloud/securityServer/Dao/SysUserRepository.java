package com.shadowell.cloud.securityServer.Dao;

import com.shadowell.cloud.securityServer.Model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by fengj on 2018/10/15.
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    SysUser findByUsername(String username);
}
