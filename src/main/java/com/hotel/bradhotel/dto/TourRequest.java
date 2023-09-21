package com.hotel.bradhotel.dto;

import com.hotel.bradhotel.constant.TourCity;

import javax.validation.constraints.NotNull;

//DTO data Transfer Object
//裝雜項用，負責前端傳遞參數轉換，
public class TourRequest {

    //只留前端要傳遞的參數，多餘的刪除
    @NotNull
    private String tourName;
    @NotNull
    private TourCity city;
    @NotNull
    private String address;
    @NotNull
    private String tel;
    @NotNull
    private String imageUrl;
    @NotNull
    private Integer tickets;
    @NotNull
    private Integer stock;

    private String introduce;


    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public TourCity getCity() {
        return city;
    }

    public void setCity(TourCity city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
