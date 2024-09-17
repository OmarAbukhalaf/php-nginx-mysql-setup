package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.SECOND;

public class ProcessTrade {
    public static List<TradeData> readTrades(String file) throws IOException, ParseException {
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<TradeData> trades =new ArrayList<>();
        BufferedReader br =new BufferedReader(new FileReader(file));
        String line;
        while ((line= br.readLine())!=null){
            String[] segment=line.split(",");
            Date datetime= df.parse(segment[0]);
            double open=Double.parseDouble(segment[1]);
            double high=Double.parseDouble(segment[2]);
            double low=Double.parseDouble(segment[3]);
            double close=Double.parseDouble(segment[4]);
            long volume=Long.parseLong(segment[5]);
            double value=Double.parseDouble(segment[6]);
            String direction=segment[9];
            int tradeval=Integer.parseInt(segment[10]);
            trades.add(new TradeData(datetime,open,high,low,close,volume,value,direction,tradeval));
        }
        return trades;
    }

    public static List<TradeData> groupTrades(List<TradeData> trades,int minutes){
        List<TradeData> groupedTrades=new ArrayList<>();
        Double previousClose= 0.0;
        for(int i=0;i<trades.size();i++) {
            Date datetime = getStartTime(trades.get(i).dateTime, minutes);
            double open = trades.get(i).open;
            double high = trades.get(i).high;
            double low = trades.get(i).low;
            double close = trades.get(i).close;
            long volume = trades.get(i).volume;;
            double value = trades.get(i).value;;
            int tradeval = trades.get(i).tradesval;
            i++;
            while(i<trades.size() && getStartTime(trades.get(i).dateTime,minutes).equals(datetime)){
                high=max(high,trades.get(i).high);
                low=min(low,trades.get(i).low);
                close=trades.get(i).close;
                volume+=trades.get(i).volume;
                value+=trades.get(i).value;
                tradeval+=trades.get(i).tradesval;
                i++;
            }
            String dir="";
            if(close==previousClose){
                dir="=";
            }else if(close>previousClose){
                dir="+";
            }else if(close<previousClose){
                dir="-";
            }
            previousClose=close;
        groupedTrades.add(new TradeData(getStartTime(datetime,minutes),open,high,low,close,volume,value,dir,tradeval));
        }
        return groupedTrades;
        }

    public static Date getStartTime(Date datetime, int interval){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);
        calendar.set(SECOND, 0);
        if(interval==5){
            int minute=calendar.get(MINUTE);
            calendar.set(MINUTE,(minute/5)*5);
        }
        return calendar.getTime();
    }
}
