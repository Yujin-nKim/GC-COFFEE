package com.example.gccoffe.model;

import lombok.Getter;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.regex.Pattern;

@Getter
public class Email {
    private final String address;

    public Email(String address) {
        //검증. spring에서 제공하는 Assert이용
        Assert.notNull(address, "address should not be null"); //(false일때)null이 되었을때 예외가 throw 될때 전달할 메세지 작성
        //문자열에 대한 validation 로직
        Assert.isTrue(address.length() >= 4 && address.length() <= 50, "address length  must be between 4 and 50 characters.");
        //메세지 포맷이 맞는지 검증
        Assert.isTrue(checkAddress(address), "Invalid email address");
        this.address = address;
    }

    private static boolean checkAddress(String address) {
        return Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b", address); //regexr.com 참고
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return "Email{" +
                "address='" + address + '\'' +
                '}';
    }
}
