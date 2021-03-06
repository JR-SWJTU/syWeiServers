package com.swjtu.SYWeiServers.mapper;

import com.swjtu.SYWeiServers.dto.EstateSearchRequest;
import com.swjtu.SYWeiServers.entity.Estate;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EstateMapper {
    int countByExample(@Param("dbName")String dbName);

    int insert(@Param("dbName")String dbName, @Param("entity")Estate record);

    List<Estate> selectForPage(@Param("dbName")String dbName, @Param("start")int start, @Param("pageSize")int pageSize,
                               @Param("request")EstateSearchRequest request);

    List<Estate> selectByExampleWithBLOBs(@Param("dbName")String dbName, @Param("estateIds")List<String> estateIds);

    Estate selectEstate(@Param("dbName")String dbName, @Param("estateName")String estateName, @Param("address")String address);

    int deleteByExample(@Param("dbName")String dbName,  @Param("estateIds")List<String> estateIds);

    int updateByExampleSelective(@Param("dbName")String dbName,@Param("record") Estate record);
    
}