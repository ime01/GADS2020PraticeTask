package com.flowz.gads2020praticetask.roomdb.hoursdatabse;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "hours_table" )
public class dbHoursModel {

    public String name;
    public Integer hours;
    public String country;
    public String badgeUrl;
    @PrimaryKey(autoGenerate = true)
    private int id;

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    public dbHoursModel(String name, Integer hours, String country, String badgeUrl) {
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }
}
