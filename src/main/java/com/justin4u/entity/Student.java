package com.justin4u.entity;

import java.util.List;

public class Student {

    private int id;
    private String idCard;
    private String name;
    private List<Course> courseList;
    private int deleteFlag;

    public Student() {
    }

    public Student(int id, String idCard, String name, List<Course> courseList, int deleteFlag) {
        this.id = id;
        this.idCard = idCard;
        this.name = name;
        this.courseList = courseList;
        this.deleteFlag = deleteFlag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}












































































































