package com.swjtu.SYWeiServers.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.swjtu.SYWeiServers.entity.Employee;
import com.swjtu.SYWeiServers.mapper.EmployeeMapper;
import com.swjtu.SYWeiServers.service.EmployeeService;
import com.swjtu.SYWeiServers.util.DataSourceFactory;
import com.swjtu.SYWeiServers.util.PageResult;
import com.swjtu.SYWeiServers.util.ToolHelper;
import com.swjtu.SYWeiServers.web.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/19.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeMapper employeeMapper = null;

    @Override
    public boolean addEmployee(String companyId, String dbName,Employee employee) throws Exception {
        if(findEmployee(companyId, dbName, employee.getEmpno()) != null) {
            throw new CustomException("账号已经存在");
        }

        employee.setEmpid(ToolHelper.autoID());
        employeeMapper = DataSourceFactory.getMapper(companyId, dbName, EmployeeMapper.class);
        return employeeMapper.insert(dbName, employee) == 1;
    }

    @Override
    public Employee login(String companyId, String dbName, Employee employee) throws CustomException {
        Employee employee1 = findEmployee(companyId, dbName, employee.getEmpno());

        if(employee1 == null) {
            throw new CustomException("账号不存在");
        }

        if(!employee1.getPasswordweb().equals(employee.getPasswordweb())) {
            throw new CustomException("密码不正确");
        }

        return employee1;
    }

    @Override
    public List<Employee> findEmployeeByEmployeeNo(String companyId, String dbName, List<String> employeeNos) throws Exception {
        employeeMapper = DataSourceFactory.getMapper(companyId, dbName, EmployeeMapper.class);

        List<Employee> employees = employeeMapper.selectByExampleWithBLOBs(dbName, employeeNos);
        if(employees == null || employees.size() == 0) {
            return new ArrayList<Employee>();
        }
        return employees;
    }

    @Override
    public Employee findEmployee(String companyId, String dbName, String employeeNo) {
        employeeMapper = DataSourceFactory.getMapper(companyId, dbName, EmployeeMapper.class);
        Employee employee = null;
        List<String> employeeNos = new ArrayList<String>();
        employeeNos.add(employeeNo);
        //调用dao中方法进行数据查询
        List<Employee> employees = employeeMapper.selectByExampleWithBLOBs(dbName, employeeNos);
        if( employees != null && employees.size() != 0) {
            employee = employees.get(0);
        }

        return employee;
    }

    public PageResult getEmployeeForPage(String companyId, String dbName, Integer pageNum, Integer pageSize) throws Exception {
        employeeMapper =  DataSourceFactory.getMapper(companyId, dbName, EmployeeMapper.class);

        PageHelper.startPage(pageNum, pageSize);
        List<Employee> employees = employeeMapper.selectByExampleWithBLOBs(dbName, null);
        PageInfo<Employee> pageInfo = new PageInfo<Employee>(employees);
        PageResult pageResult = new PageResult();
        pageResult.setRows(employees);
        pageResult.setTotal(pageInfo.getTotal());
        return pageResult;
    }

    @Override
    public boolean deleteEmployee(String companyId, String dbName, List<String> employeeIds) throws Exception {
        employeeMapper = DataSourceFactory.getMapper(companyId, dbName, EmployeeMapper.class);

        return employeeMapper.deleteByExample(dbName, employeeIds) != 0;
    }

    @Override
    public boolean updateEmployee(String companyId, String dbName, Employee employee) throws Exception {
        employeeMapper =  DataSourceFactory.getMapper(companyId, dbName, EmployeeMapper.class);

        return employeeMapper.updateByExampleSelective(dbName, employee) != 0;
    }
}