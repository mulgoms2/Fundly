package com.fundly.project.controller;

import com.fundly.project.exception.ImageValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class StoryImageValidator implements Validator {
    static final int MAX_WIDTH = 1240;
    static final int MAX_HEIGHT = 1600;
    static final long MAX_SIZE = 5000000;
    static final List<String> POSSIBLE_CONTENT_TYPE = List.of("image/gif","image/jpeg","image/png");

    @Override
    public boolean supports(Class<?> clazz) {

        log.error ("\n\n storyImageValidator is called (supports) \n\n");
        return clazz.isAssignableFrom(StoryImage.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        log.error ("\n\n storyImageValidator is called (validate) \n\n");
        StoryImage storyImage = (StoryImage)target;
        try {
            storyImage.setMetaData();
        } catch (IOException e) {
            //IOException이 발생하면 검증할 필드를 set할 수 없으므로 validation이 실패
            //errors.reject()를 호출해야 하나 고민했지만, 검증 자체가 이루어지지 못한 것이라 그냥 예외를 던짐.
            e.printStackTrace();
            ImageValidationException ex = new ImageValidationException("image validation failed");
            ex.initCause(e);
            throw ex;
        }

        //content-type 체크 (확장자 jpeg, png, gif)
        if(!POSSIBLE_CONTENT_TYPE.contains(storyImage.getContentType()))
            errors.rejectValue("contentType","invalidContentType", POSSIBLE_CONTENT_TYPE.toArray(String[]::new), null );


        //빈 파일인지 체크
        if(storyImage.getFile().isEmpty() || storyImage.getFile() == null)
            errors.rejectValue("file","invalidFile");
        //5MB이하의 크기
        if(storyImage.getFileSize()> MAX_SIZE)
            errors.rejectValue("fileSize","tooBigFileSize", new String[]{MAX_SIZE+"MB"},null);

        //파일 사이즈 width 1240px height 1600px
        if(storyImage.getDimension().width > MAX_WIDTH || storyImage.getDimension().height > MAX_HEIGHT)
            errors.rejectValue("dimension","invalidDimension",new String[]{"1240px", "1600px"}, null);

    }
}
