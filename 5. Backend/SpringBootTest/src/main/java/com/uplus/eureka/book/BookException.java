package com.uplus.eureka.book;

import com.uplus.eureka.advice.EurekaException;

public class BookException extends EurekaException {
  public BookException(String message) {
    // 부모 생성자 호출
    super(message);
  }
}
