package br.mack.entidade;

import java.util.Date;

public class HeartRate {
    private int id;
    private int bpm;
    private Date day;

    public HeartRate() { }

    public HeartRate(int id, int bpm, Date day) {
        this.id = id;
        this.bpm = bpm;
        this.day = day;
    }

    public HeartRate(int bpm, Date day) {
        this.bpm = bpm;
        this.day = day;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getBpm() {
        return bpm;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}
