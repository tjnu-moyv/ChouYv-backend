package cn.chouyv.common.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author SurKaa
 */
@Data
public class StudentLoginRequest implements Serializable {

    private static final long serialVersionUID = -4822032801251789016L;

    private String username;
    private String password;
}
