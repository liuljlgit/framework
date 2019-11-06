package com.cloud.frame.frameauth.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cloud.frame.authclient.entity.GatewayRoute;

/**
  * 接口类 IGatewayRouteDao
  * @author lijun
  */
@Repository
public interface IGatewayRouteDao extends IBaseMapper<GatewayRoute>{

    //------------------------ custom code begin ------------------------//
    
    //======================== custom code end ========================//

}