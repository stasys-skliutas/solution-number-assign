package dev.stasys.numberassigner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface AssignedNumberRepository extends JpaRepository<AssignResult, String> {
    Optional<AssignResult> findAssignResultByInputString(String inputString);

    @Query(nativeQuery = true, value = "select next value for value_seq")
    Long getNextSeqValue();
}
