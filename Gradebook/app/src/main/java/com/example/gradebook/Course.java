package com.example.gradebook;


import java.io.*;
import java.util.*;

public class Course {
    private String title;
    private int courseGrade;
    private ArrayList<Category> categoryList;
    int categoryIterator = 0;
    private int courseNumber;

    public Course(String title, int courseGrade, ArrayList<Category> categoryList) {
        this.title = title;
        this.courseGrade = courseGrade;
        this.categoryList = (ArrayList<Category>) categoryList.clone();
    }

    public Course(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCourseGrade() {
        return courseGrade;
    }

    public void calcCourseGrade(int courseGrade) {
        int size = this.categoryList.size();
        int totalGrade = 0;
        for (int i = 0 ; i < size ; i++){
            totalGrade += (categoryList.get(i).getCategoryTotalGrade() * categoryList.get(i).getWeight());
        }
        this.courseGrade = totalGrade;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public ArrayList<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = (ArrayList<Category>) categoryList.clone();
    }

    public void addCategory(Category category){
        this.categoryList.add(category);
        categoryIterator++;
    }

    public Boolean removeCategory(String categoryName){
        boolean elementRemoved = false;
        int size = this.categoryList.size();
        for (int i = 0 ; i < size ; i++){
            if (categoryName == this.categoryList.get(i).getCategoryName()){
                this.categoryList.remove(i);
                elementRemoved = true;
            }
        }
        for (int i = 0 ; i < size ; i++){
            this.categoryList.get(i).setCategoryNumber(i);
        }
        return elementRemoved;
    }
}
