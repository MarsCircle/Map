package com.flightmap.map.Pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(value = {"countallId", "jsonDataCurrent","jsonDataToday"})
public class MapCountall {
    private Long countallId;

    private String jsonDataCurrent;

    private String jsonDataToday;

    private String allCountToday;

    private String countCurrent;

    private String departureAirportMostCurrent;

    private String arrivalAirportMostCurrent;

    private String departureAirportMostToday;

    private String arrivalAirportMostToday;

    private Date createTime;

    private Date updateTime;

    public MapCountall(Long countallId, String jsonDataCurrent, String jsonDataToday, String allCountToday, String countCurrent, String departureAirportMostCurrent, String arrivalAirportMostCurrent, String departureAirportMostToday, String arrivalAirportMostToday, Date createTime, Date updateTime) {
        this.countallId = countallId;
        this.jsonDataCurrent = jsonDataCurrent;
        this.jsonDataToday = jsonDataToday;
        this.allCountToday = allCountToday;
        this.countCurrent = countCurrent;
        this.departureAirportMostCurrent = departureAirportMostCurrent;
        this.arrivalAirportMostCurrent = arrivalAirportMostCurrent;
        this.departureAirportMostToday = departureAirportMostToday;
        this.arrivalAirportMostToday = arrivalAirportMostToday;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public MapCountall(String jsonDataCurrent, String jsonDataToday, String allCountToday, String countCurrent, String departureAirportMostCurrent, String arrivalAirportMostCurrent, String departureAirportMostToday, String arrivalAirportMostToday) {
        this.jsonDataCurrent = jsonDataCurrent;
        this.jsonDataToday = jsonDataToday;
        this.allCountToday = allCountToday;
        this.countCurrent = countCurrent;
        this.departureAirportMostCurrent = departureAirportMostCurrent;
        this.arrivalAirportMostCurrent = arrivalAirportMostCurrent;
        this.departureAirportMostToday = departureAirportMostToday;
        this.arrivalAirportMostToday = arrivalAirportMostToday;
    }


    public MapCountall(String jsonDataCurrent, String jsonDataToday, String allCountToday, String countCurrent) {
        this.jsonDataCurrent = jsonDataCurrent;
        this.jsonDataToday = jsonDataToday;
        this.allCountToday = allCountToday;
        this.countCurrent = countCurrent;
    }
    public MapCountall() {
        super();
    }

    public Long getCountallId() {
        return countallId;
    }

    public void setCountallId(Long countallId) {
        this.countallId = countallId;
    }

    public String getJsonDataCurrent() {
        return jsonDataCurrent;
    }

    public void setJsonDataCurrent(String jsonDataCurrent) {
        this.jsonDataCurrent = jsonDataCurrent == null ? null : jsonDataCurrent.trim();
    }

    public String getJsonDataToday() {
        return jsonDataToday;
    }

    public void setJsonDataToday(String jsonDataToday) {
        this.jsonDataToday = jsonDataToday == null ? null : jsonDataToday.trim();
    }

    public String getAllCountToday() {
        return allCountToday;
    }

    public void setAllCountToday(String allCountToday) {
        this.allCountToday = allCountToday == null ? null : allCountToday.trim();
    }

    public String getCountCurrent() {
        return countCurrent;
    }

    public void setCountCurrent(String countCurrent) {
        this.countCurrent = countCurrent == null ? null : countCurrent.trim();
    }

    public String getDepartureAirportMostCurrent() {
        return departureAirportMostCurrent;
    }

    public void setDepartureAirportMostCurrent(String departureAirportMostCurrent) {
        this.departureAirportMostCurrent = departureAirportMostCurrent == null ? null : departureAirportMostCurrent.trim();
    }

    public String getArrivalAirportMostCurrent() {
        return arrivalAirportMostCurrent;
    }

    public void setArrivalAirportMostCurrent(String arrivalAirportMostCurrent) {
        this.arrivalAirportMostCurrent = arrivalAirportMostCurrent == null ? null : arrivalAirportMostCurrent.trim();
    }

    public String getDepartureAirportMostToday() {
        return departureAirportMostToday;
    }

    public void setDepartureAirportMostToday(String departureAirportMostToday) {
        this.departureAirportMostToday = departureAirportMostToday == null ? null : departureAirportMostToday.trim();
    }

    public String getArrivalAirportMostToday() {
        return arrivalAirportMostToday;
    }

    public void setArrivalAirportMostToday(String arrivalAirportMostToday) {
        this.arrivalAirportMostToday = arrivalAirportMostToday == null ? null : arrivalAirportMostToday.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}