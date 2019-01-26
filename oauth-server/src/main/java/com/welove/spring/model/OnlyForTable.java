package com.welove.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.Date;

public class OnlyForTable {

}


@Entity
class OauthClientDetails{
    @Id
    private String clientId;
    private String resourceId;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private String accessTokenValidity;
    @Lob
    private String additionalInformation;
    private String autoApprove;
}

@Entity
class OauthClientToken{

    @Id
    private String authenticationId;

    @Lob
    private String token;

    private String tokenId;

    private String userName;

    private String clientId;
}

@Entity
class oauthAccessToken{

    @Id
    private String authenticationId;

    private String tokenId;
    @Lob
    private String token;

    private String userName;

    private String clientId;

    @Lob
    private String authentication;


    private String refreshToken;
}

@Entity
class oauthRefreshToken{

    @Id
    private String tokenId;

    @Lob
    private String token;
    @Lob
    private String authentication;
}

@Entity
class OauthCode{
    @Id
    private String id;

    private String code;

    @Lob
    private String authentication;
}

@Entity
class OauthApprovals{
    @Id
    @Column(name = "userId")
    private String userId;

    @Column(name = "clientId")
    private String clientId;

    @Column(name = "scope")
    private String scope;

    @Column(name = "state")
    private String status;

    @Column(name = "expiresAt")
    private Date expiresAt;

    @Column(name = "lastModifiedAt")
    private Date lastModifiedAt;
}

@Entity
class ClientDetails{

    @Id
    @Column(name = "appId")
    private String appId;

    @Column(name = "resourceIds")
    private String resourceIds;

    @Column(name = "appSecret")
    private String appSecret;

    @Column(name = "scope")
    private String scope;

    @Column(name = "grantType")
    private String grantType;

    @Column(name = "redirectUrl")
    private String redirectUrl;

    @Column(name = "authorities")
    private String authorities;

    @Column(name = "access_token_validity")
    private String access_token_validity;

    @Column(name = "refresh_token_validity")
    private String refresh_token_validity;

    @Column(name = "additionalInformation")
    private String additionalInformation;

    @Column(name = "authApproveScopes")
    private String authApproveScopes;
}