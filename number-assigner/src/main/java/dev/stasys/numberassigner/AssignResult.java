package dev.stasys.numberassigner;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "assign_result")
@Data
public class AssignResult {
    @NotNull
    @Id
    @Column(name = "input_string")
    private String inputString;

    @NotNull
    @Column(name = "value")
    private Long value;
}
