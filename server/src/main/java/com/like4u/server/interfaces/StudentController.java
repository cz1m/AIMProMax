package com.like4u.server.interfaces;
import com.like4u.server.domain.inet.service.StudentService;
import com.like4u.server.domain.inet.service.UserInfoService;
import com.like4u.server.infrastructrue.comon.AjaxResult;
import com.like4u.server.infrastructrue.po.SPage;
import com.like4u.server.infrastructrue.po.Student;
import com.like4u.server.infrastructrue.po.StudentQuery;
import com.like4u.server.infrastructrue.po.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/3/29 22:58
 */
@Controller
@RequestMapping("/api")
public class StudentController {
    @Autowired
    public StudentService service;
    @Autowired
    public UserInfoService userInfoService ;

    @GetMapping("/allstudent")
    @ResponseBody
    public List<Student> getAllStudent(){
        return service.getAllStudent();
    }
    @PostMapping( "/student")
    @ResponseBody
    public AjaxResult addStudent(@Validated @RequestBody Student student){
        System.out.println(student);
        int count = service.insertStudent(student);
        if (count !=0)return AjaxResult.success("添加成功");
        return AjaxResult.error("添加失败");

    }
    @GetMapping("/student/q")
    @ResponseBody
    public AjaxResult searchStudent(StudentQuery sq){

        SPage<Student> sPage = service.searchStudent(sq);
        return AjaxResult.success(null,sPage);
    }
    @DeleteMapping("student/{id}")
    @ResponseBody
    public AjaxResult deleteStudent(@PathVariable Integer id){

        int deleted = service.deleteStudent(id);
        if (deleted !=0) return AjaxResult.success("删除成功");
        return AjaxResult.error("删除失败");

    }
    @DeleteMapping("student/")
    @ResponseBody
    public  AjaxResult deleteStudents(@RequestBody int[] ids){
        int changeRows = service.deleteStudents(ids);
        return AjaxResult.success(changeRows);
    }

    @PutMapping("/student")
    @ResponseBody
    public AjaxResult updateStudent(@Validated  @RequestBody Student student){

        int rows= service.updateStudent(student);
        if (rows!=0){
            return AjaxResult.success("修改成功");
        }
        return AjaxResult.error("修改失败");
    }
    @GetMapping("/info/{username}")
    public AjaxResult userInfo(@PathVariable String username){
        Student student=service.whoImI(username);
        return AjaxResult.success(student);
    }
    @PostMapping("/info")
    public AjaxResult userInfo(@RequestBody UserInfoDto userInfoDto){
        try{
            System.out.println(userInfoDto);
            userInfoService.updateUserInfo(userInfoDto);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        return AjaxResult.success("更新成功");
    }

}
