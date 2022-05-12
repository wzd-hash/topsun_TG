package com.topsun.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.topsun.entity.Location;
import com.topsun.entity.Roules;
import com.topsun.service.LocationService;
import com.topsun.util.FileUploadUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @description 区域控制类
 * @author: wzd
 * @create: 2021-11-25 16:06
 * @Version 1.0
 **/
@RestController
@RequestMapping("/location")
public class LocationController extends BaseController{

    @Value("${photo.url}")
    private String fileUrl;

    @Autowired
    private LocationService locationService;


    @RequestMapping("/listLocationByType")
    public  Object listLocationByType(HttpServletRequest request){

        Map<String, Object> map = getSessionInfo();
        String areaType = request.getParameter("areaType");
        String areaName = request.getParameter("areaName");
        String useType = request.getParameter("useType");
        //土场是否有图片 1-有图片  2-无图片
        String state = request.getParameter("state");
        String page = request.getParameter("page");
        String limit = request.getParameter("limit");

        map.put("areaType", areaType);
        map.put("areaName", areaName);
        map.put("useType", useType);
        map.put("state", state);


        Page pageHelper = new Page<>();
        if (StringUtils.isNotEmpty(page) && StringUtils.isNotEmpty(limit)) {
            pageHelper = PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(limit));
        }

        List<Location> list = locationService.listLocationByType(map);

        map.clear();
        map.put("list", list);
        map.put("total", pageHelper.getTotal());
        map.put("code", 0);
        return map;
    }

    @RequestMapping("/uploadFile")
    public String uploadFile(MultipartFile file) throws IOException {

        String uploadPath = null;
        if(file != null){
            uploadPath = FileUploadUtil.fileUpload(file, fileUrl);
        }
        return uploadPath;
    }

    @RequestMapping("/addLocationByType")
    public  Object addLocationByType(Location location) {
        Map<String, Object> map = getSessionInfo();

        Integer result = locationService.insertLocation(location);

        map.clear();
        if (result <= 0 ){
            map.put("code", 1);
            map.put("msg", "新增失败");
        }

        map.put("code", 0);
        map.put("msg", "新增成功");
        return map;
    }


    @RequestMapping("/updateLocationByType")
    public  Object updateLocationByType(Location location) {
        Map<String, Object> map = getSessionInfo();

        Integer result = locationService.updateLocationByType(location);

        map.clear();
        if (result <= 0 ){
            map.put("code", 1);
            map.put("msg", "更新失败");
        }

        map.put("code", 0);
        map.put("msg", "更新成功");
        return map;
    }

    @RequestMapping("delLocation")
    public Object delLocation(Integer ID){
        Map<String, Object> map = getSessionInfo();

        Integer result = locationService.delLocation(ID);

        map.clear();
        if (result <= 0){
            map.put("code", 1);
            map.put("msg", "删除失败");
        }

        map.put("code", 0);
        map.put("msg", "删除成功");

        return map;
    }

    @RequestMapping("/getCompanyID")
    public Object getCompanyID(HttpServletRequest request){

        Map<String, Object> map = getSessionInfo();
        String name = request.getParameter("name");

        map.put("name", name);
        List<Roules> list = locationService.getCompanyID(map);

        map.clear();
        map.put("list", list);

        return map;
    }

}
