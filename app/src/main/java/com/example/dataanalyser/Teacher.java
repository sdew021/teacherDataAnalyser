package com.example.dataanalyser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Teacher {
    String sem, branch, id;
    int nOS;
    List<String> subjects;

    public Teacher() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getnOS() {
        return nOS;
    }

    public void setnOS(int nOS) {
        this.nOS = nOS;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> sub) {
        this.subjects = sub;
    }

}
