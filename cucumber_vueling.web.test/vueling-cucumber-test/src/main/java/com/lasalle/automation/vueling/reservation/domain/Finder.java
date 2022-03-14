package com.lasalle.automation.vueling.reservation.domain;

public class Finder {

    private String origin;
    private String destination;
    private String outboundFly;
    private String returnFly;
    private String passengers;

    public Finder() {

    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOutboundFly() {
        return outboundFly;
    }

    public void setOutboundFly(String outboundFly) {
        this.outboundFly = outboundFly;
    }

    public String getReturnFly() {
        return returnFly;
    }

    public void setReturnFly(String returnFly) {
        this.returnFly = returnFly;
    }

    public String getPassengers() {
        return passengers;
    }

    public void setPassengers(String passengers) {
        this.passengers = passengers;
    }

    @Override
    public String toString() {
        return "SearchDto{" +
                "origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", outboundFly='" + outboundFly + '\'' +
                ", returnFly='" + returnFly + '\'' +
                ", passengers='" + passengers + '\'' +
                '}';
    }
}
