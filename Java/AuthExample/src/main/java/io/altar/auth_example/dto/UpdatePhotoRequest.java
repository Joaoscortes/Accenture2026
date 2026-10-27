package io.altar.auth_example.dto;

public class UpdatePhotoRequest {
    private String photoUrl;

    public UpdatePhotoRequest() {}

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
