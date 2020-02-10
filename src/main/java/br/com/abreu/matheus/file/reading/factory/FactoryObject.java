package br.com.abreu.matheus.file.reading.factory;

import br.com.abreu.matheus.file.reading.model.ClientModel;
import br.com.abreu.matheus.file.reading.model.SalesModel;
import br.com.abreu.matheus.file.reading.model.SellerModel;

public class FactoryObject {

    public static Object factoryObject(String line) {

        try {
            String[] split = line.split("ç");

            if(!split[0].isEmpty()){

                switch (split[0]) {

                    case("001"):
                        SellerModel seller = new SellerModel();
                        seller.setCpf(split[1]);
                        seller.setSellerName(split[2]);
                        seller.setSalary(Double.parseDouble(split[3]));

                        return seller;

                    case("002"):
                        ClientModel client = new ClientModel();
                        client.setCnpj(split[1]);
                        client.setClientName(split[2]);
                        client.setArea(split[3]);

                        return client;

                    case("003"):
                        SalesModel sell = new SalesModel();
                        sell.setSaleId(split[1]);
                        sell.setItems(split[2]);
                        sell.setSellerName(split[3]);

                        return sell;
                }
            } else {
                System.out.println("Error: " + "Id inválido");
            }
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return null;
    }
}
