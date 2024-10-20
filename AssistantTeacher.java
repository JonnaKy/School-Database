package SchoolDatabase;

import java.util.ArrayList;
import java.util.List;
//Holds the information about assistants teachers

public class AssistantTeacher extends Employee implements Teacher  {
    private List<DesignatedCourse> courses = new ArrayList<DesignatedCourse>();

    public AssistantTeacher(String lname, String fname){
        super(lname, fname);
    }

    public String getEmployeeIdString(){
        return "OY_ASSISTANT_";
    }
    public String getCourses(){
        StringBuilder desCourse = new StringBuilder();
        for (DesignatedCourse course : courses){
            if (course != null) {
                desCourse.append(course.toString()).append("\n\t");
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
        String teacherAssistan = "Teacher id: " + getIdString();
        String name = "\n\tFirst name: " + getFirstName() + ", Last name: " + getLastName();
        String birthday = "\n\tBirthdate: " + getBirthDate();
        String salary = "\n\tSalary: " + String.format("%.2f",calculatePayment()); //toimiikohan tämä
        String coursesAssistant = "\n\tAssistant for courses: \n\t";
        String coursesAssisted = getCourses();
        return teacherAssistan + name + birthday + salary + coursesAssistant + coursesAssisted;
    }
}
