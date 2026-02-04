package vn.hieunm.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseData<T> {

    /** HTTP status code */
    private int status;

    /** Message cho FE / người dùng */
    private String message;

    /** Dữ liệu thực */
    private T data;
}
