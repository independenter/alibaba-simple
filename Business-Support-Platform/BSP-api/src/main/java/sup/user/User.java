package sup.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

    private Integer id;
    private String username;
    private String password;
    private String realname;
    private String email;
}
