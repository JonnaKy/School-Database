package SchoolDatabase;

import java.time.Year;
//Holds the information about a person who is an employee
public abstract class Employee extends Person implements Payment{
    private String empId = null;
    private int startYear = Year.now().getValue();
    private Payment payment;

    public Employee(String lname, String fname){
        super(lname, fname);
        int id = super.getRandomId(2000, 3000);
        empId = getEmployeeIdString() + Integer.toString(id);
    }

    public String getIdString(){
        return empId;
    }
    public int getStartYear(){
        return startYear;
    }
    public void setStartYear(final int startYear){
        if (startYear > 2000 && (startYear <= (Year.now().getValue()))) {
            this.startYear = startYear;
        }
    }
    public Payment getPayment(){
        return payment;
    }
    public void setPayment(Payment payment){
        if (payment != null) {
            this.payment = payment;
        }
    }
    @Override
    public double calculatePayment(){
        double salary = 0.0;
        if (payment != null) {
            salary = payment.calculatePayment();
        }
        return salary;
    }
    protected abstract String getEmployeeIdString();
}
