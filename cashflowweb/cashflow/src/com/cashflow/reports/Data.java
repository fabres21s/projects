package com.cashflow.reports;

import java.util.List;

/**
 *
 * @author Omer Faruk KURT
 * @Created on date 18.12.2016 20:30:07
 * @blog http://kurtomerfaruk.com
 * @mail kurtomerfaruk@gmail.com
 */
public class Data {

    private String name;
    private List<Double> data;
    private String type;

    public Data(String name, List<Double> data, String type) {
        this.name = name;
        this.data = data;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

	private String getType() {
		return type;
	}

	private void setType(String type) {
		this.type = type;
	}

}