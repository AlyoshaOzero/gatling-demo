package org.example.constant;

public enum BusinessProcess {

    BP1("BP1: Get All Products");

    private final String businessProcessFullName;

    BusinessProcess(String businessProcessFullName) {
        this.businessProcessFullName = businessProcessFullName;
    }

    public String getBusinessProcessFullName() {
        return businessProcessFullName;
    }
}
