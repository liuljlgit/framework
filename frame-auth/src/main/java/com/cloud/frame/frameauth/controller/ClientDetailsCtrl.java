package com.cloud.frame.frameauth.controller;

import com.cloud.frame.authclient.feign.ClientDetailsFeign;
import com.cloud.frame.authclient.req.ClientDetailsReq;
import com.cloud.ftl.ftlbasic.exception.BusiException;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * @author Liulj
 * @version v 0.1 2019/11/6 11:44
 */
@Slf4j
@RestController
@Validated
@Api(tags = "自定义clientdetails接口")
public class ClientDetailsCtrl implements ClientDetailsFeign {

    @Autowired
    private JdbcClientDetailsService clientDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveClientDetails(@RequestBody ClientDetailsReq clientDetailsReq) {
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId(clientDetailsReq.getClientId());
        baseClientDetails.setClientSecret(clientDetailsReq.getClientSecret());
        baseClientDetails.setAccessTokenValiditySeconds(clientDetailsReq.getAccessTokenValiditySeconds());
        baseClientDetails.setRefreshTokenValiditySeconds(clientDetailsReq.getRefreshTokenValiditySeconds());
        if(CollectionUtils.isNotEmpty(clientDetailsReq.getAutoApproveScopes())){
            baseClientDetails.setAutoApproveScopes(clientDetailsReq.getAutoApproveScopes());
        }
        if(CollectionUtils.isNotEmpty(clientDetailsReq.getScope())){
            baseClientDetails.setScope(clientDetailsReq.getScope());
        }
        if(CollectionUtils.isNotEmpty(clientDetailsReq.getResourceIds())){
            baseClientDetails.setResourceIds(clientDetailsReq.getResourceIds());
        }
        if(CollectionUtils.isNotEmpty(clientDetailsReq.getAuthorizedGrantTypes())){
            baseClientDetails.setAuthorizedGrantTypes(clientDetailsReq.getAuthorizedGrantTypes());
        }
        if(CollectionUtils.isNotEmpty(clientDetailsReq.getRegisteredRedirectUris())){
            baseClientDetails.setRegisteredRedirectUri(clientDetailsReq.getRegisteredRedirectUris());
        }
        if(Objects.nonNull(clientDetailsReq.getAdditionalInformation())){
            baseClientDetails.setAdditionalInformation(clientDetailsReq.getAdditionalInformation());
        }
        if(StringUtils.isEmpty(baseClientDetails.getClientId())){
            throw new BusiException("clientId不能为空");
        }
        try {
            clientDetailsService.loadClientByClientId(baseClientDetails.getClientId());
            clientDetailsService.updateClientDetails(baseClientDetails);
        } catch (NoSuchClientException e){
            clientDetailsService.addClientDetails(baseClientDetails);
        }
    }

    @Override
    public void deleteClientDetails(@RequestParam("clientId") String clientId) {
        if(StringUtils.isEmpty(clientId)){
            throw new BusiException("clientId不能为空");
        }
        clientDetailsService.removeClientDetails(clientId);
    }
}
