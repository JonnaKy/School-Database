package SchoolDatabase;
//gives the payment for employees who get paid by the hour
public class HourBasedPayment implements Payment{
    private double eurosPerHour = 0.0;
    private double hours = 0.0;

    public double getEurosPerHour(){
        return eurosPerHour;
    }
    public void setEurosPerHour(double eurosPerHour){
        if (eurosPerHour > 0.0) {
            this.eurosPerHour = eurosPerHour;
        }
    }
    public double getHours(){
        return hours;
    }
    public void setHours(double hours){
        if ( hours > 0.0) {
            this.hours = hours;
        }
    }
    public double calculatePayment(){
        double payment = eurosPerHour * hours;
        return payment;
    }

}
