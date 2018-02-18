package net.orbit.orbit.models.pojo;

public class Grade {

    private int gradeId;
    private String grade;
    private String year;
    private Student student;
    private Course course;
    private Boolean isSelected = false;

    public Grade() {

    }

    public Grade(int gradeId, String grade, String year, Student student, Course course) {
        this.gradeId = gradeId;
        this.grade = grade;
        this.year = year;
        this.student = student;
        this.course = course;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "Grade [gradeId=" + gradeId + ", grade=" + grade + ", year=" + year + ", student=" + student.toString() + ", course=" + course.toString() + "]";
    }

}
