package com.eeluo.licman;

import java.net.InetAddress;
import java.net.NetworkInterface;

public class ApplicationTest {
	public static void main(String[] args) throws Exception  
    {  
		//生成证书
		LicenseMake clicense=new LicenseMake("/licenseMakeConf.properties");  
      	clicense.create(); 
		//取得本机mac
		InetAddress ia = InetAddress.getLocalHost();
		System.out.println(getMACAddress(ia));
        //证书安装和验证
		LicenseVertify vlicense=new LicenseVertify(getMACAddress(ia)); // 项目唯一识别码，对应生成配置文件的subject  
        vlicense.uninstall();
        vlicense.install(System.getProperty("user.dir"));  
        vlicense.vertify();  
    } 
	private static String getMACAddress(InetAddress ia) throws Exception {
		// 获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		 
		// 下面代码是把mac地址拼装成String
		StringBuffer sb = new StringBuffer();
		 
		for (int i = 0; i < mac.length; i++) {
		if (i != 0) {
		sb.append("-");
		}
		// mac[i] & 0xFF 是为了把byte转化为正整数
		String s = Integer.toHexString(mac[i] & 0xFF);
		sb.append(s.length() == 1 ? 0 + s : s);
		}
		 
		// 把字符串所有小写字母改为大写成为正规的mac地址并返回
		return sb.toString().toUpperCase();
		}
}
