package com.bytesmart.apiwebadmin.factory;

import com.bytesmart.apiwebadmin.RemoteLogService;
import com.bytesmart.apiwebadmin.domain.BytesmartLogininfor;
import com.bytesmart.apiwebadmin.domain.BytesmartOperLog;
import com.bytesmart.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * 日志服务降级处理
 * 
 * @author hd
 */
@Component
public class RemoteLogFallbackFactory implements FallbackFactory<RemoteLogService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteLogFallbackFactory.class);

    @Override
    public RemoteLogService create(Throwable throwable)
    {
        log.error("日志服务调用失败:{}", throwable.getMessage());
        return new RemoteLogService()
        {
            @Override
            public R<Boolean> saveLog(BytesmartOperLog bytesmartOperLog, String source)
            {
                return R.fail("保存操作日志失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> saveLogininfor(BytesmartLogininfor bytesmartLogininfor, String source)
            {
                return R.fail("保存登录日志失败:" + throwable.getMessage());
            }
        };

    }
}
