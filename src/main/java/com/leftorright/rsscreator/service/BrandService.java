package com.leftorright.rsscreator.service;


import com.leftorright.rsscreator.domain.response.ServiceResponse;
import com.leftorright.rsscreator.entity.Brand;
import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;

public interface BrandService {

    public Result saveBrand(Brand brand);

    public Result deleteBrand(int  id);

    public Result getAllBrand(PageBean pageBean);

}
