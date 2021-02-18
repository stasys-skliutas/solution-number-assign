package dev.stasys.numberassignservice.externalapi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name="valueAssignClient", url = "${serviceUrl}")
public interface ValueAssignClient {
    @PutMapping("?s={inputText}")
    AssignResultResponse assignValue(@PathVariable("inputText") String inputText);
}
