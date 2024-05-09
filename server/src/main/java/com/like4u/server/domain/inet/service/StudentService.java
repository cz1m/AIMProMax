package com.like4u.server.domain.inet.service;


import com.like4u.server.infrastructrue.po.SPage;
import com.like4u.server.infrastructrue.po.Student;
import com.like4u.server.infrastructrue.po.StudentQuery;

import java.util.List;

/**
 * @author Zhang Min
 * @version 1.0
 * @Date 2024/3/29 22:42
 */

public interface StudentService {
    String getNameById(Long id);

    List<Student> getAllStudent();

    int insertStudent(Student student);

    SPage<Student> searchStudent(StudentQuery query);

    int deleteStudent(Integer id);

    int deleteStudents(int[] students);

    int updateStudent(Student student);

    Student whoImI(String username);
}
