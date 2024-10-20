package SchoolDatabase;
//Holds the information on Course
public class Course {
    private String name = ConstantValues.NO_TITLE;
    private String courseCode = ConstantValues.NOT_AVAILABLE;
    private Character courseBase = ' ';
    private int courseType = ConstantValues.OPTIONAL;
    private int period = 0;
    private double credits = 0.0;
    private boolean numericGrade =  false;

    public Course(){

    }
    public Course(String name, final int code, Character courseBase,
                    final int type, final int period, final double credits,
                    boolean numericGrade){
        setName(name);
        setCourseCode(code, courseBase);
        setCourseType(type);
        setPeriod(period);
        setCredits(credits);
        setNumericGrade(numericGrade);
    }
    public Course(Course course){
        this.name = course.getName();
        this.courseCode = course.getCourseCode();
        this.courseBase = course.getCourseBase();
        this.courseType = course.getCourseType();
        this.period = course.getPeriod();
        this.credits = course.getCredits();
        this.numericGrade = course.isNumericGrade();
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        if (name != null && (!name.isEmpty())) {
            this.name = name;
        }
    }
    public String getCourseTypeString(){
        int type = this.courseType;
        String result = null;
        if (type == ConstantValues.OPTIONAL) {
            result = "Optional";
        }else if (type == ConstantValues.MANDATORY) {
            result = "Mandatory";
        }
        return result;
    }
    public int getCourseType(){
        return courseType;
    }
    public void setCourseType(final int type){
        if (type == ConstantValues.OPTIONAL || type == ConstantValues.MANDATORY) {
            this.courseType = type;
        }
    }
    public String getCourseCode(){
        return courseCode;
    }
    public void setCourseCode(final int courseCode, Character courseBase){
        courseBase = Character.toUpperCase(courseBase);
        if ((courseCode > 0 && courseCode < 1000000) && (courseBase == 'A' ||
            courseBase == 'P' || courseBase == 'S')) {
            this.courseCode = Integer.toString(courseCode) + courseBase;
            this.courseBase = courseBase;
        }
    }
    public Character getCourseBase(){
        return courseBase;
    }
    public int getPeriod(){
        return period;
    }
    public void setPeriod(final int period){
        if (period >= ConstantValues.MIN_PERIOD && period <= ConstantValues.MAX_PERIOD) {
            this.period = period;
        }
    }
    public double getCredits(){
        return credits;
    }
    private void setCredits(final double credits){
        if (credits >= ConstantValues.MIN_CREDITS && credits <= ConstantValues.MAX_COURSE_CREDITS) {
            this.credits = credits;
        }
    }
    public boolean isNumericGrade(){
        return numericGrade;
    }
    public void setNumericGrade(boolean numericGrade){
        this.numericGrade = numericGrade;
    }
    @Override
    public String toString(){
        String courseType = getCourseTypeString();
        return "[" + courseCode + " (" + String.format("%.2f", credits) + " cr), \"" + name + "\". " + courseType
            + ", period: " + period + ".]";  
    }    
}
