package com.example.hulya.yemekhane.viewmodel;


/**
 * Created by hulyacetin on 24.08.2017.
 */

public class AboutUsVM {


    public int person_photo;
    public String person_name;
    public String person_information;
    public String github, linkedin;

    public int getPerson_photo() {
        return person_photo;
    }

    public void setPerson_photo(int person_photo) {
        this.person_photo = person_photo;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getPerson_information() {
        return person_information;
    }

    public void setPerson_information(String person_information) {
        this.person_information = person_information;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}
