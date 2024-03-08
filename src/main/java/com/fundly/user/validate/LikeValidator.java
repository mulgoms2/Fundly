package com.fundly.user.validate;

import com.fundly.project.model.ProjectMapper;
import com.persistence.dto.LikeDto;
import com.persistence.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LikeValidator implements Validator {

    @Autowired
    private MessageSource messageSource;

    @Override
    public boolean supports(Class<?> clazz) {

        return LikeDto.class.equals(clazz);

    }

    @Override
    public void validate(Object target, Errors errors) {

//        LikeDto likedto = (LikeDto) target;
//        ProjectDto pjdto = pjdao.getByPjId(likedto.getPj_id());
//
//        // 프로젝트 존재 여부 확인 및 유효성 검사
//        if (pjdto == null) {
//            String errorMessage = messageSource.getMessage("like.validation.error.notfound", null, null);
//            errors.rejectValue("project_id", "project.notfound", errorMessage);
//        }
    }
}
