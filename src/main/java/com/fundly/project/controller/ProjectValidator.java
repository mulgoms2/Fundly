package com.fundly.project.controller;


import com.persistence.dto.ProjectDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class ProjectValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ProjectDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProjectDto projectDto = (ProjectDto) target;

    }
}
