package br.com.abreu.matheus.file.reading.dto;

import java.util.List;

public class ReportDto {

    private List<String> idSale;
    private int client;
    private int seller;
    private List<String> worstSeller;

    public List<String> getIdSale() {
        return idSale;
    }

    public void setIdSale(List<String> idSale) {
        this.idSale = idSale;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }

    public List<String> getWorstSeller() {
        return worstSeller;
    }

    public void setWorstSeller(List<String> worstSeller) {
        this.worstSeller = worstSeller;
    }
}
