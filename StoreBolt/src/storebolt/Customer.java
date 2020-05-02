package storebolt;

import java.time.LocalDate;

public class Customer {
    private String name;
    private String email;
    private String dateOfBirth;
    private String password;

    public Customer(String name, String email, String dateOfBirth, String password){
        this.name = name;
        this.email= email;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String toCsv(){
        return getName()+","+ getEmail()+","+ getDateOfBirth()+","+ getPassword();
    }

}
