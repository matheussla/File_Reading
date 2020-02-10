package br.com.abreu.matheus.file.reading.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

public class FolderWatcher {

    private String file;

    public FolderWatcher(String path) throws IOException {

        this.file = path + "/data/in/";

        this.init();
        this.startWacther();
    }

    private void init() {

        File path = new File(this.file);
        File[] files = path.listFiles(file -> file.getName().endsWith(".txt"));

        for(int i = 0; i < files.length; i++) {
            try {
                new FileReading().fileReading(files[0]);
                files[0].delete();
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void startWacther() throws IOException {

        Path dir = Paths.get(this.file);

        WatchService watcher = dir.getFileSystem().newWatchService();
        dir.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);

        for (;;) {

            try {
                WatchKey key;
                try {
                    key = watcher.take();
                } catch (InterruptedException e) {
                    return;
                }

                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();

                    if (kind == OVERFLOW) {
                        continue;
                    }

                    if (event.kind() == StandardWatchEventKinds.ENTRY_CREATE) {

                        String fread = this.file + event.context().toString();
                        File file = new File(fread);
                        new FileReading().fileReading(file);
                        file.delete();
                    }
                }
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
