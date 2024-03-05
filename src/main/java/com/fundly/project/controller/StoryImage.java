package com.fundly.project.controller;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


@Slf4j
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class StoryImage { //커맨드 객체
    private MultipartFile file;
    private Dimension dimension; //접근제어자를 설정하는 문제..
    private long fileSize;
    private String contentType;

    @ToString
    @Getter
    class Dimension {
        int width;
        int height;
    }



    public void setFile(MultipartFile file){
        this.file = file;
    }

    public void setMetaData() throws IOException {
        Dimension dimension = new Dimension();
        InputStream is = file.getInputStream();
        BufferedImage bimg = ImageIO.read(is);
        dimension.width = bimg.getWidth();
        dimension.height = bimg.getHeight();
        log.error("\n\n width={} \n\n", dimension.width);
        log.error("\n\n height={} \n\n", dimension.height);
        this.dimension = dimension;
        this.fileSize = this.file.getSize();
        this.contentType = this.file.getContentType();
        log.error("\n\n size={} \n\n", fileSize);
        log.error("\n\n contentType={} \n\n", contentType);


        is.close(); //닫아주지 않으면 fail to delete original file after copy ~ IOException 터짐
    }



    //    public void setFileSize(){
//        this.fileSize = this.file.getSize();
//    }
//
//    public void setContentType(){
//        this.contentType = this.file.getContentType();
//    }

}


