package com.example.domolandproject;

public class coachData {

    private Integer id;
    private String coachId;
    private String name;
    private String address;
    private String gender;
    private Integer phoneNum;
    private String status;

    public coachData(Integer id, String coachId, String name, String address, String gender, Integer phoneNum, String status){
        this.id =  id;
        this.coachId = coachId;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.phoneNum = phoneNum;
        this.status = status;
    }
    public Integer getId(){
        return id;
    }
    public String getCoachId(){
        return coachId;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getGender(){
        return gender;
    }
    public Integer getPhoneNum(){
        return phoneNum;
    }
    public String getStatus(){
        return status;
    }
}
