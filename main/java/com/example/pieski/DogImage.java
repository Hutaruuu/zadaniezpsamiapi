package com.example.pieski;

import com.google.gson.annotations.SerializedName;

public class DogImage {

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}