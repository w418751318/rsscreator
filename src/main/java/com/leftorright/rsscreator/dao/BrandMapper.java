package com.leftorright.rsscreator.dao;

import com.leftorright.rsscreator.entity.Brand;
import com.leftorright.rsscreator.entity.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BrandMapper {

    int saveBrand(Brand brand);

    int updateBrand(Brand brand);


    int deleteBrand(@Param("id") int id);

    List<Brand> getAllBrand(PageBean pageBean);

    int getAllBrandCount();

}
