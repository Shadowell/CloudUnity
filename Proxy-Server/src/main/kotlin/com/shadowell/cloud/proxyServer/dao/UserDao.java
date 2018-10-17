package com.shadowell.cloud.proxyServer.dao;


import com.shadowell.cloud.proxyServer.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<SysUser, Long>{

	SysUser findByUsername(String username);
}
