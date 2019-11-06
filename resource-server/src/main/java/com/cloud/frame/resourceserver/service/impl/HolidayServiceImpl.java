package com.cloud.frame.resourceserver.service.impl;

import org.springframework.stereotype.Service;
import com.cloud.frame.resourceserver.cache.impl.HolidayCacheImpl;
import com.cloud.frame.resourceserver.service.IHolidayService;

/**
 * IHolidayService service实现类
 * @author lijun
 */
@Service("holidayService")
public class HolidayServiceImpl extends HolidayCacheImpl implements IHolidayService {

}