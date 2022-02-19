package ma.my.grant.models;

public class LegalGuardian {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private float annualIncome;

    public LegalGuardian() {
    }

    public LegalGuardian(int id, String firstName, String lastName, String phoneNumber, float annualIncome) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.annualIncome = annualIncome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(float annualIncome) {
        this.annualIncome = annualIncome;
    }
}
