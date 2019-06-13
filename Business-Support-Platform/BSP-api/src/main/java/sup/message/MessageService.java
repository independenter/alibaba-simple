package sup.message;

public interface MessageService {
    public boolean sendMobileMessage(java.lang.String mobile, java.lang.String message);

    public boolean sendEmailMessage(java.lang.String email, java.lang.String message);
}
