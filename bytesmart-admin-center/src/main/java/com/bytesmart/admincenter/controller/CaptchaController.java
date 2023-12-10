package com.bytesmart.admincenter.controller;

import com.bytesmart.common.core.constant.CacheConstants;
import com.bytesmart.common.core.constant.Constants;
import com.bytesmart.common.core.utils.uuid.IdUtils;
import com.bytesmart.common.core.web.domain.AjaxResult;
import com.google.code.kaptcha.Producer;
import java.util.concurrent.TimeUnit;
import com.bytesmart.common.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import com.bytesmart.common.core.utils.sign.Base64;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class CaptchaController {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisService redisCache;

//    @Autowired
//    private ISysConfigService configService;

    @GetMapping("code")
    public AjaxResult getCode(HttpServletResponse response) throws IOException
    {
        AjaxResult ajax = AjaxResult.success();
        boolean captchaEnabled = true;
        ajax.put("captchaEnabled", captchaEnabled);
        if (!captchaEnabled)
        {
            return ajax;
        }

        // 随机生成一个uuid，并加上指定指定前缀表示redis的key
        String uuid = IdUtils.simpleUUID();
        String rediskey = CacheConstants.WEB_CAPTCHA_CODE_KEY + uuid;

        String capStr = null, redisvalue = null;
        BufferedImage image = null;

        // 生成验证码
//        String captchaType = RuoYiConfig.getCaptchaType();
//        if ("math".equals(captchaType))
//        {
        // 调用谷歌公司创建验证码的类和方法
        String capText = captchaProducerMath.createText();

        // 使用1+2@3举例，就是生成一个这样的算术格式使用1+2@3举例
        capStr = capText.substring(0, capText.lastIndexOf("@"));

        // 使用1+2@3举例，3作为值存储在redis里面，key就是变量rediskey
        redisvalue = capText.substring(capText.lastIndexOf("@") + 1);

        // 使用1+2@3举例，1+2当前图片返回给前端
        image = captchaProducerMath.createImage(capStr);

        //
        redisCache.setCacheObject(rediskey, redisvalue, Constants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try
        {
            ImageIO.write(image, "jpg", os);
        }
        catch (IOException e)
        {
            return AjaxResult.error(e.getMessage());
        }

        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;


//        }
//        else if ("char".equals(captchaType))
//        {
//            capStr = code = captchaProducer.createText();
//            image = captchaProducer.createImage(capStr);
//        }

    }

}
