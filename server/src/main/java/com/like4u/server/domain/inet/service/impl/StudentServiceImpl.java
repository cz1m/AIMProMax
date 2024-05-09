package com.like4u.server.domain.inet.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.like4u.server.domain.inet.repository.StudentMapper;
import com.like4u.server.domain.inet.service.StudentService;
import com.like4u.server.infrastructrue.po.SPage;
import com.like4u.server.infrastructrue.po.Student;
import com.like4u.server.infrastructrue.po.StudentQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/3/29 22:42
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student>
        implements StudentService {
    @Autowired
    public StudentMapper studentMapper;

    @Override
    public String getNameById(Long id) {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getId, id);
        return studentMapper.selectOne(wrapper).getName();
    }

    @Override
    public List<Student> getAllStudent() {
        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        return studentMapper.selectList(wrapper);
    }

    @Override
    public int insertStudent(Student student) {

        return studentMapper.insert(student);
    }

    @Override
    public SPage<Student> searchStudent(StudentQuery query) {

        if (query.getPageNumber() == null || query.getPageSize() == null) {
            query.setPageNumber(1);
            query.setPageSize(5);

        }
        Page<Student> studentPage = new Page<>();
        studentPage.setCurrent(query.getPageNumber())
                .setSize(query.getPageSize());
        Integer[] age = query.getAge();

        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();

        wrapper.like(StringUtils.hasLength(query.getName()), Student::getName, query.getName())
                .eq(StringUtils.hasLength(query.getSex()), Student::getSex, query.getSex());
        if (age != null && age.length > 0) {
            if (age.length ==1){
                wrapper.eq(Student::getAge,age[0]);
            }else {
                wrapper.ge(Student::getAge, age[0])
                        .lt(Student::getAge, age[age.length - 1]);
            }

        }

        Page<Student> result = studentMapper.selectPage(studentPage, wrapper);


        return new SPage(result.getRecords(), result.getTotal());
    }

    @Override
    public int deleteStudent(Integer id) {

        return studentMapper.deleteById(id);
    }

    @Override
    public int deleteStudents(int[] students) {
        int count = 0;
        for (Integer id : students) {
            count+=studentMapper.deleteById(id);
        }
        return count;
    }

    @Override
    public int updateStudent(Student student) {
        LambdaUpdateWrapper<Student> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(Student::getId, student.getId());
        updateWrapper.set(Student::getName,student.getName());
        updateWrapper.set(Student::getSex,student.getSex());
        updateWrapper.set(Student::getAge,student.getAge());
        return studentMapper.update(student,updateWrapper);

    }

    @Override
    public Student whoImI(String username) {

        LambdaQueryWrapper<Student> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Student::getName, username);
        return studentMapper.selectOne(wrapper);

    }
}
