package com.fundly.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.InputStream;
import java.util.Random;

@Service
public class MailSendService {

    @Autowired
    JavaMailSenderImpl mailSender;

    private int authNumber;

    // 난수 발생
    public void makeRandomNumber() {
        Random r = new Random();
        authNumber = r.nextInt(888888) + 111111;
    }

    //이메일 보낼 양식
    public String joinEmail(String email) {
        makeRandomNumber();

        /* 랜덤 인증번호를 저장(email 별 구분 ) 하는 table 은 ? */
        String setFrom = email; //
        String toMail = email;
        String title = "회원 가입 인증 이메일 입니다."; // 이메일 제목
        String content =
                "<strong>Fundly 홈페이지를 방문해주셔서 감사합니다.</strong>" +
                        "<br><br>" +
                        "인증 번호는 " + authNumber + "입니다." +
                        "<br>" +
                        "해당 인증번호를 인증번호 확인란에 기입하여 주세요.";
        mailSend(setFrom, toMail, title, content, authNumber);
        return Integer.toString(authNumber);
    }

    public void mailSend(String setFrom, String toMail, String title, String content, int authNumber) {
        MimeMessage message = mailSender.createMimeMessage();

        // true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(setFrom);
            helper.setTo(toMail);
            helper.setSubject(title);
            // true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
            helper.setText(content, true);
//            redisUtil.setDataExpire(toMail, String.valueOf(authNumber), 60 * 5L); // 5분 동안 redis에 저장
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
