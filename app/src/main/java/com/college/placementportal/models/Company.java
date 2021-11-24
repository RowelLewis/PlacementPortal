package com.college.placementportal.models;

public class Company {
    private String companyId;
    private String companyName;
    private String companyDescription;
    private String eligibleBranches;
    private String offeredCTC;
    private String offeredStipend;
    private boolean isDreamOption;
    private boolean isSuperDreamOption;
    private String minCGPA;

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

    public String getEligibleBranches() {
        return eligibleBranches;
    }

    public void setEligibleBranches(String eligibleBranches) {
        this.eligibleBranches = eligibleBranches;
    }

    public String getOfferedCTC() {
        return offeredCTC;
    }

    public void setOfferedCTC(String offeredCTC) {
        this.offeredCTC = offeredCTC;
    }

    public String getOfferedStipend() {
        return offeredStipend;
    }

    public void setOfferedStipend(String offeredStipend) {
        this.offeredStipend = offeredStipend;
    }

    public boolean isDreamOption() {
        return isDreamOption;
    }

    public void setDreamOption(boolean dreamOption) {
        isDreamOption = dreamOption;
    }

    public boolean isSuperDreamOption() {
        return isSuperDreamOption;
    }

    public void setSuperDreamOption(boolean superDreamOption) {
        isSuperDreamOption = superDreamOption;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
