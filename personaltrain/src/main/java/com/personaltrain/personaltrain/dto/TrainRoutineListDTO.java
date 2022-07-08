package com.personaltrain.personaltrain.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TrainRoutineListDTO {
    private String nameRoutine;
    private LocalDate dateTrain;
    private short timeBegin;
    private short timeEnd;
    private String description; 
}