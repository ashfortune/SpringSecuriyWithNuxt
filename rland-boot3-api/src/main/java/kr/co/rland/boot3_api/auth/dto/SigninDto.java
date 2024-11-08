package kr.co.rland.boot3_api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninDto {
    private String userId;
    private String userPwd;
}
