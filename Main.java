package SchoolDatabase;

import java.util.ArrayList;
/*Main class to test the program
Prints information on two teachers and courses that they teach
Prints information on a student and shows how their studies are going
Prints out information about a student's degrees, that hold values, it's title, courses, courses garde and credits
*/
public class Main {
    public static void main(String[] args) {
         ResponsibleTeacher responsibleTeacher1 = new ResponsibleTeacher("Mouse", "Mickey");
        responsibleTeacher1.setBirthDate("230498-045T");
        MonthlyPayment monthlyPayment = new MonthlyPayment();
        monthlyPayment.setSalary(756.85);
        responsibleTeacher1.setPayment(monthlyPayment);

        AssistantTeacher assistantTeacher1 = new AssistantTeacher("Dog","Goofy");
        assistantTeacher1.setBirthDate("141200A2315");
        HourBasedPayment hourBasedPayment = new HourBasedPayment();
        hourBasedPayment.setEurosPerHour(3.5);
        hourBasedPayment.setHours(11.0);
        assistantTeacher1.setPayment(hourBasedPayment);

        Student student1 = new Student("Duck", "Donald");

        Course course1 = new Course("Programming 1", 811104, 'P', 1, 1, 5.0, true);
        Course course2 = new Course("All kinds of basic studies", 112233, 'P', 1, 2, 45.0, true);
        Course course3 = new Course("More basic studies", 223344, 'a', 1, 1, 50.5, true);
        Course course4 = new Course("Even more basic studies", 556677, 'a',  0, 3, 50.0, true);
        Course course5 = new Course("Final basic studies", 123123, 'A', 1, 4, 50.5, true);
        Course course6 = new Course("Programming 2", 616161, 'A', 1, 3, 25.0, true);
        Course course7 = new Course( "All kinds of master studies", 717171, 'P', 0,2, 45.0, true);
        Course course8 = new Course( "More master studies", 818181, 'A', 1, 1, 25.0, true);
        Course course9 = new Course( "Even more master studies ", 919191, 'S', 1, 3, 20.0, true);
        Course course10 = new Course("Extra master studies", 666666, 'S', 0, 5, 8.0, false);
        Course course11 = new Course("Final master studies", 888888, 'S', 1, 5, 18.0, false);

        DesignatedCourse designatedCourse = new DesignatedCourse(course3, false, 2023);
        DesignatedCourse designatedCourse2 = new DesignatedCourse(course4, true, 2023);
        DesignatedCourse designatedCourse3 = new DesignatedCourse(course10, false, 2022);
        DesignatedCourse designatedCourse4 = new DesignatedCourse(course11, false, 2023);
        ArrayList<DesignatedCourse> designatedCourses = new ArrayList<DesignatedCourse>();
        designatedCourses.add(0, designatedCourse);
        designatedCourses.add(1, designatedCourse2);
        designatedCourses.add(2, designatedCourse3);
        designatedCourses.add(3, designatedCourse4);

        responsibleTeacher1.setCourses(designatedCourses);
        assistantTeacher1.setCourses(designatedCourses);
        System.out.println(responsibleTeacher1.toString());
        System.out.println(assistantTeacher1.toString());

        StudentCourse studentCourse = new StudentCourse(course1, 1, 2013);
        StudentCourse studentCourse2 = new StudentCourse(course2, 1, 2014);
        StudentCourse studentCourse3 = new StudentCourse(course3, 1, 2015);
        StudentCourse studentCourse4 = new StudentCourse(course4, 4, 2016);
        StudentCourse studentCourse5 = new StudentCourse(course5, 5, 2017);
        StudentCourse studentCourse6 = new StudentCourse(course6, 1, 2018);
        StudentCourse studentCourse7 = new StudentCourse(course7, 1, 2019);
        StudentCourse studentCourse8 = new StudentCourse(course8, 2, 2020);
        StudentCourse studentCourse9 = new StudentCourse(course9, 0, 2021);
        StudentCourse studentCourse10 = new StudentCourse(course10, 'A', 2021);
        StudentCourse studentCourse11 = new StudentCourse(course11, 'f', 2022);


        ArrayList<StudentCourse> bachelorCourses = new ArrayList<StudentCourse>();
        bachelorCourses.add(studentCourse);
        bachelorCourses.add(studentCourse2);
        bachelorCourses.add(studentCourse3);
        bachelorCourses.add(studentCourse4);
        bachelorCourses.add(studentCourse5);

        ArrayList<StudentCourse> mastersCourses = new ArrayList<StudentCourse>();
        mastersCourses.add(studentCourse6);
        mastersCourses.add(studentCourse7);
        mastersCourses.add(studentCourse8);
        mastersCourses.add(studentCourse9);
        mastersCourses.add(studentCourse10);
        mastersCourses.add(studentCourse11);

        student1.setDegreeTitle(0, "Bachelor");
        student1.setDegreeTitle(1, "Masters");
        student1.setTitleOfThesis(0, "Bachelors title");
        student1.setTitleOfThesis(1, "Masters title"); 

        
        student1.addCourses(0, bachelorCourses);
        student1.addCourses(1, mastersCourses);
        student1.setStartYear(2001);
        student1.setGraduationYear(2020);
        student1.setBirthDate("230498-045T");


        studentCourse9.setGrade(3);
        student1.setGraduationYear(2020);
        System.out.println(student1.toString());
        student1.printDegrees();
    }
}



