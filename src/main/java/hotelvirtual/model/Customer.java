package hotelvirtual.model;

public class Customer {

    public enum Gender {
        Male, Female;
    }

    private int id;
    private String name;
    private String surname;
    private Gender gender;
    private String passportId;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
