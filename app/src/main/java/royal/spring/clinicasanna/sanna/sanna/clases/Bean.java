package royal.spring.clinicasanna.sanna.sanna.clases;

public class Bean {
    String initial, firstName, middleName, lastName;

    public Bean(String initial, String firstName, String middleName, String lastName) {
        this.initial = initial;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }


    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
