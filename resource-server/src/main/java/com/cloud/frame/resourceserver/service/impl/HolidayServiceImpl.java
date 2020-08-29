package com.cloud.frame.resourceserver.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.cloud.frame.resourceserver.cache.impl.HolidayCacheImpl;
import com.cloud.frame.resourceserver.service.IHolidayService;

/**
 * IHolidayService service实现类
 * @author lijun
 */
@Slf4j
@Service("holidayService")
public class HolidayServiceImpl extends HolidayCacheImpl implements IHolidayService {

    @Override
    @Async("asyncServiceExecutor")
    public void testThreadMdc() {
      log.info("子线程testThreadMdc");
    }
}