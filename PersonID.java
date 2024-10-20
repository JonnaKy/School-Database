package SchoolDatabase;
//calculates the person's birthdate from theis social security number (Finnish type)
// information on how it's calculated from: https://dvv.fi/en/personal-identity-code2
public class PersonID {
    private String birthDate = ConstantValues.NO_BIRTHDATE;

    public String getBirthDate(){
        return birthDate;
    }
    public String setPersonId(final String personID){
        String result = null;
        String birthday = null;
        boolean checkIDnum = false;
        boolean checkBirthDay = false;
        boolean checkChar = false;
        String checkYear = null;
        if (personID != null && (!personID.isEmpty()) && (personID.length() == 11)) {
            checkIDnum = checkPersonIDNumber(personID);
            if (checkIDnum){
                if (personID.charAt(6) == '+') {
                    checkYear = "18";
                }else if (personID.charAt(6) == '-') {
                    checkYear = "19";
                }else {
                    checkYear = "20";
                }  
                birthday = personID.substring(0, 2) + "."
                                + personID.substring(2, 4) + "."
                                + checkYear + personID.substring(4, 6);
                checkBirthDay = checkBirthDate(birthday);
                if (checkBirthDay){
                    checkChar = checkValidCharacter(personID);
                }
            }
            if (checkIDnum && checkBirthDay &&checkChar) {
                this.birthDate = birthday.substring(0,10);
                result = "Ok";
            }else if (!checkIDnum) {
                result = "Invalid birthday!";
            } else if (!checkBirthDay) {
                result = "Invalid birthday!";
            }else if (!checkChar) {
                result = "Incorrect check mark!";
            }
        }else {
            result = "Invalid birthday!";
        }
        return result;
    }
    private boolean checkPersonIDNumber(final String personID){
        if (personID.length() != 11 || (personID.charAt(6) != '+' && personID.charAt(6) != '-' &&
            personID.charAt(6) != 'A')) {
            return false;
        }else{
            return true;
        }
    }
    private boolean checkLeapYear(int year){
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        }else {
            return false;
        }
    }
    private boolean checkValidCharacter(final String personID){
        String [] remainder = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13",
                                "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", 
                                "26", "27", "28", "29", "30"};
        String [] character = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E",
                             "F", "H", "J", "K", "L", "M", "N", "P", "R", "S", "T", "U", "V", "W", "X", "Y"};
        double calc = 0.0;
        int finalRemainder = 0;
        String comparing = null;
        String lastChar = personID.substring(10);
        String calcBirthDate = personID.substring(0, 6) + personID.substring(7, 10);
        calc = Double.parseDouble(calcBirthDate);
        calc = calc / 31;
        double decimals = calc % 1;
        finalRemainder = (int)(Math.round(decimals * 31));
        comparing = String.valueOf(finalRemainder);
        int index = 0;
        for(int i = 0; i < remainder.length; i++){
            if (remainder[i].equals(comparing)) {
                comparing = character[index];
                index = i;
            }
        }
        if (character[index].equals(lastChar)) {
            return true;
        }else {
            return false;
        }
    }
    private boolean checkBirthDate(final String date){
        if (date.length() != 10) {
            return false;
        }
        String dayString = date.substring(0,2);
        String monthString = date.substring(3, 5);
        String yearString = date.substring(6, 10);
        int day = Integer.valueOf(dayString);
        int month = Integer.valueOf(monthString);
        int year = Integer.valueOf(yearString);

        if (year >= 0 && (month >= 1 && month <= 12) && (day >= 1 && day <= 31)) {
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day > 30) {
                    return false;
                }
            }else if (month == 2) {
                if (checkLeapYear(year)) {
                    if (day > 29) {
                        return false;
                    }
                }else if (day > 28) {
                    return false;
                }
            }
            return true;
        }else{
            return false;
        }
    }    
}
