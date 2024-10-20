package SchoolDatabase;

import java.util.ArrayList;
import java.util.List;
// Designates courses that the teacher is responsible of
public class ResponsibleTeacher extends Employee implements Teacher {
    private List<DesignatedCourse> courses = new ArrayList<DesignatedCourse>();

    public ResponsibleTeacher(String lname, String fname){
        super(lname, fname);
    }

    public String getEmployeeIdString(){
        return "OY_TEACHER_";
    }
    public String getCourses(){
        StringBuilder desCourse = new StringBuilder();
        for (DesignatedCourse course : courses){
            if (course != null && course.isResponsible()) {
                desCourse.append("Responsible teacher: ").append(course.toString()).append("\n\t");
            }else if (course != null && (!course.isResponsible())) {
                desCourse.append("Teacher: ").append(course.toString()).append("\n\t");
            }
        }
        return desCourse.toString();
    }
    public void setCourses(List<DesignatedCourse> courses){
        if (courses != null) {
            this.courses = courses;
        }
    }
    @Override
    public String toString(){
        String teacher = "Teacher id: " + getIdString();
        String name = "\n\tFirst name: " + getFirstName() + ", Last name: " + getLastName();
        String birthday = "\n\tBirthdate: " + getBirthDate();
        String salary = "\n\tSalary: " + String.format("%.2f",calculatePayment());
        String courseTeacher = "\n\tTeacher for courses: \n\t";
        String coursesTeached = getCourses();
        return teacher + name + birthday + salary + courseTeacher + coursesTeached;
    }
}
