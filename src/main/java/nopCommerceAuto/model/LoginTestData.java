package nopCommerceAuto.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginTestData {
    private String email;
    private String password;
    private boolean expectSuccess;
}
