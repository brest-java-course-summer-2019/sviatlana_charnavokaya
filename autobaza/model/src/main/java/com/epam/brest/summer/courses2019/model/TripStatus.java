package com.epam.brest.summer.courses2019.model;

public class TripStatus {

    /**
     * TripStatus Id.
     */
    private Integer tripStatusId;

    /**
     * TripStatus name.
     */
    private String tripStatusName;


    public Integer getTripStatusId() {
        return tripStatusId;
    }

    public void setTripStatusId(Integer tripStatusId) {
        this.tripStatusId = tripStatusId;
    }

    public String getTripStatusName() {
        return tripStatusName;
    }

    public void setTripStatusName(String tripStatusName) {
        this.tripStatusName = tripStatusName;
    }

    @Override
    public String toString() {
        return "TripStatus {"
                + "tripStatusId=" + tripStatusId
                + ", tripStatusName=" + tripStatusName
                + "}";
    }
}
