package org.limingnihao.config.aliyun;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.limingnihao.base.rpc.bean.IResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

public class SmsServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Autowired
    private MyAliyunProperties properties;

    /**
     * 发送短信
     *
     * @param mobile
     * @param templateCode
     * @param params
     * @param regionId
     * @param endpoint
     */
    public IResultBean<String> send(String mobile, String templateCode, String params, String regionId, String endpoint) {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        IResultBean resultBean = new IResultBean<>();
        try {
            mobile = mobile.replace("-", "");
            IClientProfile profile = DefaultProfile.getProfile(regionId, this.properties.getAccessKeyId(), this.properties.getAccessKeySecret());
            DefaultProfile.addEndpoint(endpoint, regionId, this.properties.getProduct(), this.properties.getDomain());
            IAcsClient client = new DefaultAcsClient(profile);
            SendSmsRequest request = new SendSmsRequest();
            request.setMethod(MethodType.POST);
            request.setPhoneNumbers(mobile);
            request.setSignName(this.properties.getSignName());
            request.setTemplateCode(templateCode);
            if (!StringUtils.isEmpty(params)) {
                request.setTemplateParam(params);
            }
            SendSmsResponse sendSmsResponse = client.getAcsResponse(request);
            logger.debug("send - model=" + sendSmsResponse.getCode());
            resultBean.setSuccess(true);
            resultBean.setMessage("发送成功！");
        } catch (Exception e) {
            e.printStackTrace();
            resultBean.setSuccess(false);
            resultBean.setMessage("请不要发送太频繁！");
        }
        return resultBean;
    }

}
