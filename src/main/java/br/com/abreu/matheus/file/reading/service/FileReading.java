package br.com.abreu.matheus.file.reading.service;

import br.com.abreu.matheus.file.reading.factory.FactoryObject;
import br.com.abreu.matheus.file.reading.model.ClientModel;
import br.com.abreu.matheus.file.reading.model.SalesModel;
import br.com.abreu.matheus.file.reading.model.SellerModel;
import br.com.abreu.matheus.file.reading.report.Report;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileReading {

    public void fileReading(File file) throws IOException {

        BufferedReader bufferedReader =
                new BufferedReader(new FileReader(file));

        String line = bufferedReader.readLine();

        List<SellerModel> sellers = new ArrayList<>();
        List<ClientModel> clients = new ArrayList<>();
        List<SalesModel> sales = new ArrayList<>();

        while (Objects.nonNull(line)) {

            Object obj = FactoryObject.factoryObject(line);

            if (obj instanceof SellerModel) {
                sellers.add((SellerModel) obj);
            } else if (obj instanceof ClientModel) {
                clients.add((ClientModel) obj);
            } else if (obj instanceof SalesModel) {
                sales.add((SalesModel) obj);
            }
            line = bufferedReader.readLine();
        }

        try {
            Object result = (new FileResults().resultWriter(sellers, clients, sales));

            new Report().writerReport(result);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
