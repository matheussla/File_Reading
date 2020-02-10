package br.com.abreu.matheus.file.reading.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SalesModel {

    private String saleId;
    private List<ItemModel> items;
    private String sellerName;

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public List<ItemModel> getItems() {
        return items;
    }

    public void setItems(String insertItems) {
        String regex = "(\\w{1,9}-\\d{1,9}-\\d{1,9}[.]{0,1}\\d{0,4})";
        Pattern pattern = Pattern.compile("[\\[]" + regex + "[,]" + regex + "[,]" + regex + "[\\]]");
        Matcher matcher = pattern.matcher(insertItems);
        matcher.find();

        List<ItemModel> items = new ArrayList<>();

        for (int idx = 1; idx <= matcher.groupCount(); idx++) {

            String[] item = matcher.group(idx).split("-");
            ItemModel insert = new ItemModel();
            insert.setId(item[0]);
            insert.setQuantity(Float.parseFloat(item[1]));
            insert.setPrice(Float.parseFloat(item[2]));

            items.add(insert);
        }

        this.items = items;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}

