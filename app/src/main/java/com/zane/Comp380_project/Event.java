package com.zane.Comp380_project;

public class Event {



    private String time;
    private String description;
    private String title;

    public Event(String title, String time, String description) {
        this.time= time;
        this.description = description;
        this.title = title;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
