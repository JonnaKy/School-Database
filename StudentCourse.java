package SchoolDatabase;

import java.time.Year;
// creates the course for a spesific student
public class StudentCourse {
    private Course course;
    private int gradeNum = 0;
    private int yearCompleted = Year.now().getValue();

    public StudentCourse(){

    }
    public StudentCourse(Course course, final int gradeNum, final int yearCompleted){
        setCourse(course);
        setGrade(gradeNum);
        setYear(yearCompleted);
    }

    public Course getCourse(){
        return course;
    }
    public void setCourse(Course course){
        this.course = course;
    }
    public int getGradeNum(){
        return gradeNum;
    }
    protected void setGrade(int gradeNum){
        if (checkGradeValidity(gradeNum)) {
            this.gradeNum = gradeNum;
        }
        if (yearCompleted == 0) {
            this.yearCompleted = Year.now().getValue();
        }
    }
    private boolean checkGradeValidity(final int gradeNum){
        if (course.isNumericGrade()&&(gradeNum >= 0 && gradeNum <= 5)) {
            return true;
        }else if (!course.isNumericGrade()) {
            if ( gradeNum == 'a' || gradeNum == 'A' || gradeNum == 'f' || gradeNum == 'F') {
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public boolean isPassed(){
        if ((!course.isNumericGrade()) && (this.gradeNum == 'a' || this.gradeNum == 'A')) {
            return true;
        }else if ((course.isNumericGrade()) && (this.gradeNum > 0 && this.gradeNum <= 5)) {
            return true;
        }else{
            return false;
        }
    }
    public int getYear(){
        return yearCompleted;
    }
    public void setYear(final int year){
        if (year > 2000 && (year <= (Year.now().getValue()))) {
            this.yearCompleted = year;
        }
    }
    @Override
    public String toString(){
        String gradeValue = null;
        String courseString = null;
        if (course.isNumericGrade()) {
            if (gradeNum == 0) {
                gradeValue = "Not graded";
            }else{
                gradeValue = Integer.toString(gradeNum) + ".";
            }
        }
        if (!course.isNumericGrade()) {
            if (isPassed()) {
                gradeValue = ConstantValues.GRADE_ACCEPTED + ".";
            }else if (!isPassed()) {
                gradeValue = ConstantValues.GRADE_FAILED + ".";
            }
        }
        courseString = course.toString();
        return courseString + " Year: " + yearCompleted + ", Grade: " + gradeValue + "]";
    }
}
