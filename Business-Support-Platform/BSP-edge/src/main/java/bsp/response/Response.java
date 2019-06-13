package bsp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@AllArgsConstructor
public class Response implements Serializable {

    private String code;
    private String message;

    public Response() {
        this.code="0000";
        this.message="success";
    }

    public static final Response USERNAME_PASSWORD_INVALID = new Response("1001","USERNAME_PASSWORD_INVALID");
    public static final Response MOBILE_OR_EMAIL_REQUIED = new Response("1002","MOBILE_OR_EMAIL_REQUIED");
    public static final Response SEND_VERIFYCODE_FAILED = new Response("1003","SEND_VERIFYCODE_FAILED");
    public static final Response SUCCESS = new Response();
    public static final Response VERIFYCODE_INVALID= new Response("1004","VERIFYCODE_INVALID");



    public static Response exception(Exception e){
        return new Response("9999",e.getMessage());
    }

}
