package com.fundly.pay.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionHandlerUtil {
    // 예외 처리
    public static void handleException(String methodName, Exception e) {
        log.error("{} : {}\n {}\n", methodName, e.getMessage(), e.getStackTrace());
        throw new RuntimeException(e);
    }
}
