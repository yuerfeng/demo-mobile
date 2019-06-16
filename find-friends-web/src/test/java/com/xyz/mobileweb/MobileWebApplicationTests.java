//package com.xyz.mobileweb;
//
//import com.xyz.demo.socket.client.event.*;
//import net.openmob.mobileimsdk.server.protocal.ErrorCode;
//import net.openmob.mobileimsdk.server.protocal.Protocal;
//import net.openmob.mobileimsdk.server.protocal.ProtocalFactory;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.net.DatagramSocket;
//import java.net.InetAddress;
//
////@RunWith(SpringRunner.class)
////@SpringBootTest
//public class MobileWebApplicationTests {
//    private final static String TAG = MobileWebApplicationTests.class.getSimpleName();
//
//    static DatagramSocket localUDPSocket = null;
//
//    @Test
//    public static void main(String[] arg) throws Exception{
//        // 设置AppKey
//        ConfigEntity.appKey = "5418023dfd98c579b6001741";
//         ChatBaseEventImpl baseEventListener = null;
//        //
//         ChatTransDataEventImpl transDataListener = null;
//        //
//         MessageQoSEventImpl messageQoSListener = null;
//        // 设置服务器ip和服务器端口
////			ConfigEntity.serverIP = "192.168.82.138";
////			ConfigEntity.serverIP = "rbcore.openmob.net";
////			ConfigEntity.serverUDPPort = 7901;
//
//        // MobileIMSDK核心IM框架的敏感度模式设置
////			ConfigEntity.setSenseMode(SenseMode.MODE_10S);
//
//        // 开启/关闭DEBUG信息输出
////	    	ClientCoreSDK.DEBUG = false;
//
//        // 设置事件回调
//        baseEventListener = new ChatBaseEventImpl();
//        transDataListener = new ChatTransDataEventImpl();
//        messageQoSListener = new MessageQoSEventImpl();
//        ClientCoreSDK.getInstance().setChatBaseEvent(baseEventListener);
//        ClientCoreSDK.getInstance().setChatTransDataEvent(transDataListener);
//        ClientCoreSDK.getInstance().setMessageQoSEvent(messageQoSListener);
//
//        ConfigEntity.serverIP = "127.0.0.1";
//        ConfigEntity.serverUDPPort = 7901;
//
//        DatagramSocket localUDPSocket = (ConfigEntity.localUDPPort == 0?
//                new DatagramSocket():new DatagramSocket(ConfigEntity.localUDPPort));//_Utils.LOCAL_UDP_SEND$LISTENING_PORT);
//        localUDPSocket.setReuseAddress(true);
//        //开始登陆
//        int code = sendLogin("user1","111111","");
//        System.out.println("登陆code="+code);
//        code = sendLoginout();
//        System.out.println("登出code="+code);
//        System.out.println("登出code="+code);
//    }
//
//    static int sendLogin(String loginUserId, String loginToken, String extra)
//    {
//        byte[] b = ProtocalFactory.createPLoginInfo(loginUserId, loginToken, extra).toBytes();
//        int code = send(b, b.length);
//        if(code == 0)
//        {
//            ClientCoreSDK.getInstance().setCurrentLoginUserId(loginUserId);
//            ClientCoreSDK.getInstance().setCurrentLoginToken(loginToken);
//            ClientCoreSDK.getInstance().setCurrentLoginExtra(extra);
//        }
//
//        return code;
//    }
//
//    static public int sendLoginout()
//    {
//        int code = ErrorCode.COMMON_CODE_OK;
//        if(ClientCoreSDK.getInstance().isLoginHasInit())
//        {
//            byte[] b = ProtocalFactory.createPLoginoutInfo(
//                    ClientCoreSDK.getInstance().getCurrentLoginUserId()).toBytes();
//            code = send(b, b.length);
//            if(code == 0)
//            {
//                // do nothing
//            }
//        }
//        ClientCoreSDK.getInstance().release();
//        return code;
//    }
//
//    static int sendKeepAlive()
//    {
//        byte[] b = ProtocalFactory.createPKeepAlive(
//                ClientCoreSDK.getInstance().getCurrentLoginUserId()).toBytes();
//        return send(b, b.length);
//    }
//
//    static public int sendCommonData(String dataContentWidthStr, String to_user_id)
//    {
//        return sendCommonData(dataContentWidthStr, to_user_id, -1);
//    }
//    static public int sendCommonData(String dataContentWidthStr, String to_user_id, int typeu)
//    {
//        return sendCommonData(dataContentWidthStr, to_user_id, null, typeu);
//    }
//    static public int sendCommonData(String dataContentWidthStr, String to_user_id
//            , String fingerPrint, int typeu)
//    {
//        return sendCommonData(dataContentWidthStr, to_user_id, true, fingerPrint, typeu);
//    }
//    static public int sendCommonData(String dataContentWidthStr, String to_user_id
//            , boolean QoS, String fingerPrint, int typeu)
//    {
//        return sendCommonData(ProtocalFactory.createCommonData(dataContentWidthStr
//                , ClientCoreSDK.getInstance().getCurrentLoginUserId(), to_user_id, QoS, fingerPrint, typeu));
//    }
//
//    static public int sendCommonData(Protocal p)
//    {
//        if(p != null)
//        {
//            byte[] b = p.toBytes();
//            int code = send(b, b.length);
//            if(code == 0)
//            {
////                if(p.isQoS() && !QoS4SendDaemon.getInstance().exist(p.getFp()))
////                    QoS4SendDaemon.getInstance().put(p);
//            }
//            return code;
//        }
//        else
//            return ErrorCode.COMMON_INVALID_PROTOCAL;
//    }
//    static private int send(byte[] fullProtocalBytes, int dataLen)
//    {
//        if(!ClientCoreSDK.getInstance().isInitialed())
//            return ErrorCode.ForC.CLIENT_SDK_NO_INITIALED;
//
//        DatagramSocket ds = localUDPSocket;
//        if(ds != null && !ds.isConnected())
//        {
//            try
//            {
//                if(ConfigEntity.serverIP == null)
//                {
//                    //Log.w(TAG, "【IMCORE】send数据没有继续，原因是ConfigEntity.server_ip==null!");
//                    return ErrorCode.ForC.TO_SERVER_NET_INFO_NOT_SETUP;
//                }
//
//                ds.connect(InetAddress.getByName(ConfigEntity.serverIP), ConfigEntity.serverUDPPort);
//            }
//            catch (Exception e)
//            {
//                //Log.w(TAG, "【IMCORE】send时出错，原因是："+e.getMessage(), e);
//                return ErrorCode.ForC.BAD_CONNECT_TO_SERVER;
//            }
//        }
//        return UDPUtils.send(ds, fullProtocalBytes, dataLen) ? ErrorCode.COMMON_CODE_OK : ErrorCode.COMMON_DATA_SEND_FAILD;
//    }
//
//}
