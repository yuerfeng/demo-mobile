package com.xyz.demo.enums;

public enum VerifyCodeTypeEnum {
    /**
     * 注册
     */
    REGIST(0,"regist"),
    /**
     * 修改密码
     */
    MODIFY_PASS(1,"modifypass"),
    /**
     * 登录
     */
    LOGIN(2,"login");

    private String name;
    private Integer id;
    private VerifyCodeTypeEnum(Integer id,String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static VerifyCodeTypeEnum getNameById(Integer id){
        if(id == null){
            return null;
        }
        for(VerifyCodeTypeEnum enu : VerifyCodeTypeEnum.values()){
            if(enu.getId().equals(id)){
                return enu;
            }
        }
        return null;
    }
}
