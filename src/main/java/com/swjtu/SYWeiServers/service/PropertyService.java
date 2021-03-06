package com.swjtu.SYWeiServers.service;

import com.swjtu.SYWeiServers.dto.property.PropertyDTO;
import com.swjtu.SYWeiServers.dto.property.PropertySearchRequest;
import com.swjtu.SYWeiServers.entity.Property;
import com.swjtu.SYWeiServers.web.exception.CustomException;

import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2018/3/19.
 */
public interface PropertyService {

    /**
     * 添加一个房源的信息
     * @param property
     * @return
     */
    boolean addProperty(String companyId, String dbName, Property property) throws CustomException, Exception;

    /**
     * 根据房源Id批量查找房源信息
     * @param propertyIds
     * @return
     */
    List<PropertyDTO> findPropertyByPropertyId(String companyId, String dbName, List<String> propertyIds) throws Exception;

    /**
     * 根据房源Id从数据库获取一个房源信息
     * @param companyId
     * @param dbName
     * @param roomno 房源编号
     * @return
     */
    PropertyDTO findProperty(String companyId, String dbName, String roomno) throws IOException;

    /**
     * 分页获取房源信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<PropertyDTO> getPropertyForPage(String companyId, String dbName, Integer pageNum, Integer pageSize, PropertySearchRequest request) throws Exception;

    /**
     * 根据房源id批量删除房源信息
     * @param propertyIds
     * @return
     */
    boolean deleteProperty(String companyId, String dbName, List<String> propertyIds) throws Exception;

    /**
     * 根据id更新房源信息
     * @param companyId
     * @param dbName
     * @param property
     * @return
     * @throws Exception
     */
    boolean updateProperty(String companyId, String dbName, Property property) throws Exception;

    /**
     * 获取房源量
     * @return
     * @throws Exception
     */
    Integer getPropertyNumber(String companyId, String dbName/*, PropertySearchRequest request*/) throws Exception;

    /**
     * 获取出售房源量
     * @return
     * @throws Exception
     */
    Integer getSellPropertyNumber(String companyId, String dbName) throws Exception;

    /**
     * 获取精品出售房源
     * @return
     */
    List<Property> getQualitySellProperty(String companyId, String dbName) throws Exception;

    /**
     * 获取出租房源量
     * @return
     * @throws Exception
     */
    Integer getRentPropertyNumber(String companyId, String dbName) throws Exception;

    /**
     * 获取精品出租房源
     * @return
     */
    List<Property> getQualityRentProperty(String companyId, String dbName) throws Exception;
}
