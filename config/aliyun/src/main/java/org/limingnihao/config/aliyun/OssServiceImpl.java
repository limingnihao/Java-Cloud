package org.limingnihao.config.aliyun;

import com.aliyun.oss.OSSClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 阿里云文件操作
 */
public class OssServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(OssServiceImpl.class);


    private OSSClient client = null;

    //    public OssServiceImpl(String accessKeyId, String accessKeySecret){
    //        this.accessKeyId = accessKeyId;
    //        this.accessKeySecret = accessKeySecret;
    //    }
    //
    //    public void setEndpoint(String endpoint) {
    //        this.endpoint = endpoint;
    //    }
    //
    //    public void setBucketName(String bucketName) {
    //        this.bucketName = bucketName;
    //    }
    //
    //    public void connect(String endpoint){
    //        this.endpoint = endpoint;
    //        this.connect();
    //    }
    //
    //    /**
    //     * 链接
    //     */
    //    public void connect(){
    //        this.client = new OSSClient("https://" + endpoint, accessKeyId, accessKeySecret);
    //        client.getClientConfiguration().setMaxConnections(100);
    //        client.getClientConfiguration().setConnectionTimeout(5000);
    //        client.getClientConfiguration().setMaxErrorRetry(3);
    //        client.getClientConfiguration().setSocketTimeout(2000);
    //    }
    //
    //    /**
    //     * 断开
    //     */
    //    public void shutdown(){
    //        client.shutdown();
    //    }
    //
    //    public void list(){
    //        ObjectListing objectListing = client.listObjects(new ListObjectsRequest(bucketName));
    //        for (OSSObjectSummary objectSummary : objectListing.getObjectSummaries()) {
    //            System.out.println(" - " + objectSummary.getKey() + "  " +  "(size = " + objectSummary.getSize() + ")");
    //        }
    //
    //    }
    //
    //    public String createFile(String key, String filePath)  throws Exception{
    //        return createFile(key, filePath, null);
    //    }

    //    public String createFile(String key, String filePath, String dns) throws Exception {
    //        logger.info("createFile - key=" + key + ", filePath=" + filePath + ", dns=" + dns);
    //        if(FileUtil.isExists(filePath)){
    //            PutObjectResult result = this.client.putObject(this.bucketName, key, new File(filePath));
    //            if(StringUtil.isNotBlank(dns)){
    //                return dns + "/" + key;
    //            }
    //            else{
    //                return "http://" + this.bucketName + "." + this.endpoint + "/" + key;
    //            }
    //        }
    //        throw new FileNotFoundException("文件不存在!");
    //    }
    //
    //    public String createFile(String key, InputStream is){
    //        return createFile(key, is, null);
    //    }
    //
    //    public String createFile(String key, InputStream is, String dns){
    //        PutObjectResult result = this.client.putObject(this.bucketName, key, is);
    //        if(StringUtil.isNotBlank(dns)){
    //            return dns + "/" + key;
    //        }
    //        else{
    //            return "http://" + this.bucketName + "." + this.endpoint + "/" + key;
    //        }
    //    }
    //
    //    private boolean createFolder(String folder){
    //        try{
    //            PutObjectResult result = this.client.putObject(this.bucketName, folder + "/", new ByteArrayInputStream(new byte[0]));
    //            logger.info("createFolder - folder=" + folder + ", " + result.toString());
    //            return true;
    //        }catch(Exception e){
    //            e.printStackTrace();
    //        }
    //        return false;
    //    }
    //
    //
    //
    //    /**
    //     * ----------------------------------- new --------------------------------------------
    //     */
    //
    //    public String createPublicFile(String bucket, String key, String filePath)  throws Exception{
    //        bucketName = bucket;
    //        endpoint = beijingEndpoint;
    //        return createPublicFile(bucketName, key, filePath, null);
    //    }
    //
    //    public String createPrivateFile(String bucket, String key, String filePath)  throws Exception{
    //        bucketName = bucket;
    //        endpoint = shanghaiEndpoint;
    //        return createPrivateFile(bucketName, key, filePath, null);
    //    }
    //
    //    public String createPublicFile(String bucket, String key, String filePath, String dns) throws Exception {
    //        bucketName = bucket;
    //        endpoint = beijingEndpoint;
    //        return createFile(key, filePath, dns);
    //    }
    //
    //    public String createPrivateFile(String bucket, String key, String filePath, String dns) throws Exception {
    //        bucketName = bucket;
    //        endpoint = shanghaiEndpoint;
    //        return createFile(key, filePath, dns);
    //    }
    //
    //    public String createPublicFile(String bucket, String key, InputStream is){
    //        bucketName = bucket;
    //        endpoint = beijingEndpoint;
    //        return createFile(key, is, null);
    //    }
    //
    //    public String createPrivateFile(String bucket, String key, InputStream is){
    //        bucketName = bucket;
    //        endpoint = shanghaiEndpoint;
    //        return createFile(key, is, null);
    //    }
    //
    //    public String createPublicFile(String bucket, String key, InputStream is, String dns){
    //        bucketName = bucket;
    //        endpoint = beijingEndpoint;
    //        return createFile(key, is, dns);
    //    }
    //
    //    public String createPrivateFile(String bucket, String key, InputStream is, String dns){
    //        bucketName = bucket;
    //        endpoint = shanghaiEndpoint;
    //        return createFile(key, is, dns);
    //    }
    //
    //    public boolean createPublicFolder(String bucket, String folder){
    //        bucketName = bucket;
    //        endpoint = beijingEndpoint;
    //        return createFolder(folder);
    //    }
    //
    //    public boolean createPrivateFolder(String bucket, String folder){
    //        bucketName = bucket;
    //        endpoint = shanghaiEndpoint;
    //        return createFolder(folder);
    //    }
    //
    //    /**
    //     * 获取文件相关
    //     */
    //    public InputStream getPrivateObjectInputStream(String bucket, String key) throws ParseException, IOException {
    //        bucketName = bucket;
    //        endpoint = shanghaiEndpoint;
    //
    //        //服务器端生成url签名字串
    //        Date expiration = DateUtil.parseRfc822Date("Wed, 18 Mar 2099 14:20:00 GMT");
    //        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key, HttpMethod.GET);
    //        //设置过期时间
    //        request.setExpiration(expiration);
    //        // 生成URL签名(HTTP GET请求)
    //        URL signedUrl = client.generatePresignedUrl(request);
    //        System.out.println("signed url for getObject: " + signedUrl);
    //
    //        return signedUrl.openStream();
    //    }
    //
    //    public URL getPrivateObjectURL(String bucket, String key) throws ParseException {
    //        bucketName = bucket;
    //
    //        //服务器端生成url签名字串
    //        Date expiration = DateUtil.parseRfc822Date("Wed, 18 Mar 2099 14:20:00 GMT");
    //        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key, HttpMethod.GET);
    //        //设置过期时间
    //        request.setExpiration(expiration);
    //        // 生成URL签名(HTTP GET请求)
    //        URL signedUrl = client.generatePresignedUrl(request);
    //        return signedUrl;
    //    }

}
