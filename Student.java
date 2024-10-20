package SchoolDatabase;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
// holds information about a student who is a person
public class Student extends Person {
    private int id = 0;
    private int startYear = Year.now().getValue();
    private int graduationYear = 0;
    private int degreeCount = 3;
    private List<Degree> degrees = new ArrayList<Degree>(degreeCount);

    public Student(String lname, String fname){
        super(lname, fname);
        id = super.getRandomId(ConstantValues.MIN_STUDENT_ID, ConstantValues.MAX_STUDENT_ID);
    }

    public int getId(){
        return id;
    }
    public void setId(final int id){
        if (id >= ConstantValues.MIN_STUDENT_ID && id <= ConstantValues.MAX_STUDENT_ID){
            this.id = id;
        }
    }
    public int getStartYear(){
        return startYear;
    }
    public void setStartYear(final int startYear){
        if (startYear > 2000 && (startYear <= (Year.now().getValue()))) {
            this.startYear = startYear;
        }
    }
    public int getGraduationYear(){
        return graduationYear;
    }
    public String setGraduationYear(final int graduationYear){
        boolean graduation = canGraduate();
        String setGraduation = null;
        if (!graduation) {
            setGraduation = "Check amount of required credits";
        }else if(startYear > graduationYear || graduationYear > (Year.now().getValue())){
            setGraduation = "Check graduation year";
        }else if(graduation == true){
            setGraduation = "OK";
            this.graduationYear = graduationYear;
        }
        return setGraduation;
    }
    public void setDegreeTitle(final int i, String dName){
        if (degrees.size() <= degreeCount) {
            Degree degree = new Degree();
            degrees.add(degree);
        }
        if ((i >= 0 && i < degreeCount) && dName != null) {
            degrees.get(i).setDegreeTitle(dName);
        }
    }
    public boolean addCourse (final int i, StudentCourse course){
        if (degrees.size() <= degreeCount) {
            Degree degree = new Degree();
            degrees.add(degree);
        }
        if ((i >= 0 && i < degreeCount) && course != null) {
            degrees.get(i).addStudentCourse(course);
            return true;
        }else{
            return false;
        }
    }
    public int addCourses (final int i, List<StudentCourse> courses ){
        int addedCourses = 0;
        if (degrees.size() <= degreeCount) {
            Degree degree = new Degree();
            degrees.add(degree);
        }
        if ((i >= 0 && i < degreeCount) && (courses != null)) {
            for (StudentCourse course : courses){
                degrees.get(i).addStudentCourse(course);
                    addedCourses++;
                    
            }
        }
        return addedCourses;
    }
    public void printCourses(){
        for (Degree degree : degrees){
            if (degree != null) {
                degree.printCourses();
            }
        }
     }
     public void printDegrees(){
         for (Degree degree : degrees){
             if (degree != null && (!degree.getDegreeTitle().equals("No title"))) {
                 System.out.println(degree.toString());
             }
         }
     }
     public void setTitleOfThesis(final int i, String title){
        if (degrees.size() <= degreeCount) {
            Degree degree = new Degree();
            degrees.add(degree);
        }    
        if ((i >= 0 && i < degreeCount) && title != null) {
                degrees.get(i).setTitleOfThesis(title);
            }
     }
     public boolean hasGraduated(){
         if (graduationYear <= (Year.now().getValue()) && startYear != (Year.now().getValue())) {
             return true;
         }else{
             return false;
         }
     }
     private boolean canGraduate(){
        Degree bachelors = degrees.get(ConstantValues.BACHELOR_TYPE);
        double bachCredits = bachelors.getCredits();
        Degree masters = degrees.get(ConstantValues.MASTER_TYPE);
        double mastCredits = masters.getCredits();
        String bachTitle = bachelors.getTitleOfThesis();
        String mastTitle = masters.getTitleOfThesis();
        double bachCredOptional = bachelors.getCreditsByType(ConstantValues.OPTIONAL);
        double bachCredMandatory = bachelors.getCreditsByType(ConstantValues.MANDATORY);
        double mastCredOptional = masters.getCreditsByType(ConstantValues.OPTIONAL);
        double mastCredMandatory = masters.getCreditsByType(ConstantValues.MANDATORY);
        if ((bachTitle != null && bachTitle != ConstantValues.NO_TITLE) &&
            (mastTitle != null && mastTitle != ConstantValues.NO_TITLE)) {
            if ((bachCredits >= ConstantValues.BACHELOR_CREDITS) && 
                (mastCredits >= ConstantValues.MASTER_CREDITS)) {
                if ((bachCredOptional >= ConstantValues.BACHELOR_OPTIONAL && bachCredMandatory >= ConstantValues.BACHELOR_MANDATORY) &&
                    (mastCredOptional >= ConstantValues.MASTER_OPTIONAL && mastCredMandatory >= ConstantValues.MASTER_MANDATORY)) {
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
     }
     public int getStudyYears(){
         int yearsStudied = 0;
         if (hasGraduated() == true && canGraduate() == true) {
             yearsStudied = graduationYear - startYear;
         }else{
             yearsStudied = (Year.now().getValue()) - startYear;
         }
         return yearsStudied;
    }
    @Override
    public String toString(){
        String studentId = getIdString();
        String name = "\n\tFirst name: " + getFirstName() + ", Last name: " + getLastName() + "\n";
        String dateOfBirth = null;
        if (getBirthDate() == "Not available") {
            dateOfBirth = "\tDate of birth: " + "\"" + getBirthDate() + "\"";
        }else{
            dateOfBirth = "\tDate of birth: " + getBirthDate();
        }
        String status = null;
        String studyYears = null;
        if (hasGraduated() && canGraduate()) {
            status = "\n\tThe student has graduated in " + this.graduationYear;
            studyYears = " (studies lasted " + getStudyYears() + "years)";
        }else{
            status = "\n\tThe students has not graduated, yet";
            studyYears = " (studies have lasted for " + getStudyYears() + " years)";
        }
        String startYears = "\n\tStart year: " + this.startYear;

        Degree bachelors = degrees.get(ConstantValues.BACHELOR_TYPE);

        Degree masters = degrees.get(ConstantValues.MASTER_TYPE);

        double bachelorsCredits = bachelors.getCredits();
        double mastersCredits = masters.getCredits();
        String totalCredits = "\n\tTotal credits: " + String.format("%.2f", (bachelorsCredits + mastersCredits));

        List<Double> bachelorsGpaList = bachelors.getGPA(2);
        List<Double> mastersGpaList = masters.getGPA(2);
        double bachelorsGPA = bachelorsGpaList.get(2);
        double mastersGPA = mastersGpaList.get(2);
        double wholeGPA = (bachelorsGpaList.get(0) + mastersGpaList.get(0)) / (bachelorsGpaList.get(1) + mastersGpaList.get(1));
        String gpa = " (GPA = " + String.format("%.2f",wholeGPA) + ")";

        String bachelorTitle = "\n\tBachelor credits: " + String.format("%.1f", bachelorsCredits);
        String allBachelorCredits =  null;
        if (bachelorsCredits >= ConstantValues.BACHELOR_CREDITS) {
            allBachelorCredits = "\n\t\tTotal bachelor credits compelted (" + String.format("%.2f", bachelorsCredits) + "/" + ConstantValues.BACHELOR_CREDITS + ")";
        }else{
            double missingAllBachelorCredits = ConstantValues.BACHELOR_CREDITS - bachelorsCredits;
            allBachelorCredits = "\n\t\tMissing bachelor credits " + String.format("%.2f", missingAllBachelorCredits) + " (" + String.format("%.2f", bachelorsCredits) + "/" + ConstantValues.BACHELOR_CREDITS + ")";
        }

        double bachelorsMandatory = bachelors.getCreditsByType(1);
        String bachMandatoryCredits = null;
        if (bachelorsMandatory >= ConstantValues.BACHELOR_MANDATORY) {
            bachMandatoryCredits = "\n\t\tAll mandatory bachelor credits completed (" + String.format("%.2f", bachelorsMandatory) + "/" + ConstantValues.BACHELOR_MANDATORY + ")";
        }else{
            double missingBachelors = ConstantValues.BACHELOR_MANDATORY - bachelorsMandatory;
            bachMandatoryCredits = "\n\t\tMissing mandatory bachelor credits " + String.format("%.2f", missingBachelors);
        }
        String gpaBachelors = "\n\t\tGPA of bachelor studies: " + String.format("%.2f", bachelorsGPA);
        String bachThesis = "\n\t\tTitle of BSc Thesis: \"" + bachelors.getTitleOfThesis() + "\"";

        String masterTitle = "\n\tMaster Credits: " + String.format("%.1f", mastersCredits);
        String allMastresCredits = null;
        if (mastersCredits >= ConstantValues.MASTER_CREDITS) {
            allMastresCredits = "\n\t\tTotal master's credits completed (" + String.format("%.2f", mastersCredits) + "/" + ConstantValues.MASTER_CREDITS + ")";
        }else{
            double missingAllMastersCredits = ConstantValues.MASTER_CREDITS - mastersCredits;
            allMastresCredits = "\n\t\tMissing master's credits " + String.format("%.2f", missingAllMastersCredits)+ "(" + String.format("%.2f", mastersCredits) + "/" + ConstantValues.MASTER_CREDITS + ")";
        }
        double mastersMandatory = masters.getCreditsByType(1);
        String mastMandatoryCredits = null;
        if (mastersMandatory >= ConstantValues.MASTER_MANDATORY) {
            mastMandatoryCredits = "\n\t\tAll mandatory masters credits completed (" + String.format("%.2f", mastersMandatory) + "/" + ConstantValues.MASTER_MANDATORY + ")";
        }else{
            double missingMandMasters = ConstantValues.MASTER_MANDATORY - mastersMandatory;
            mastMandatoryCredits = "\n\t\tMissing mandatory masters credits " + String.format("%.2f", missingMandMasters) + "(" + String.format("%.2f", mastersMandatory) + "/" + ConstantValues.MASTER_MANDATORY + ")";
        }
        String gpaMasters = "\n\t\tGPA of Masters studies: " + String.format("%.2f", mastersGPA);
        String mastThesis = "\n\t\tTitle of MSc Thesis: \"" + masters.getTitleOfThesis() + "\"";
        
        return studentId + name + dateOfBirth + status + startYears + studyYears + totalCredits + gpa + bachelorTitle + 
                allBachelorCredits + bachMandatoryCredits + gpaBachelors + bachThesis + masterTitle + allMastresCredits +
                mastMandatoryCredits + gpaMasters + mastThesis;

    }
    public String getIdString() {
        return "Student id: " + Integer.toString(id);
    }
}
