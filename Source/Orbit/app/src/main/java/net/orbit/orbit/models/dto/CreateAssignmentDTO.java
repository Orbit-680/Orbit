package net.orbit.orbit.models.dto;

/**
 * Created by brocktubre on 12/25/17.
 */

public class CreateAssignmentDTO {
    private int courseID;
    private String name;
    private String maxPoints;

    public CreateAssignmentDTO(int courseID, String name, String maxPoints) {
        this.courseID = courseID;
        this.name = name;
        this.maxPoints = maxPoints;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(String maxPoints) {
        this.maxPoints = maxPoints;
    }

    @Override
    public String toString() {
        return "CreateAssignmentDTO{" +
                "courseID='" + courseID + '\'' +
                ", name='" + name + '\'' +
                ", maxPoints='" + maxPoints + '\'' +
                '}';
    }

}
