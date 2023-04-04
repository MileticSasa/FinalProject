package com.example.finalproject.Response;

import com.example.finalproject.Model.EventsLists;

public class CompeteEventResponse {
    private EventsLists data;

    public EventsLists getData() {
        return data;
    }

    public void setData(EventsLists data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return data.getSlider().get(0).getTitle();
    }
}
