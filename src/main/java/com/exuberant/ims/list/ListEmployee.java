package com.exuberant.ims.list;
public class ListEmployee {
    public String employeeId;
    public String employeeName;
    public ListEmployee(String employeeId, String employeeName) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
    }
    public String getEmployeeId() {
        return this.employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getEmployeeName() {
        return this.employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }
}
