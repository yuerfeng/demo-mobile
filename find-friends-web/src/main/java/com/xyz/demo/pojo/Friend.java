package com.xyz.demo.pojo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Friend extends DataEntity implements Serializable{
        private String name;
        private String nickName;
}