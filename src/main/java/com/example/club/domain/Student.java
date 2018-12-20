package com.example.club.domain;

public class Student extends Account{

    private String studentId;
    private String departmentName;
    private String studentName;
    private String className;

    @Override
    public String getStudentId() {
        return studentId;
    }

    @Override
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
