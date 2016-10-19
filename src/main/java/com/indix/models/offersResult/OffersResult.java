package com.indix.models.offersResult;

import com.indix.models.offer.Offer;

import java.util.List;

public class OffersResult {
    private int count;
    private List<Offer> offers;

    public int getCount() {
        return count;
    }

    public List<Offer> getOffers() {
        return offers;
    }
}
