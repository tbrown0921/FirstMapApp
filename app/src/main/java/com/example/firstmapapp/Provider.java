package com.example.firstmapapp;

public class Provider {

    private int providerid;
    private String providername;
    private String primarypractice;
    private String Phone;
    private String Address1;
    private String Address2;
    private String City;
    private String State;
    private String Zip;
    private int Status;
    private String FeeSchedule;
    private String Office;
    private int catvalue;

    public Provider(int providerid, String providername, String primarypractice, String Phone, String Address1, String Address2, String City, String State, String Zip, int Status, String FeeSchedule, String Office, int catvalue) {
        this.providerid = providerid;
        this.providername = providername;
        this.primarypractice = primarypractice;
        this.Phone = Phone;
        this.Address1 = Address1;
        this.Address2 = Address2;
        this.City = City;
        this.State = State;
        this.Zip = Zip;
        this.Status = Status;
        this.FeeSchedule = FeeSchedule;
        this.Office = Office;
        this.catvalue = catvalue;
    }

    public int getproviderid() {
        return providerid;
    }

    public String getprovidername() {
        return providername;
    }

    public String getprimarypractice() {
        return primarypractice;
    }

    public String getPhone() {
        return Phone;
    }

    public String getAddress1() {
        return Address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }
    public String getFeeSchedule() {
        return FeeSchedule;
    }

    public int getStatus() {
        return Status;
    }

    public String getOffice() {
        return Office;
    }

    public int getcatvalue() {
        return catvalue;
    }
}
