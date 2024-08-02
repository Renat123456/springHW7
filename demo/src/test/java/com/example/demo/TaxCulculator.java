package com.example.demo;

public class TaxCulculator {
    private TaxResolver taxResolver;

    public TaxCulculator(TaxResolver taxResolver) {
        this.taxResolver = taxResolver;
    }

    public double getPriceWithTax(double price){
        double currentTax = taxResolver.getCurrentTax();
        return price + price * currentTax;

    }
}
