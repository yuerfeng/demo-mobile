package com.xyz.demo.socket.mobile;

import net.openmob.mobileimsdk.server.ServerLauncher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ServerStart {
    private Logger logger = LoggerFactory.getLogger(ServerStart.class);
    private ServerLauncher sli;
    @PostConstruct
    public void start(){
        try{
            // 实例化后记得startup哦，单独startup()的目的是让调用者可以延迟决定何时真正启动IM服务
            sli = new ServerLauncherImpl();

            // 启动MobileIMSDK服务端的Demo
            sli.startup();

            // 加一个钩子，确保在JVM退出时释放netty的资源
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    sli.shutdown();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
            logger.error("ServerStart.start异常",e);
        }

    }
}
