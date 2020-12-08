package org.limingnihao.config.aliyun;

/**
 * Created by lishiming on 2018/2/9.
 * 阿里云的配置
 */
//@ConfigurationProperties
//@Resource
public class MyAliyunProperties {

    private String accessKeyId = "demo";
    private String accessKeySecret = "demo";
    // 短信使用
    private String signName = "demo";
    private String product = "demo";
    private String domain = "demo.aliyuncs.com";

    //oss使用
    private String endpoint = "oss-cn-beijing.aliyuncs.com";
    private String bucketName = "";

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
