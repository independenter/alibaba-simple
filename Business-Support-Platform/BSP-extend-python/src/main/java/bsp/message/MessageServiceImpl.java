package bsp.message;

import sup.message.MessageService;

public class MessageServiceImpl implements MessageService {
    @Override
    public boolean sendMobileMessage(String mobile, String message) {
        System.out.println("发送验证码给手机");
        return true;
    }

    @Override
    public boolean sendEmailMessage(String email, String message) {
        System.out.println("发送验证码给邮箱");
        return true;
    }
}
