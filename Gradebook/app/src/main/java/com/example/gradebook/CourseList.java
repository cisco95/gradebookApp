package com.example.gradebook;

import java.util.*;

public class CourseList {
    private ArrayList<Course> semesterCourses;
    private int courseIterator = 0;

    public CourseList(ArrayList<Course> semesterCourses) {
        this.semesterCourses = (ArrayList<Course>) semesterCourses.clone();
    }

    public CourseList(Course newCourse){
        semesterCourses.add(newCourse);
    }

    public CourseList() {
        semesterCourses = new ArrayList<Course>();
    }

    public ArrayList<Course> getSemesterCourses() {
        return semesterCourses;
    }

    public void setSemesterCourses(ArrayList<Course> semesterCourses) {
        this.semesterCourses = (ArrayList<Course>) semesterCourses.clone();
    }

    public void addSemesterCourse(Course newCourse){
        this.semesterCourses.add(newCourse);
    }
    public boolean removeSemesterCourse(String courseName){
        boolean elementRemoved = false;
        int size = this.semesterCourses.size();
        for (int i = 0 ; i < size ; i++){
            if (courseName == this.semesterCourses.get(i).getTitle()){
                this.semesterCourses.remove(i);
                elementRemoved = true;
            }
        }
        for (int i = 0 ; i < size ; i++){
            this.semesterCourses.get(i).setCourseNumber(i);
        }
        return elementRemoved;
    }
}
