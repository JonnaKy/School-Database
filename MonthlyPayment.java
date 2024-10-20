package SchoolDatabase;
//Gives the payment amount to employees whose payment type is monthly payment
public class MonthlyPayment implements Payment {
    private double salary = 0.0;

    public double getSalary(){
        return salary;
    }
    public void setSalary(double salary){
        if (salary > 0.0) {
            this.salary = salary;
        }
    }
    public double calculatePayment(){
        return salary;
    }
}
