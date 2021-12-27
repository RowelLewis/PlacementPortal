package com.college.placementportal.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Details {
    private String country;
    private String state;
    private String passingYear;
    private String board;
    private String instituteName;   //School/College Name
    private String percentage;
}

public class Student {
    private String fullName="";
    private String dateofbirth="";
    private String branch="";
    private String mail="";
    private String phoneNumber="";
    private String gender="";
    private String permanentAddress="";

    public Student() {
    }

    public Student(String fullName, String dateofbirth, String branch, String mail, String phoneNumber, String gender, String permanentAddress) {
        this.fullName = fullName;
        this.dateofbirth = dateofbirth;
        this.branch = branch;
        this.mail=mail;
        this.phoneNumber=phoneNumber;
        this.gender=gender;
        this.permanentAddress=permanentAddress;
//        this.temp=temp;
    }

    public String getFullName() {
        return fullName;
    }


    public String getMail() {
        return mail;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }


    public String getBranch() {
        return branch;
    }



    public String mail() {
        return mail;
    }




    public String getPhoneNumber() {
        return phoneNumber;
    }




    public String getGender() {
        return gender;
    }



    public String getPermanentAddress() {
        return permanentAddress;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("fullName", fullName);
        result.put("dateofbirth", dateofbirth);
        result.put("branch", branch);
        result.put("mail", mail);
        result.put("phoneNumber", phoneNumber);
        result.put("gender", gender);
        result.put("permanentAddress",permanentAddress);

        return result;
    }

//    private String firstName;
//    private String middleName;
//    private String lastName;
//    private String dateofbirth;
//    private String branch;
//    private String collegeEmail;
//    private String personalEmail;
//    private String phoneNumber;
//    private String alternatePhoneNumber;
//    private String gender;
//    private String permanentAddress;
//    private String currentAddress;
//    private String panCardNumber;
//    private String aadhaarCardNumber;

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getMiddleName() {
//        return middleName;
//    }
//
//    public void setMiddleName(String middleName) {
//        this.middleName = middleName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getDateofbirth() {
//        return dateofbirth;
//    }
//
//    public void setDateofbirth(String dateofbirth) {
//        this.dateofbirth = dateofbirth;
//    }
//
//    public String getBranch() {
//        return branch;
//    }
//
//    public void setBranch(String branch) {
//        this.branch = branch;
//    }
//
//    public String getCollegeEmail() {
//        return collegeEmail;
//    }
//
//    public void setCollegeEmail(String collegeEmail) {
//        this.collegeEmail = collegeEmail;
//    }
//
//    public String getPersonalEmail() {
//        return personalEmail;
//    }
//
//    public void setPersonalEmail(String personalEmail) {
//        this.personalEmail = personalEmail;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getAlternatePhoneNumber() {
//        return alternatePhoneNumber;
//    }
//
//    public void setAlternatePhoneNumber(String alternatePhoneNumber) {
//        this.alternatePhoneNumber = alternatePhoneNumber;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public String getPermanentAddress() {
//        return permanentAddress;
//    }
//
//    public void setPermanentAddress(String permanentAddress) {
//        this.permanentAddress = permanentAddress;
//    }

//    public String getCurrentAddress() {
//        return currentAddress;
//    }
//
//    public void setCurrentAddress(String currentAddress) {
//        this.currentAddress = currentAddress;
//    }
//
//    public String getPanCardNumber() {
//        return panCardNumber;
//    }
//
//    public void setPanCardNumber(String panCardNumber) {
//        this.panCardNumber = panCardNumber;
//    }
//
//    public String getAadhaarCardNumber() {
//        return aadhaarCardNumber;
//    }
//
//    public void setAadhaarCardNumber(String aadhaarCardNumber) {
//        this.aadhaarCardNumber = aadhaarCardNumber;
//    }

    private Map<String, Details> educationDetails;
    private Map<String, Double> presentCourseDetails;
}
/*
presentCourseDetails:{
    sgpaSem1:val,
    sgpaSem2:val,
    sgpaSem3:val,
    sgpaSem4:val,
    sgpaSem5:val,
    sgpaSem6:val,
    sgpaSem7:val,
    sgpaSem8:val,
    cgpa:val,
    activeBacklog:val,
    history(backlogs):val,
    educationGap:val
}
educationDetails:{
    sslc:{
        country: string,
        state: string,
        passingYear: String,
        board: string,
        instituteName: String,
        percentage: String
    },
    puc:{
        country: string,
        state: string,
        passingYear: String,
        board: string,
        instituteName: String,
        percentage: String
    },
    diploma:{
        country: string,
        state: string,
        passingYear: String,
        board: string,
        instituteName: String,
        percentage: String
    }
}

 */
