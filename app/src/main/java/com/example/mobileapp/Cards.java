package com.example.mobileapp;

public class Cards {

    String cardnum;
    String Cname;
    String ex_date;
    String CVV;

    public Cards(String cardnum, String cname, String ex_date, String CVV) {
        this.cardnum = cardnum;
        Cname = cname;
        this.ex_date = ex_date;
        this.CVV = CVV;
    }

    public String getCardnum() {
        return cardnum;
    }

    public String getCname() {
        return Cname;
    }

    public String getEx_date() {
        return ex_date;
    }

    public String getCVV() {
        return CVV;
    }
}
