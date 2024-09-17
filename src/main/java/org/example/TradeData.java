package org.example;

import java.util.Date;

public class TradeData {
    Date dateTime;
    double open,high,low,close;
    long volume;
    double value;
    String direction;
    int tradesval;
public TradeData(Date datetime, double open, double high, double low, double close, long volume, double value, String direction, int trades){
        this.dateTime=datetime;
        this.open=open;
        this.high=high;
        this.low=low;
        this.close=close;
        this.volume=volume;
        this.value=value;
        this.direction=direction;
        this.tradesval=trades;
    }
}