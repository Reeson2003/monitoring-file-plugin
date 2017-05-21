package ru.reeson2003.file_plugin;


import java.io.File;
import java.text.SimpleDateFormat;

/**
 * Date: 21.05.2017.
 * Time: 22:15.
 *
 * @author Pavel Gavrilov.
 */
public class FileMonitor implements Runnable{
    private ParameterImpl parameter;
    private volatile File file = null;
    private volatile Integer requestPeriod;
    private volatile boolean isMonitoring;

    public FileMonitor(ParameterImpl parameter) {
        this.parameter = parameter;
    }

    public void setFilePath(String filePath) {
        file = new File(filePath);
        if(file.isFile())
            parameter.changeName(file.getName());
        else {
            parameter.changeName("Файл не найден");
            file = null;
        }

    }

    public void setRequestPeriod(Integer requestPeriod) {
        if(requestPeriod > 0) {
            this.requestPeriod = requestPeriod;
            parameter.changeReqestedPeriod(requestPeriod);
        } else {
            this.requestPeriod = null;
            parameter.changeReqestedPeriod(0);
        }
    }

    public void setMonitoring(boolean monitoring) {
        isMonitoring = monitoring;
        parameter.changeRequestStatus(monitoring);
        if(isMonitoring) {
            Thread thread = new Thread(this);
            thread.setDaemon(true);
            thread.start();
        }
    }

    @Override
    public void run() {
        while (isMonitoring) {
            if (file != null) {
                String s = getLastModified();
                parameter.setValue(s);
                try {
                    Thread.sleep(requestPeriod * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getLastModified() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String lastModified = sdf.format(file.lastModified());
        return lastModified;
    }
}
