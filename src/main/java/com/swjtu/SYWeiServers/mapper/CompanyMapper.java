package com.swjtu.SYWeiServers.mapper;

import com.swjtu.SYWeiServers.entity.Company;
import com.swjtu.SYWeiServers.entity.CompanyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyMapper {
    int countByExample(CompanyExample example);

    int deleteByExample(CompanyExample example);

    int insert(Company record);

    int insertSelective(Company record);

    List<Company> selectByExampleWithBLOBs(CompanyExample example);

    List<Company> selectForPage( @Param("start")int start, @Param("pageSize")int pageSize);

    List<Company> selectByExample(CompanyExample example);

    int updateByExampleSelective(@Param("record") Company record, @Param("example") CompanyExample example);

    int updateByExampleWithBLOBs(@Param("record") Company record, @Param("example") CompanyExample example);

    int updateByExample(@Param("record") Company record, @Param("example") CompanyExample example);
}