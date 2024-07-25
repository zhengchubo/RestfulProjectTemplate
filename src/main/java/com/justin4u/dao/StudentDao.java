package com.justin4u.dao;

import com.justin4u.entity.Student;

public interface StudentDao {
    public Student findStudentById(String idCard);
}
