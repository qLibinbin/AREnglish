package com.arenglish.utils;


import java.util.regex.Pattern;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

/**
 * 发送邮件Util
 */
public class MailUtil {
	/**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    //邮箱
    private static String mailServerHost = "smtp.126.com";
    private static String mailSenderAddress = "ARenglish@126.com";
    private static String mailSenderNick = "AREnglish";
    private static String mailSenderUsername = "ARenglish@126.com";
    private static String mailSenderPassword = "arenglish456";
    
    /**
     * 发送 邮件方法 (Html格式，支持附件)
     * @param receiver  接收者
     * @param checkCode 验证码
     * @return boolean
     */
    public static boolean sendEmail(String receiver,String checkCode) {
         
        try {
            HtmlEmail email = new HtmlEmail();
            // 配置信息
            email.setHostName(mailServerHost);
            email.setFrom(mailSenderAddress,mailSenderNick);
            email.setAuthentication(mailSenderUsername,mailSenderPassword);
            email.setCharset("UTF-8");
            email.setSubject("AREnglish验证码");
            email.setHtmlMsg("AREnglish用户注册的验证码为："+checkCode);
            // 收件人
            if (null != receiver) { 
            	email.addTo(receiver);
            }
            email.send();
            System.out.println("向"+receiver+"邮件发送成功！");
            return true;
        } catch (EmailException e) {
            e.printStackTrace();
        } 
        return false;

    }
    /**
     * 校验邮箱
     * 
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }
}
