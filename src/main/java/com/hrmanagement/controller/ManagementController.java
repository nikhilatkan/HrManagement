package com.hrmanagement.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hrmanagement.dao.HrDao;
import com.hrmanagement.entities.EmployeeEntity;
import com.hrmanagement.entities.HrEntity;
import com.hrmanagement.service.UserService;
import com.hrmanagement.service.APICaller;
import com.hrmanagement.service.EmployeeService;

@Controller
public class ManagementController {

    @Autowired
    private APICaller apiCaller;
    @Autowired
    private UserService userService;
    @Autowired
    private HrDao dao;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest request, @RequestParam("name") String name,
            @RequestParam("username") String username, @RequestParam("password") String password) {
        ModelAndView mv = new ModelAndView();
        HttpSession httpSession = request.getSession(false);
        if (httpSession.getAttribute("user") == null) {

            userService.addUser(username, name, password);
            mv.setViewName("login.jsp");
            return mv;
        } else {
            List<EmployeeEntity> list = employeeService.getEmployees();
            mv.addObject("list", list);
            mv.setViewName("welcome.jsp");
            return mv;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, @RequestParam("username") String username,
            @RequestParam("pass") String password) {

        ModelAndView mv = new ModelAndView();
        HrEntity user = dao.getUser(username);

        HttpSession httpSession = request.getSession(true);
        HttpSession logoutSession = request.getSession(false);

        try {

            if (userService.authenticator(username, password)) {
                List<EmployeeEntity> list = employeeService.getEmployees();
                if (list == null) {
                    mv.addObject("list", new ArrayList<EmployeeEntity>());
                } else {
                    mv.addObject("list", list);
                }
                httpSession.setAttribute("user", user);
                httpSession.setAttribute("userName", user.getName());
                mv.setViewName("welcome.jsp");
                return mv;
            }

            mv.setViewName("login.jsp");
            return mv;

        } catch (Exception e) {
            mv.setViewName("login.jsp");
            return mv;
        }
    }

    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest request, @RequestParam("employeeId") String employeeId) {
        ModelAndView mv = new ModelAndView();
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null || httpSession.getAttribute("user") == null) {
            mv.setViewName("login.jsp");
            return mv;
        } else {

            mv.addObject("employeeId", employeeId);
            mv.setViewName("editpage.jsp");
            return mv;
        }
    }

    @RequestMapping("/add")
    public ModelAndView addpage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null || httpSession.getAttribute("user") == null) {
            mv.setViewName("login.jsp");
            return mv;
        } else {
            mv.setViewName("addemployeepage.jsp");
            return mv;
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView adduser(HttpServletRequest request, @RequestParam("employeeId") int employeeId,
            @RequestParam("employeeName") String employeeName, @RequestParam("location") String location,
            @RequestParam("email") String email, @RequestParam("dateofbirth") String dateOfBirth)
            throws ParseException {
        ModelAndView mv = new ModelAndView();
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null || httpSession.getAttribute("user") == null) {
            mv.setViewName("login.jsp");
            return mv;
        } else {
            DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date formatdate = inputFormat.parse(dateOfBirth);
            String DOB = outputFormat.format(formatdate);
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setEmployeeName(employeeName);
            employeeEntity.setEmail(email);
            employeeEntity.setEmployeeDOB(DOB);
            employeeEntity.setLocation(location);
            employeeEntity.setEmployeeId(employeeId);
            apiCaller.addEmployee(employeeEntity);

            List<EmployeeEntity> list = employeeService.getEmployees();
            mv.addObject("list", list);
            mv.setViewName("welcome.jsp");

            return mv;
        }
    }

    @RequestMapping("/editUser")
    public ModelAndView editUser(@RequestParam("employeeId") int employeeId,
            @RequestParam("employeeName") String employeeName, @RequestParam("location") String location,
            @RequestParam("email") String email, @RequestParam("date") String date) {
        ModelAndView mv = new ModelAndView();
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date formatdate = null;
        try {
            formatdate = inputFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String DOB = outputFormat.format(formatdate);
        EmployeeEntity employee = new EmployeeEntity();
        employee.setEmployeeName(employeeName);
        employee.setEmployeeId(employeeId);
        employee.setEmployeeDOB(DOB);
        employee.setEmail(email);
        employee.setLocation(location);
        apiCaller.updateEmployee(employee, employeeId);
        List<EmployeeEntity> list = employeeService.getEmployees();
        mv.addObject("list", list);
        mv.setViewName("welcome.jsp");

        return mv;

    }

    @RequestMapping("/download")
    public void downloadList(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String file = apiCaller.fetchAllEmployeesFile();
        InputStream stream = new ByteArrayInputStream(file.getBytes(StandardCharsets.UTF_8));
        PrintWriter out = response.getWriter();
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename=employeesInfo.txt");
        int i;
        while ((i = stream.read()) != -1) {
            out.write(i);
        }
        stream.close();
        out.close();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        HttpSession httpSession = request.getSession(false);
        httpSession.removeAttribute("user");
        httpSession.invalidate();

        mv.setViewName("login.jsp");
        return mv;
    }

}
