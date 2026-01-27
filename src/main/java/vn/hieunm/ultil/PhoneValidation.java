package vn.hieunm.ultil;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class PhoneValidation implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public void initialize(PhoneNumber phoneNumberNo) {
    }

    @Override
    public boolean isValid(String phoneNo, ConstraintValidatorContext cxt) {
        if (phoneNo == null) {
            return false;
        }

        // Validate số điện thoại định dạng "0902345345" (10 chữ số)
        if (phoneNo.matches("\\d{10}")) return true;

            // Validate số điện thoại có dấu gạch ngang, dấu chấm hoặc khoảng cách: 090-234-4567
        else if (phoneNo.matches("\\d{3}[-\\.\\s]\\d{3}[-\\.\\s]\\d{4}")) return true;

            // Validate số điện thoại có mã mở rộng (extension) độ dài từ 3 đến 5 số
        else if (phoneNo.matches("\\d{3}-\\d{3}-\\d{4}\\s(x|ext)\\d{3,5}")) return true;

            // Validate số điện thoại có mã vùng nằm trong dấu ngoặc đơn ()
        else return phoneNo.matches("\\(\\d{3}\\)-\\d{3}-\\d{4}");
    }
}
