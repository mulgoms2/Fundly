package com.fundly.project.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


@Slf4j
@Data
@NoArgsConstructor
public class StoryImage { //커맨드 객체
    MultipartFile file;
    Dimension dimension;
    class Dimension {
        int width;
        int height;
    }

    long fileSize;
    String contentType;

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

        is.close(); //닫아주지 않으면 fail to delete original file after copy ~ IOException 터짐
    }

    public void setFileSize(){
        this.fileSize = this.file.getSize();
    }

    public void setDimension() throws IOException {
    }

    public void setContentType(){
        this.contentType = this.file.getContentType();
    }

}


