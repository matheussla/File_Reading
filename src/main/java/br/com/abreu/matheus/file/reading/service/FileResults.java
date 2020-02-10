package br.com.abreu.matheus.file.reading.service;

import br.com.abreu.matheus.file.reading.dto.ReportDto;
import br.com.abreu.matheus.file.reading.model.ClientModel;
import br.com.abreu.matheus.file.reading.model.SalesModel;
import br.com.abreu.matheus.file.reading.model.SellerModel;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class FileResults {

    public ReportDto resultWriter(List<SellerModel> sellers, List<ClientModel> clients, List<SalesModel> sales) {

        ReportDto report = new ReportDto();
        report.setSeller(sellers.size());
        report.setClient(clients.size());
        worstSellerHighestSeller(sales, report);

        return report;
    }

    private void worstSellerHighestSeller(List<SalesModel> sales, ReportDto report) {
        Map<String, Float> idsSales = new HashMap<>();
        AtomicReference<Float> total = new AtomicReference<>((float) 0);

        for (SalesModel salesModel : sales) {
            total.set((float) 0);
            salesModel.getItems()
                    .forEach(item -> total.updateAndGet(v -> (float) (v + item.getPrice())));
            idsSales
                    .put(salesModel.getSaleId() + ", " + salesModel.getSellerName(), total.get());
        }

        List<String> worst = new ArrayList<>();
        List<String> best = new ArrayList<>();
        Float saleWorsest = Float.MAX_VALUE;
        Float saleHighest = Float.MIN_VALUE;

        for (Map.Entry<String, Float> entry : idsSales.entrySet()) {
            if (entry.getValue() <= saleWorsest) {
                if (!entry.getValue().equals(saleWorsest)) {
                    worst.clear();
                }
                worst.add(entry.getKey());
                saleWorsest = entry.getValue();
            }
            if (entry.getValue() >= saleHighest) {
                if (!entry.getValue().equals(saleHighest)) {
                    best.clear();
                }
                best.add(entry.getKey());
                saleHighest = entry.getValue();
            }
        }

        report.setIdSale(best);
        report.setWorstSeller(worst);
    }
}
