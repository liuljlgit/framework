package com.cloud.frame.frameauth.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cloud.frame.authclient.entity.ComRole;

/**
  * 接口类 IComRoleDao
  * @author lijun
  */
@Repository
public interface IComRoleDao extends IBaseMapper<ComRole>{

    //------------------------ custom code begin ------------------------//
    
    //======================== custom code end ========================//

}