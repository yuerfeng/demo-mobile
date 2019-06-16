package com.xyz.demo.annotation;

import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = {Exception.class})
public @interface MobileTrans {
}
