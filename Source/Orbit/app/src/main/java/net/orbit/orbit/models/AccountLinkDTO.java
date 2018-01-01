package net.orbit.orbit.models;

/**
 * Created by brocktubre on 12/25/17.
 */

public class AccountLinkDTO {
    private String userID;
    private int studentID;

    public AccountLinkDTO(String userID, int studentID) {
        this.userID = userID;
        this.studentID = studentID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "AccountLinkDTO{" +
                "userID='" + userID + '\'' +
                ", studentID='" + studentID + '\'' +
                '}';
    }

}
