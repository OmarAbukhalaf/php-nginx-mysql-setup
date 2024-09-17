package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.List;


public class WritetoFile {
    private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void writefile(List<TradeData> trades, String outfile) {
        try (FileWriter writer = new FileWriter(outfile,true)) {
            for(TradeData trade:trades) {
                String formatteddate= df.format(trade.dateTime);
                writer.write(formatteddate+","+trade.open+","+ trade.high+","+
                        trade.low+","+trade.close+","+
                        trade.volume+","+trade.value+","+
                        trade.direction+","+trade.tradesval+"\n"
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearFiles() {
            try {
                Files.write(Paths.get("5minute.txt"), new byte[0]);
                Files.write(Paths.get("1minute.txt"), new byte[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

    }


}
