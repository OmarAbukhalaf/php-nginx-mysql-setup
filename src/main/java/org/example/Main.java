package org.example;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.example.ProcessTrade.groupTrades;
import static org.example.ProcessTrade.readTrades;
import static org.example.WritetoFile.clearFiles;
import static org.example.WritetoFile.writefile;


public class Main {
    public static void main(String[] args) throws IOException, ParseException {
    String input="C:\\Users\\Omar\\Desktop\\TradeFile\\src\\main\\java\\org\\example\\2010.txt";
    String out1="1minute.txt";
    String out5="5minute.txt";
    clearFiles();
    List<TradeData> trades=readTrades(input);
    List<TradeData> grouped1=groupTrades(trades,1);
    List<TradeData> grouped5=groupTrades(trades,5);
    writefile(grouped1,"1minute.txt");
    writefile(grouped5,"5minute.txt");




    }
}