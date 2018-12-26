package com.example.club.controller;

import com.example.club.config.WebSecurityConfig;
import com.example.club.domain.*;
import com.example.club.mapper.AccountMapper;
import com.example.club.mapper.ActivityMapper;
import com.example.club.mapper.AdminMapper;
import com.example.club.service.AccountService;
import com.example.club.service.ActivtyService;
import com.example.club.service.impl.AccountServiceImpl;
import com.example.club.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ApiBootController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountMapper accountMapper;


    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ActivityMapper activityMapper;


    @Autowired
    private ActivtyService activtyService;

    /**
     * @param username
     * @param password
     * @param response
     * @param session
     * @return JsonData code:0 成功
     * @ClassName login
     * /api/login
     * 处理登陆post返回
     */
    @PostMapping("login")
    public JsonData login(String username, String password, HttpServletResponse response, HttpSession session) {
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password);
        try {
            String studentId = accountService.login(account);

            if (studentId == null) {
                return JsonData.buildError("登陆失败");
            } else {
                //查询是否是社团成员
                Student student = accountMapper.getStudent(studentId);
                if (student == null) {
                    return JsonData.buildError("未能查询到该学号，请联系管理员", 1);
                } else {
                    String token = TokenUtils.createJwtToken(account.getStudentId());
                    response.addCookie(new Cookie("token", token));
                    //session
                    session.setAttribute(WebSecurityConfig.SESSION_KEY, username);


                    return JsonData.buildSuccess(student, "登陆成功");

                }

            }
        } catch (Exception e) {
            e.getMessage();
            return JsonData.buildError(e.getMessage());
        }

    }

    /**
     * @param session
     * @return
     * @ClassName logout
     * /api/logout
     * 删除session_key 并跳转login页面
     */
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
        return "redirect:/login";
    }


    @PostMapping("student")
    public JsonData student(String studentId) {
        /*
          @ClassName: student
         * 前台输入学号后，post学号进行查询是否录入该学号
         * 如果已经录入自动填充之后的信息
         * 如果学号未被录入返回提示msg提示未被录入
         *
         * studentId: 用于查询该学号的状态
         *
         * code:0 正常执行
         * code:1 尚未被录入
         * code:2 该学号已被注册
         *
         * */
        Student student = new Student();

        student = accountMapper.getStudent(studentId);
        boolean isEntry = accountService.isEntry(studentId);
        if (student == null) {
            return JsonData.buildError("该学号尚未被录入，无法提供注册服务，请联系管理人员", 1);
        } else if (!isEntry) {
            return JsonData.buildError("该学号已被注册", 2);
        } else {
            return JsonData.buildSuccess(student, "如信息有误,请直接修改");
        }

    }

    @PostMapping("studentName")
    public JsonData studentName(String studentId) {
        /*
          @ClassName: student
         * 前台输入学号后，post学号进行查询是否录入该学号
         * 如果已经录入自动填充之后的信息
         * 如果学号未被录入返回提示msg提示未被录入
         *
         * studentId: 用于查询该学号的状态
         *
         * code:0 正常执行
         * code:1 尚未被录入
         * code:2 该学号已被注册
         *
         * */
        Student student = new Student();

        student = accountMapper.getStudent(studentId);
        boolean isEntry = accountService.isEntry(studentId);
        if (student == null) {

            return JsonData.buildError("该学号尚未被录入，无法提供注册服务，请联系管理人员", 1);
        } else if (!isEntry) {
            return JsonData.buildSuccess(student, "查询成功");
        } else {
            return JsonData.buildSuccess(student, "如信息有误,请直接修改");
        }

    }

    public int getStatus(String studentId) {
        /*
          @ClassName: getStudentIdStatus
         *
         * 查询学号的状态
         *
         * studentId: 查询该学号
         *
         * code:0 正常执行
         * code:1 尚未被录入
         * code:2 该学号已被注册
         *
         * */
        Student student = new Student();

        student = accountMapper.getStudent(studentId);
        boolean isEntry = accountService.isEntry(studentId);
        if (student == null) {
            return 1;
        } else if (!isEntry) {
            return 2;
        } else {
            return 0;
        }
    }

    @PostMapping("register")
    public JsonData register(Student student) {
        String studentId = student.getStudentId();
        int code = getStatus(studentId);
        switch (code) {
            case 1:
                return JsonData.buildError("该学号尚未被录入，请联系管理员", code);
            case 2:
                return JsonData.buildError("该学号已被注册，请联系管理员", code);
            case 0:
                try {
                    accountMapper.updateStudentById(student);
                    accountService.createAccount(student);
                } catch (Exception e) {
                    return JsonData.buildError(e.getMessage());
                }
                return JsonData.buildSuccess("注册成功", code);

            default:
                return JsonData.buildError("未知错误");

        }
    }

    @PostMapping("admin")
    public JsonData adminLogin(String username, String password) {

        Permission permission = adminMapper.check(username, password);
        if (permission == null) {
            return JsonData.buildError("登陆失败");
        }

        return JsonData.buildSuccess(permission, "登陆成功");

    }

    /**
     * /api/addActivty
     * 新增一项活动
     *
     * @param activity
     * @return
     */
    @PostMapping("addActivty")
    public JsonData addActivty(Activity activity) {
        String img ="/"+ activity.getImg_src();
        activity.setImg_src(img);
        int code = activtyService.addActivity(activity);
        return JsonData.buildSuccess(activity, code);
    }


    /**
     * /api/getAllActivty
     * 查询当前所有的活动记录
     *
     * @return
     */
    @PostMapping("getAllActivty")
    public JsonData getAllActivty() {
        return JsonData.buildSuccess(activityMapper.getAll());
    }

    /**
     * /api/updateActivty
     * 根据id更新活动内容
     *
     * @param activity
     * @return 成功返回code为更新行数
     */
    @PostMapping("updateActivty")
    public JsonData updateActivty(Activity activity) {
        try {
            int line = activityMapper.updateActivity(activity);
            if (line == 0) {
                return JsonData.buildError("更新活动失败");
            } else {
                return JsonData.buildSuccess("成功更新" + line + "行数据", line);
            }

        } catch (DataIntegrityViolationException e) {
            return JsonData.buildError("字段过长");
        }
    }

    /**
     * 根据id删除活动
     *
     * @param id 对应数据库中的id字段
     * @return
     */
    @PostMapping("deleteActivityById")
    public JsonData deleteActivityById(int id) {

        try {
            int lines = activityMapper.deleteActivityById(id);
            return JsonData.buildSuccess("成功删除" + lines + "行数据", lines);
        } catch (Exception e) {
            return JsonData.buildError(e.getMessage());
        }


    }

    @PostMapping("getActivityById")
    public JsonData getActivityById(int id) {
        return JsonData.buildSuccess(activtyService.getActivityById(id));
    }

    /**
     * 获得最新的活动
     * @param top
     * @return
     */
    @PostMapping("getNewTop")
    public JsonData getNew(int top) {
        return JsonData.buildSuccess(activityMapper.getNew(top));
    }

    @PostMapping("getPage")
      public JsonData getPage(int lines,int start) {
        return JsonData.buildSuccess(activityMapper.getPage(lines,start));
    }

}
