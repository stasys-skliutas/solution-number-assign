package dev.stasys.numberassignservice;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.zip.GZIPInputStream;

class InputTextReader implements AutoCloseable {
    private final BufferedReader reader;
    private String nextNumber;

    InputTextReader(String pathToInputFile) {
        try {
            var is = new FileInputStream(Paths.get(pathToInputFile).toFile());
            var gzipInputStream = new GZIPInputStream(is);
            this.reader = new BufferedReader(new InputStreamReader(gzipInputStream));
            nextNumber = getNextNonBlankNumber();
        } catch (IOException e) {
            throw new RuntimeException("Unable to initialize reader", e);
        }
    }

    public boolean hasNextTextLine() {
        return nextNumber != null;
    }

    private String getNextNonBlankNumber() throws IOException {
        String numRead;
        while ((numRead = reader.readLine()) != null) {
            if (!numRead.isBlank()) {
                break;
            }
        }
        return numRead;
    }

    String nextTextLine() {
        try {
            String result = nextNumber;
            nextNumber = getNextNonBlankNumber();
            return result;
        } catch (IOException e) {
            throw new RuntimeException("Unable to read next number", e);
        }
    }

    @Override
    public void close() throws Exception {
        if (reader != null) {
            reader.close();
        }
    }
}
