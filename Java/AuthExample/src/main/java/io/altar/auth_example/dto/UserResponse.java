package io.altar.auth_example.dto;

import io.altar.auth_example.model.User;

public class UserResponse {
    private long id;
    private String username;
    private String email;
    private int age;
    private String role;
    private String photoUrl;

    public UserResponse() {}

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.role = user.getRole() != null ? user.getRole().name() : null;
        this.photoUrl = user.getPhotoUrl();
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public String getRole() {
        return role;
    }
}
