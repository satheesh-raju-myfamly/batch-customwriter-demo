package com.example.batchcustomwriterdemo.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CompositeModel
{

    private Students students;
    private Persons persons;
    private Colleges colleges;

}
