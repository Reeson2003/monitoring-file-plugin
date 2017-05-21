package ru.reeson2003.file_plugin;

import ru.reeson2003.Parameter;

/**
 * Date: 21.05.2017.
 * Time: 21:49.
 *
 * @author Pavel Gavrilov.
 */
public class ParameterImpl implements Parameter {
    private String name = "Файл не задан";
    private String value = "";
    private Integer requestPeriod = 0;
    private boolean requestStatus = false;
    private FileMonitor fileMonitor;

    public ParameterImpl() {
        this.fileMonitor = new FileMonitor(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String s) {
        fileMonitor.setFilePath(s);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String s) {
        this.value = s;
    }

    @Override
    public Integer getRequestPeriod() {
        return requestPeriod;
    }

    @Override
    public void setRequestPeriod(Integer period) {
        fileMonitor.setRequestPeriod(period);
    }

    @Override
    public boolean getRequestStatus() {
        return requestStatus;
    }

    @Override
    public void setRequestStatus(boolean b) {
        fileMonitor.setMonitoring(b);
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void changeReqestedPeriod(Integer period) {
        this.requestPeriod = period;
    }

    public void changeRequestStatus(boolean b) {
        requestStatus = b;
    }
}
