package dev.stasys.numberassigner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class NumberAssignService {
    private final AssignedNumberRepository assignedNumberRepository;

    @Transactional
    public AssignResult assignNumber(String inputText) {
        return assignedNumberRepository.findAssignResultByInputString(inputText).orElseGet(() -> {
            var assignResult = new AssignResult();
            assignResult.setInputString(inputText);
            assignResult.setValue(assignedNumberRepository.getNextSeqValue());
            return assignedNumberRepository.save(assignResult);
        });
    }
}
