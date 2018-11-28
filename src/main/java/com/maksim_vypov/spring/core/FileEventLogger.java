package com.maksim_vypov.spring.core;

import org.apache.commons.io.FileUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {

    private String fileName;
    private File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(new File(fileName), event.getMsg(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void init() throws IOException {
        this.file = new File(fileName);
        if (!this.file.canWrite()) {
            throw new IOException("Can not write to file:" + fileName);
        }
    }
}
