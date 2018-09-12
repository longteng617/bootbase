package com.yunda.base.common.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取电脑的物理地址
 */
public class MacAddressUtils {

    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = request.getHeader("x-forwarded-for");
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress= inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15
            if(ipAddress.indexOf(",")>0){
                ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }


    public static String getMACAddress(String ip){
        String str = "";
        String macAddress = "";
        String strBr = "";
        try {
            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
            InputStreamReader ir = new InputStreamReader(p.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                System.out.println("str:"+str+"====================strBr:"+strBr);
                if (str != null) {
//                    if (str.indexOf("MAC Address") > 1) {
//                        macAddress = str.substring(str.indexOf("MAC Address") + 14, str.length());
//                        break;
//                    }
                    if(str.toUpperCase().contains("MAC")){
                        macAddress = str.substring(str.length()-17,str.length());
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return macAddress;
    }


    /**
     * 通过IP地址获取MAC地址
     * @param ip String,127.0.0.1格式
     * @return mac String
     * @throws Exception
     */
//    public static String getMACAddress(String ip){
//        String line = "";
//        String macAddress = "";
//        final String MAC_ADDRESS_PREFIX = "MAC Address = ";
//        final String LOOPBACK_ADDRESS = "127.0.0.1";
//        //如果为127.0.0.1,则获取本地MAC地址。
//        if (LOOPBACK_ADDRESS.equals(ip)) {
//            InetAddress inetAddress = null;
//            try {
//                inetAddress = InetAddress.getLocalHost();
//                //貌似此方法需要JDK1.6。
//                byte[] mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
//                //下面代码是把mac地址拼装成String
//                StringBuilder sb = new StringBuilder();
//                for (int i = 0; i < mac.length; i++) {
//                    if (i != 0) {
//                        sb.append("-");
//                    }
//                    //mac[i] & 0xFF 是为了把byte转化为正整数
//                    String s = Integer.toHexString(mac[i] & 0xFF);
//                    sb.append(s.length() == 1 ? 0 + s : s);
//                }
//                macAddress = sb.toString().trim().toUpperCase();
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            } catch (SocketException e) {
//                e.printStackTrace();
//            }
//            //把字符串所有小写字母改为大写成为正规的mac地址并返回
//            return macAddress;
//        }
//        //获取非本地IP的MAC地址
//        try {
//            Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
//            InputStreamReader isr = new InputStreamReader(p.getInputStream());
//            BufferedReader br = new BufferedReader(isr);
//
//            while ((line = br.readLine()) != null) {
//                if (line != null) {
//                    int index = line.indexOf(MAC_ADDRESS_PREFIX);
//                    if (index != -1) {
//                        macAddress = line.substring(index + MAC_ADDRESS_PREFIX.length()).trim().toUpperCase();
//                    }
//                }
//            }
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace(System.out);
//        }
//        return macAddress;
//    }

}
