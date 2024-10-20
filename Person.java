package SchoolDatabase;

import java.util.Random;
//Hold the information about a persons names and get the birth date from person's social security number
public abstract class Person {

    private String firstName = ConstantValues.NO_NAME;
    private String lastName = ConstantValues.NO_NAME;
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    public Person(String lname, String fname){
        setFirstName(fname);
        setLastName(lname);
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        if (firstName != null && (!firstName.isEmpty())) {
            this.firstName = firstName;
        }
    }
    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        if (lastName != null && (!lastName.isEmpty())) {
            this.lastName = lastName;
        }
    }
    public String getBirthDate(){
        return birthDate;
    }
    public String setBirthDate(String personId){
        PersonID idChecker  = new PersonID();
        if (personId != null && (idChecker.setPersonId(personId) == "Ok")) {
            this.birthDate = idChecker.getBirthDate();
            return birthDate;
        }else{
            return "No change";
        }
    }
    protected int getRandomId(final int min, final int max){
        Random random_id = new Random();
        int id = random_id.nextInt(max - min) + min;
        return id;
    }
    public abstract String getIdString();

}
