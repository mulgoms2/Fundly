package com.fundly.project.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectStoryDto {
    private String purpose;
    private String budget;
    private String sched;
    private String intro;
    private String reward;

}
