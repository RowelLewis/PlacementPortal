//package com.college.placementportal.models;
//
//public class Company {
//    private String companyId;
//    private String companyName;
//    private String companyDescription;
//    private String eligibleBranches;
//    private String offeredCTC;
//    private String offeredStipend;
//    private boolean isDreamOption;
//    private boolean isSuperDreamOption;
//    private String minCGPA;
//
//    public String getMinCGPA() {
//        return minCGPA;
//    }
//
//    public void setMinCGPA(String minCGPA) {
//        this.minCGPA = minCGPA;
//    }
//
//    public String getCompanyName() {
//        return companyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        this.companyName = companyName;
//    }
//
//    public String getCompanyDescription() {
//        return companyDescription;
//    }
//
//    public void setCompanyDescription(String companyDescription) {
//        this.companyDescription = companyDescription;
//    }
//
//    public String getEligibleBranches() {
//        return eligibleBranches;
//    }
//
//    public void setEligibleBranches(String eligibleBranches) {
//        this.eligibleBranches = eligibleBranches;
//    }
//
//    public String getOfferedCTC() {
//        return offeredCTC;
//    }
//
//    public void setOfferedCTC(String offeredCTC) {
//        this.offeredCTC = offeredCTC;
//    }
//
//    public String getOfferedStipend() {
//        return offeredStipend;
//    }
//
//    public void setOfferedStipend(String offeredStipend) {
//        this.offeredStipend = offeredStipend;
//    }
//
//    public boolean isDreamOption() {
//        return isDreamOption;
//    }
//
//    public void setDreamOption(boolean dreamOption) {
//        isDreamOption = dreamOption;
//    }
//
//    public boolean isSuperDreamOption() {
//        return isSuperDreamOption;
//    }
//
//    public void setSuperDreamOption(boolean superDreamOption) {
//        isSuperDreamOption = superDreamOption;
//    }
//
//    public String getCompanyId() {
//        return companyId;
//    }
//
//    public void setCompanyId(String companyId) {
//        this.companyId = companyId;
//    }
//}

package com.college.placementportal.models;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Company {
    //    private String companyId;
    private String companyName;
    private String companyDescription;
    private ArrayList<String> eligibleBranches;
    private String offeredCTC;
    //    private String offeredStipend;
    private String offer;
    private String minCGPA;
    private String closingOn;

    public Company() {
    }

    public Company(String CompanyName, String companyDescription, ArrayList<String> eligibleBranches, String offeredCTC, String offer, String minCGPA, String closingDate) {
        this.companyName=CompanyName;
        this.companyDescription=companyDescription;
        this.eligibleBranches=eligibleBranches;
        this.offeredCTC=offeredCTC;
        this.offer=offer;
        this.minCGPA=minCGPA;
        this.closingOn=closingDate;
//        this.temp=temp;
    }




    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();

        result.put("companyName",companyName) ;
        result.put("companyDescription",companyDescription);
        result.put("eligibleBranches",eligibleBranches);
        result.put("offeredCTC",offeredCTC);
        result.put("offer", offer);
        result.put("minCGPA",minCGPA);
        result.put("closingOn",closingOn);

        return result;
    }

    public String getClosingOn() {
        return closingOn;
    }

    public void setClosingOn(String closingOn) {
        this.closingOn = closingOn;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getMinCGPA() {
        return minCGPA;
    }

    public void setMinCGPA(String minCGPA) {
        this.minCGPA = minCGPA;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDescription() {
        return companyDescription;
    }

    public void setCompanyDescription(String companyDescription) {
        this.companyDescription = companyDescription;
    }

    public ArrayList<String>  getEligibleBranches() {
        return eligibleBranches;
    }

    public void setEligibleBranches(ArrayList<String> eligibleBranches) {
        this.eligibleBranches = eligibleBranches;
    }

    public String getOfferedCTC() {
        return offeredCTC;
    }

    public void setOfferedCTC(String offeredCTC) {
        this.offeredCTC = offeredCTC;
    }

//    public String getOfferedStipend() {
//        return offeredStipend;
//    }

//    public void setOfferedStipend(String offeredStipend) {
//        this.offeredStipend = offeredStipend;
//    }


//    public String getCompanyId() {
//        return companyId;
//    }
//
//    public void setCompanyId(String companyId) {
//        this.companyId = companyId;
//    }
}

