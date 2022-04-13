package site.metacoding.blogv3.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import site.metacoding.blogv3.handler.ex.CustomException;

public class UtilValid {

    public static void 요청에러처리(BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {

            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fe : bindingResult.getFieldErrors()) {
                // System.out.println(fe.getField());
                // System.out.println(fe.getDefaultMessage());
                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }
            // data 리턴인지 html 리턴인지 구분해준다.
            throw new CustomException(errorMap.toString());
        }

    }
}
