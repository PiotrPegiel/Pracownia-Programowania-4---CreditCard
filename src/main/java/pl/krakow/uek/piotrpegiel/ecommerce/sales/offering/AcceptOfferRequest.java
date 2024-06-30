package pl.krakow.uek.piotrpegiel.ecommerce.sales.offering;

public class AcceptOfferRequest {
    private String firstName;
    private String lastName;
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public AcceptOfferRequest setFirstName(String fname) {
        this.firstName = fname;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AcceptOfferRequest setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AcceptOfferRequest setEmail(String email) {
        this.email = email;
        return this;
    }
}