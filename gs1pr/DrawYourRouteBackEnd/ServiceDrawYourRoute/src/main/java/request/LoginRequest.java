package request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginRequest {

    @XmlElement
    public String nickName;
    @XmlElement
    public String password;

}
