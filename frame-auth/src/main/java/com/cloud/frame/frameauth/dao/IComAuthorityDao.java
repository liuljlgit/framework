package com.cloud.frame.frameauth.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cloud.frame.authclient.entity.ComAuthority;

/**
  * 接口类 IComAuthorityDao
  * @author lijun
  */
@Repository
public interface IComAuthorityDao extends IBaseMapper<ComAuthority>{

    //------------------------ custom code begin ------------------------//
        
    //======================== custom code end ========================//

}