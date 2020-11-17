package com.itheima.mm.dao;

import com.itheima.mm.pojo.Catalog;

import java.util.List;

/**
 * @Author :fuwei
 * @Date:2020/11/3 16:45
 * @Version 1.0
 */
public interface CatalogDao {
   Long findById(Integer id);
   List<Catalog> findCatalogListByCourseId(Integer courseId);
}
