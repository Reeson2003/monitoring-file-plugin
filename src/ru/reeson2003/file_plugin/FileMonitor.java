package ru.reeson2003.file_plugin;


import ru.reeson2003.Parameter;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Date: 21.05.2017.
 * Time: 22:15.
 *
 * @author Pavel Gavrilov.
 */
public class FileMonitor implements Parameter, Runnable{
    private volatile File file = null;
    private Integer requestPeriod = 0;
    private String name = "no file";
    private String value = "";
    private String configuration = "no file";
    private volatile boolean requestStatus = false;


    private void setFilePath(String filePath) {
        file = new File(filePath);
        if(file.isFile())
            this.name = file.getName();
        else {
            this.name = "no file";
            file = null;
        }

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String s) {}

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String s) {}

    @Override
    public Integer getRequestPeriod() {
        return requestPeriod;
    }

    public void setRequestPeriod(Integer requestPeriod) {
        if(requestPeriod > 0) {
            this.requestPeriod = requestPeriod;
        } else {
            this.requestPeriod = 0;
        }
    }

    @Override
    public boolean getRequestStatus() {
        return requestStatus;
    }

    @Override
    public void setRequestStatus(boolean requestStatus) {
        setMonitoring(requestStatus);
    }

    @Override
    public String getConfiguration() {
        return configuration;
    }

    @Override
    public void setConfiguration(String configuration) {
        setFilePath(configuration);
    }

    private void setMonitoring(boolean monitoring) {
        requestStatus = monitoring;
        if(requestStatus) {
            Thread thread = new Thread(this);
            thread.setDaemon(true);
            thread.start();
        }
    }

    @Override
    public void run() {
        while (requestStatus) {
            if (file != null) {
                value = getLastModified();
                try {
                    Thread.sleep(requestPeriod * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getLastModified() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return sdf.format(file.lastModified());
    }
}
