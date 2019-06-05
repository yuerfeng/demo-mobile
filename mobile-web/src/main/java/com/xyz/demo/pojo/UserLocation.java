package com.xyz.demo.pojo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class UserLocation extends DataEntity implements Serializable{
        private Integer userId;
        private String lat;
        private String lng;
}