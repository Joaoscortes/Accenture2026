package io.altar.auth_example.dto;

public class RecoverPasswordRequest {
    private String email;
    
    // Default constructor
    public RecoverPasswordRequest() {}
    
    // Constructor with parameters
    public RecoverPasswordRequest(String email) {
        this.email = email;
    }
    
    // Getters and setters
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
}