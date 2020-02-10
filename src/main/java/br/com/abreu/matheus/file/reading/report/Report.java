package br.com.abreu.matheus.file.reading.report;

import br.com.abreu.matheus.file.reading.dto.ReportDto;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Report {

    private String path = System.getProperty("user.dir") + "/data/out/";

    public void writerReport(Object result) throws IOException {

        String report = "report.txt";

        String lineReport = (" Clients:" + ((ReportDto) result).getClient()
                + " Sellers:" + ((ReportDto) result).getSeller()
                + " Most expensive sale id:" + ((ReportDto) result).getIdSale().toString()
                + " Worst salesman id:" + ((ReportDto) result).getWorstSeller());

        String file = path + report;
        FileWriter arq = new FileWriter(file);
        PrintWriter saveArq = new PrintWriter(arq);
        saveArq.print(lineReport);
        arq.close();
    }
}
