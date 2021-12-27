package com.college.placementportal.models;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Registered {

//    private String CompanyID;
    private String CompanyName;
    private ArrayList<String> StudentIDs = new ArrayList<>();
//    private String StudentID;

    public Registered() {
    }

    public Registered(String companyName, String studentID) {
        CompanyName = companyName;
//        StudentID = studentID;
        StudentIDs.add(studentID);
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

//        result.put("companyID",CompanyID) ;
        result.put("companyName",CompanyName);
        result.put("studentID",StudentIDs);
//        result.put("studentID",StudentID);

        return result;
    }

//    public String getCompanyID() {
//        return CompanyID;
//    }
//
//    public void setCompanyID(String companyID) {
//        CompanyID = companyID;
//    }

//    public String getStudentID() {
//        return StudentID;
//    }
//
//    public void setStudentID(String studentID) {
//        StudentID = studentID;
//    }

//    public String getCompanyName() {
//        return CompanyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        CompanyName = companyName;
//    }

    public ArrayList<String> getStudentID() {
        return StudentIDs;
    }

    public void setStudentID(ArrayList<String> studentID) {
        StudentIDs = studentID;
    }
}
