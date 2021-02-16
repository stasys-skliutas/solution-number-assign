package dev.stasys.numberassigner;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NumberAssignController {
    private final NumberAssignService numberAssignService;

    @PutMapping
    public AssignResult handleAssignNumber(@RequestParam("s") String inputString) {
        return numberAssignService.assignNumber(inputString);
    }
}
