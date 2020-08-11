package com.leftorright.rsscreator.service.Imp;


import com.leftorright.rsscreator.dao.PastCasesMapper;
import com.leftorright.rsscreator.entity.*;
import com.leftorright.rsscreator.repository.PodcastInfoRepository;
import com.leftorright.rsscreator.service.PastCasesService;
import com.leftorright.rsscreator.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PastCasesServiceImpl implements PastCasesService {
    private static final Logger logger = LoggerFactory.getLogger(PastCasesServiceImpl.class);

    @Autowired
    private PastCasesMapper pastCasesMapper;
    @Autowired
    private PodcastInfoRepository podcastInfoRepository;

    @Override
    public Result savePast(PastCases pastCases) {
        Result result = new Result();
        try {
            String type = pastCases.getType();
            if(type.equals("2")){
                PodcastInfo podcastInfo = pastCases.getPodcastInfo();
                FileUtils.setFileUrl(podcastInfo);
                PodcastInfo save = podcastInfoRepository.save(pastCases.getPodcastInfo());
                pastCases.setPodcastid(String.valueOf(save.getId()));
            }
            pastCasesMapper.savePastCases(pastCases);
            result.setCode(0);
            result.setData("成功");
        } catch (Exception e) {
            logger.error("保存过往案例失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result deletePast(int id) {
        Result result = new Result();
        try {
            int i = pastCasesMapper.deletePastCases(id);
            result.setCode(0);
            result.setData("成功");
        } catch (Exception e) {
            logger.error("删除过往案例失败：",e);
            result.setCode(1);
            result.setData("失败");
        }
        return result;
    }

    @Override
    public Result getAllPast(PageBean pageBean) {
        Result result = new Result();
        try {
            List<PastCases> allPastCases = pastCasesMapper.getAllPastCases(pageBean);
            int count = pastCasesMapper.getAllCount(pageBean);
            Map<String,Object> map = new HashMap<>();
            map.put("list",allPastCases);
            map.put("total",count);
            result.setCode(0);
            result.setData(map);
        } catch (Exception e) {
            logger.error("查询过往案例失败：",e);
            result.setCode(1);
            result.setMsg("失败");
        }
        return result;
    }

}
