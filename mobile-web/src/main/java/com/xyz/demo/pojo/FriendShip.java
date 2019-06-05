package com.xyz.demo.pojo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class FriendShip extends DataEntity implements Serializable{
        private Integer userId;
        private Integer friendId;
}