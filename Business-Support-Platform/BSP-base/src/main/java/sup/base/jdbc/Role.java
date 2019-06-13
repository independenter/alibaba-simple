package sup.base.jdbc;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable, Cloneable {

    private int role_id;

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    private String role_name;
    private int role_right;
    private String email;
    private String tel;
    private String use_tag;
    private Timestamp start_date;
    private Timestamp end_date;

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public int getRole_right() {
        return role_right;
    }

    public void setRole_right(int role_right) {
        this.role_right = role_right;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUse_tag() {
        return use_tag;
    }

    public void setUse_tag(String use_tag) {
        this.use_tag = use_tag;
    }

    public Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(Timestamp start_date) {
        this.start_date = start_date;
    }

    public Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Timestamp end_date) {
        this.end_date = end_date;
    }
}
