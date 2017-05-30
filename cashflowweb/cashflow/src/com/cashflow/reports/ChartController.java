package com.cashflow.reports;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Omer Faruk KURT
 * @Created on date 18.12.2016 20:31:13
 * @blog http://kurtomerfaruk.com
 * @mail kurtomerfaruk@gmail.com
 */
@ManagedBean
@ViewScoped
public class ChartController implements java.io.Serializable {

    private static final long serialVersionUID = 3479985883495611426L;

    private String month;
    private String data;
    private List<Data> dataList;

    @PostConstruct
    public void init() {

    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

}