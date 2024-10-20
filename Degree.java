package SchoolDatabase;

import java.util.ArrayList;
import java.util.List;
//Holds the information about degree and courses included in the degree
public class Degree {
    private static final int MAX_COURSES = 50;
    private int count = 0;
    private String degreeTitle = ConstantValues.NO_TITLE;
    private String titleOfThesis = ConstantValues.NO_TITLE;
    private List<StudentCourse> myCourses = new ArrayList<StudentCourse>();
    
    public List<StudentCourse> getCourses(){
        System.out.println("Kurssit: " + count);
        return myCourses;
    }
    public void addStudentCourses(List<StudentCourse> courses){
        if (courses != null && courses.size() < MAX_COURSES) { 
            for (StudentCourse course : courses){
                addStudentCourse(course);
            }
        }
    }
    public boolean addStudentCourse(StudentCourse course){
        int index = count;
        if (course != null && count < MAX_COURSES) { 
            myCourses.add(course);
            index += 1;
            this.count = index;
            return true;
        }else{
            return false;
        }
    }
    public String getDegreeTitle(){
        return degreeTitle;
    }
    public void setDegreeTitle(String degreeTitle){
        if (degreeTitle != null && (!degreeTitle.isEmpty())) {
            this.degreeTitle = degreeTitle;
        }
    }
    public String getTitleOfThesis(){
        return titleOfThesis;
    }
    public void setTitleOfThesis(String titleOfThesis){
        if (titleOfThesis != null && (!titleOfThesis.isEmpty())) {
            this.titleOfThesis = titleOfThesis;
        }
    }
    public double getCreditsByBase (Character base){
        double baseCredits = 0.0;
        for (StudentCourse course : myCourses){
            if (isCourseCompleted(course) && course.getCourse().getCourseBase() == base) {
                baseCredits += course.getCourse().getCredits();
            }
        }   
        return baseCredits;
    }
    public double getCreditsByType(final int courseType){
        double typeCredits = 0.0;
        for (StudentCourse course : myCourses){
            if (isCourseCompleted(course) && course.getCourse().getCourseType() == courseType) {
                typeCredits += course.getCourse().getCredits();
            }
        }
        return typeCredits;
    }
    public double getCredits(){
        double allCredits = 0.0;
        for (StudentCourse course : myCourses){
            if (isCourseCompleted(course)) {
                allCredits += course.getCourse().getCredits();
            }
        }
        return allCredits;
    }
    private boolean isCourseCompleted(StudentCourse c){
        if (c != null && c.getCourse().isNumericGrade()) {
            if (c.getGradeNum() > 0) {
                return true;
            }else{
                return false;
            }
        }else if (c != null && (!(c.getCourse().isNumericGrade()))) {
            if (c.isPassed()){

                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    public void printCourses(){
        for (StudentCourse course : myCourses ){
            if (course != null) {
                System.out.println(course.toString());
            }
        }
    }
    public List<Double> getGPA(int type){
        double allGrades = 0.0;
        double numericGradeAmount = 0.0; 
        double average = 0.0; 
        List<Double> gpa = new ArrayList<Double>(3);

        for (StudentCourse course : myCourses){
            if (course.getCourse().isNumericGrade()) {
               if (type == course.getCourse().getCourseType()) {
                    allGrades += course.getGradeNum(); 
                    numericGradeAmount++;
               }else if (type == 2) {
                    allGrades += course.getGradeNum();
                    numericGradeAmount++;
               }
            }
        }
        average = allGrades / numericGradeAmount;
        allGrades = roundDecimals(allGrades);
        numericGradeAmount = roundDecimals(numericGradeAmount);
        average = roundDecimals(average);
        gpa.add(0,allGrades);
        gpa.add(1, numericGradeAmount);
        gpa.add(2, average);
        return gpa;
    }
    private double roundDecimals(double gpaNumber){
        double roundedNumber = gpaNumber;
        double decimals = roundedNumber * 1000;
        decimals = decimals % 10;
        if (decimals >= 5) {
            roundedNumber = Math.ceil(roundedNumber * 100) / 100;
        }else{
            roundedNumber = Math.floor(roundedNumber * 100) / 100;
        }
        return roundedNumber;
    }
    @Override
    public String toString(){
        String title = null;
        String thesis = null;
        StringBuilder oneCourse = new StringBuilder();
        title = "Degree [Title: \"" + degreeTitle + "\" (courses: " + count + ")\n";
        thesis = "\tThesis title: \"" + titleOfThesis + "\"";
        int i = 1;
        for (StudentCourse course : myCourses){
            if (course != null) {
                oneCourse.append("\n\t").append(i).append(". ").append(course.toString());
            }
            i += 1;
        }
        return title + thesis + oneCourse + "]";
    }
}
