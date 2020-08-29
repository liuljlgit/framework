package com.cloud.frame.resourceserver.dao;

import org.springframework.stereotype.Repository;
import java.util.List;
import com.cloud.ftl.ftlbasic.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import com.cloud.frame.resourceclient.entity.Holiday;

/**
  * 接口类 IHolidayDao
  * @author lijun
  */
@Repository
public interface IHolidayDao extends IBaseMapper<Holiday>{

    //------------------------ custom code begin ------------------------//
    
    //======================== custom code end ========================//

}