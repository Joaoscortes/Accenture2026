package io.altar.auth_example.dto;

public class AddressRequest {
    private String street;
    private String city;
    private String postalCode;
    private String country;

    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }
    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public boolean isValid() {
        return validField(street) && validField(city) && validField(postalCode) && validField(country);
    }

    private boolean validField(String value) {
        return value != null && !value.trim().isEmpty() && value.length() <= 255;
    }
}
