package com.example.gradebook;


import java.io.*;
import java.util.*;


public class Category {
    private String categoryName;
    private int weight;
    private ArrayList<Assignment> assignmentArray;
    private int categoryTotalGrade;
    private int categoryNumber;
    int assignmentIterator = 0;

    public Category(String categoryName, int weight, int categoryTotalGrade) {
        this.categoryName = categoryName;
        this.weight = weight;
        assignmentArray = new ArrayList<Assignment>();
        this.categoryTotalGrade = categoryTotalGrade;
        categoryNumber = 0;
    }

    public Category(String categoryName, int weight) {
        this.categoryName = categoryName;
        this.weight = weight;
        assignmentArray = new ArrayList<Assignment>();
        categoryNumber = 0;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight / 100;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }

    public ArrayList<Assignment> getAssignmentArray() {
        return assignmentArray;
    }

    public void setAssignmentArray(ArrayList<Assignment> assignmentArray) {
        this.assignmentArray = (ArrayList<Assignment>)assignmentArray.clone();
    }

    public void addAssignment(Assignment assignment) {
        this.assignmentArray.add(assignment);
        assignmentIterator++;
    }

    public boolean removeAssignment(String assignmentName){
        boolean elementRemoved = false;
        int size = this.assignmentArray.size();
        for (int i = 0 ; i < size ; i++){
            if (assignmentName == this.assignmentArray.get(i).getName()){
                this.assignmentArray.remove(i);
                elementRemoved = true;
            }
        }
        for (int i = 0 ; i < size ; i++){
            this.assignmentArray.get(i).setAssignmentNumber(i);
        }
        return elementRemoved;
    }

    public int getCategoryTotalGrade() {
        return categoryTotalGrade;
    }

    public void calcCategoryTotalGrade() {
        int size = this.assignmentArray.size();
        int totalGrade = 0;
        for (int i = 0 ; i < size ; i++){
           totalGrade += this.assignmentArray.get(i).getGrade();
        }
        totalGrade = totalGrade / size;

        this.categoryTotalGrade = totalGrade;
    }


}
