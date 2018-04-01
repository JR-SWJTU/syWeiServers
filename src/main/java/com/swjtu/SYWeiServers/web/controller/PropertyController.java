package com.swjtu.SYWeiServers.web.controller;

import com.swjtu.SYWeiServers.entity.Property;
import com.swjtu.SYWeiServers.service.PropertyService;
import com.swjtu.SYWeiServers.util.JsonResult;
import com.swjtu.SYWeiServers.util.enums.StatusCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Propertyistrator on 2017/10/18.
 */
@Controller
@RequestMapping("properties")
@ResponseBody
public class PropertyController {

    @Resource
    private PropertyService propertyService;

    @RequestMapping(value="/addProperty", method = RequestMethod.POST)
    public JsonResult register(@RequestBody Property property, String companyId, String dbName) throws  Exception{
        boolean res = propertyService.addProperty(companyId, dbName, property);
        return JsonResult.build(StatusCode.SUCCESS, res ? 1 : 0);
    }

    /**批量删除房源*/
    @RequestMapping(value = "/deleteProperty", method = RequestMethod.POST)
    public JsonResult deleteProperty(@RequestBody Map map, String companyId, String dbName) throws Exception {
        List ids =  (List)map.get("ids");
        boolean res = propertyService.deleteProperty(companyId, dbName, ids);
        return JsonResult.build(StatusCode.SUCCESS, res ? 1 : 0);
    }

    /**
     * 分页查询房源信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public JsonResult queryPropertys(Integer pageNum, Integer pageSize, String companyId, String dbName) throws Exception {
        List<Property> propertys = propertyService.getPropertyForPage(companyId, dbName, pageNum, pageSize);
        return JsonResult.build(StatusCode.SUCCESS, propertys);
    }

    /**
     * 更新房源信息
     * @param property
     * @return
     */
    @RequestMapping(value = "/updateProperty", method = RequestMethod.POST)
    public JsonResult updateProperty(@RequestBody Property property, String companyId, String dbName) throws Exception{
        return JsonResult.build(StatusCode.SUCCESS,  propertyService.updateProperty(companyId, dbName, property) ? 1 : 0);
    }

}