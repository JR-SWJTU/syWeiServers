package com.swjtu.SYWeiServers.web.controller;

import com.swjtu.SYWeiServers.entity.Employee;
import com.swjtu.SYWeiServers.service.EmployeeService;
import com.swjtu.SYWeiServers.util.JsonResult;
import com.swjtu.SYWeiServers.util.PageResult;
import com.swjtu.SYWeiServers.util.enums.StatusCode;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Employeeistrator on 2017/10/18.
 */
@Controller
@RequestMapping("employees")
@ResponseBody
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public JsonResult register(@RequestBody Employee employee, String companyId, String dbName) throws  Exception{
        boolean res = employeeService.addEmployee(companyId, dbName, employee);
        return JsonResult.build(StatusCode.SUCCESS, res ? 1 : 0);
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public JsonResult login( @RequestBody Employee employee, String companyId, String dbName) throws  Exception{
        Employee employee1 = employeeService.login(companyId, dbName, employee);
        JSONObject jsonObject = JSONObject.fromObject(employee1);
        jsonObject.remove("password");
        employee1 = (Employee) JSONObject.toBean(jsonObject, Employee.class);
        return JsonResult.build(StatusCode.SUCCESS, employee1);
    }

    /**批量删除员工*/
    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.POST)
    public JsonResult deleteEmployee(@RequestBody Map map, String companyId, String dbName) throws Exception {
        List ids =  (List)map.get("ids");
        boolean res = employeeService.deleteEmployee(companyId, dbName, ids);
        return JsonResult.build(StatusCode.SUCCESS, res ? 1 : 0);
    }

    /**
     * 分页查询员工信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public JsonResult queryEmployees(Integer pageNum, Integer pageSize, String companyId, String dbName) throws Exception {
        PageResult employees = employeeService.getEmployeeForPage(companyId, dbName, pageNum, pageSize);
        return JsonResult.build(StatusCode.SUCCESS, employees);
    }

    /**
     * 更新员工信息
     * @param employee
     * @return
     */
    @RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
    public JsonResult updateEmployee(@RequestBody Employee employee, String companyId, String dbName) throws Exception{
        return JsonResult.build(StatusCode.SUCCESS,  employeeService.updateEmployee(companyId, dbName, employee) ? 1 : 0);
    }

}