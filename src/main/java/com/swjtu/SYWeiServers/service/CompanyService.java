package com.swjtu.SYWeiServers.service;

import com.swjtu.SYWeiServers.entity.Company;
import com.swjtu.SYWeiServers.web.exception.CustomException;

import java.util.List;

/**
 * Created by Administrator on 2018/3/19.
 */
public interface CompanyService {

    /**
     * 添加一个公司的信息
     * @param company
     * @return
     */
    boolean addCompany(Company company) throws CustomException, Exception;

    /**
     * 公司登陆操作
     * @param company
     * @return
     */
    Company login(Company company) throws CustomException;

    /**
     * 根据公司No批量查找公司信息
     * @param companyNos
     * @return
     */
    List<Company> findCompanyByCompanyNo(List<String> companyNos) throws Exception;

    /**
     * 根据公司id从数据库获取一个公司信息
     * @param companyNo
     * @return
     */
    Company findCompany(String companyNo);

    /**
     * 根据数据库名称获取一个公司信息
     * @param dbName
     * @return
     */
    Company findCompanyByDbName(String dbName);

    /**
     * 分页获取公司信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Company> getCompanyForPage(Integer pageNum, Integer pageSize) throws Exception;

    /**
     * 根据公司id批量删除公司信息
     * @param companyIds
     * @return
     */
    boolean deleteCompany(List<String> companyIds) throws Exception;

    /**
     * 更新公司信息
     * @param company
     * @return
     * @throws Exception
     */
    boolean updateCompany(Company company) throws Exception;

    /**
     * 获取当前用户量
     * @return
     * @throws Exception
     */
    Integer getCommanyNumber() throws Exception;
}
