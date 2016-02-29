package com.lecodesoft.safepalmobile.storyrecycler;

/**
 * Created by root on 11/16/15.
 */
public class Story {
    int singleStoryActionTakenThumbnail;
    String singleStoryIncidentLocation;
    String singleStoryPostTime;
    String singleStoryIncidentDescription;
    String singleStoryIncidentHappenedTime;
    String singleStoryIncidentStatus;
    String singleStoryIncidentType;


    public Story(int singleStoryActionTakenThumbnail,
                 String singleStoryIncidentLocation,
                 String singleStoryPostTime,
                 String singleStoryIncidentDescription,
                 String singleStoryIncidentHappenedTime,
                 String singleStoryIncidentStatus,
                 String singleStoryIncidentType
                 ) {
        this.singleStoryActionTakenThumbnail = singleStoryActionTakenThumbnail;
        this.singleStoryIncidentLocation = singleStoryIncidentLocation;
        this.singleStoryPostTime = singleStoryPostTime;
        this.singleStoryIncidentDescription = singleStoryIncidentDescription;
        this.singleStoryIncidentHappenedTime = singleStoryIncidentHappenedTime;
        this.singleStoryIncidentStatus = singleStoryIncidentStatus;
        this.singleStoryIncidentType = singleStoryIncidentType;
    }

    public String getSingleStoryIncidentType() {
        return singleStoryIncidentType;
    }

    public void setSingleStoryIncidentType(String singleStoryIncidentType) {
        this.singleStoryIncidentType = singleStoryIncidentType;
    }

    public int getSingleStoryActionTakenThumbnail() {
        return singleStoryActionTakenThumbnail;
    }

    public void setSingleStoryActionTakenThumbnail(int singleStoryActionTakenThumbnail) {
        this.singleStoryActionTakenThumbnail = singleStoryActionTakenThumbnail;
    }

    public String getSingleStoryIncidentLocation() {
        return singleStoryIncidentLocation;
    }

    public void setSingleStoryIncidentLocation(String singleStoryIncidentLocation) {
        this.singleStoryIncidentLocation = singleStoryIncidentLocation;
    }

    public String getSingleStoryPostTime() {
        return singleStoryPostTime;
    }

    public void setSingleStoryPostTime(String singleStoryPostTime) {
        this.singleStoryPostTime = singleStoryPostTime;
    }

    public String getSingleStoryIncidentDescription() {
        return singleStoryIncidentDescription;
    }

    public void setSingleStoryIncidentDescription(String singleStoryIncidentDescription) {
        this.singleStoryIncidentDescription = singleStoryIncidentDescription;
    }

    public String getSingleStoryIncidentHappenedTime() {
        return singleStoryIncidentHappenedTime;
    }

    public void setSingleStoryIncidentHappenedTime(String singleStoryIncidentHappenedTime) {
        this.singleStoryIncidentHappenedTime = singleStoryIncidentHappenedTime;
    }

    public String getSingleStoryIncidentStatus() {
        return singleStoryIncidentStatus;
    }

    public void setSingleStoryIncidentStatus(String singleStoryIncidentStatus) {
        this.singleStoryIncidentStatus = singleStoryIncidentStatus;
    }
}
