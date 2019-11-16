package com.cloud.frame.frameauth.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cloud.frame.authclient.entity.ComAuthorityRole;

/**
  * 接口类 IComAuthorityRoleDao
  * @author lijun
  */
@Repository
public interface IComAuthorityRoleDao extends IBaseMapper<ComAuthorityRole>{

    //------------------------ custom code begin ------------------------//
        
    //======================== custom code end ========================//

}