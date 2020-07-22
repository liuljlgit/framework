package com.cloud.frame.authclient.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 14:32
 */
@Data
public class OauthTokenResp implements Serializable {

    private String access_token;

    private String token_type;

    private String refresh_token;

    private Long expires_in;

    private String scope;
}
