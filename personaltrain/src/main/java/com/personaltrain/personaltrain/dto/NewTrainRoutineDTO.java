package com.personaltrain.personaltrain.dto;


import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class NewTrainRoutineDTO {
    @NotNull(message = "Name Routine can't be null")
    private String nameRoutine;
    private LocalDate dateTrain;
    private short timeBegin;
    private short timeEnd;
    @NotNull(message = "Name Routine can't be null")
    private String description;
}
