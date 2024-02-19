package com.fundly.chat.validate;

import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

//파일 유효성 검사.
public class FileValidator implements ConstraintValidator<ValidFile, MultipartFile> {
    private static final Tika tika = new Tika();

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext constraintValidatorContext) {
//        파일이 널이거나, 비어있거나. 이미지 파일로 확장자를 속였을 경우 false
        return multipartFile != null && !multipartFile.isEmpty() && isImageFile(multipartFile);
    }

    public static boolean isImageFile(MultipartFile file) {
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
            List<String> allowFileList = Arrays.asList("image/jpeg", "image/pjpeg", "image/png", "image/gif", "image/bmp", "image/x-windows-bmp");

//            실제 파일의 mimeType 검출. 확장자를 속여도 소용 없다.
            String mimeType = tika.detect(inputStream);

            System.out.println("MimeType : " + mimeType);

//            허가된 확장자 중에 일치하는 것이 있는지 확인
            boolean isValid = allowFileList.stream().anyMatch(allowFile -> allowFile.equalsIgnoreCase(mimeType));

            return isValid;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
