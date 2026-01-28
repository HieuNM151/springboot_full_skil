package vn.hieunm.dto.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class SampleDTO implements Serializable {
    private Integer id;
    private String name;
}
