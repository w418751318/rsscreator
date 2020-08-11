package com.leftorright.rsscreator.service;


import com.leftorright.rsscreator.entity.PageBean;
import com.leftorright.rsscreator.entity.Result;
import com.leftorright.rsscreator.entity.Rotation;

public interface RotationService {

    public Result saveRotation(Rotation rotation);

    public Result deleteRotation(int id);

    public Result getAllRotation(PageBean pageBean);

}
