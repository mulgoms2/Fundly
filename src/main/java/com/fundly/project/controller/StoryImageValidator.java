package com.fundly.project.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

public class StoryImageValidator implements Validator {
    int MAX_WIDTH = 1240;
    int MAX_HEIGHT = 1600;
    long MAX_SIZE = 5000000;
    List<String> POSSIBLE_CONTENT_TYPE = List.of("image/gif","image/jpeg","image/png");

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(StoryImage.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StoryImage storyImage = (StoryImage)target;




        //content-type 체크 (확장자 jpeg, png, gif)
        if(!POSSIBLE_CONTENT_TYPE.contains(storyImage.getContentType()))
            errors.rejectValue("contentType","invalidContentType", POSSIBLE_CONTENT_TYPE.toArray(String[]::new), null );



        //빈 파일인지 체크
        if(storyImage.getFile().isEmpty())
            errors.rejectValue("fileSize","emptyFile");
        //5MB이하의 크기
        if(storyImage.getFileSize()> MAX_SIZE)
            errors.rejectValue("fileSize","tooBigFileSize", new String[]{MAX_SIZE+""},null);

//파일 사이즈 width 1240px height 1600px

    }
}
