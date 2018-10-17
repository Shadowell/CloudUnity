package com.shadowell.cloud.proxyServer.service;

import com.shadowell.cloud.proxyServer.entity.Blog;

import java.util.List;

/**
 * Created by fangzhipeng on 2017/5/15.
 */
public interface IBlogService {
    List<Blog> getBlogs();
    void deleteBlog(long id);
}
