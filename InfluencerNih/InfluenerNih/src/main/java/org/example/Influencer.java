package org.example;

public class Influencer {
    private String name;
    private String platform;
    private String username;
    private int followers;
    private String status;
    private String imagePath;

    // Constructor
    public Influencer(String name, String platform, String username, int followers, String status, String imagePath) {
        this.name = name;
        this.platform = platform;
        this.username = username;
        this.followers = followers;
        this.status = status;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
