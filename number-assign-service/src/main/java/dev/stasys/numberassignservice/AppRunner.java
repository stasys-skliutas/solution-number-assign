package dev.stasys.numberassignservice;

import dev.stasys.numberassignservice.externalapi.ValueAssignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AppRunner {
    private final ValueAssignClient valueAssignClient;
    private final String pathToInputFile;

    public AppRunner(
            ValueAssignClient valueAssignClient,
            @Value("${pathToInputFile}") String pathToInputFile
    ) {
        this.valueAssignClient = valueAssignClient;
        this.pathToInputFile = pathToInputFile;
    }

    public void run() throws Exception {
        System.out.println("Sending input text job has started");
        try (InputTextReader inputTextReader = new InputTextReader(pathToInputFile)) {
            while (inputTextReader.hasNextTextLine()) {
                String inputText = inputTextReader.nextTextLine();
                System.out.println("Processing inputText: " + inputText);
                try {
                    System.out.println(inputText + " -> " + valueAssignClient.assignValue(inputText));
                } catch (Exception e) {
                    System.out.printf("Failed to assign value for input text '%s'. See logs for additional information.%n", inputText);
                    log.error(e.getMessage(), e);
                }
            }
        } finally {
            System.out.println("Job has ended");
        }
    }
}
