package wzy.crowd.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 网中鱼
 * @date 2021/9/10-23:02
 */
@Component
@ConfigurationProperties(prefix = "alipay")
public class PayProperties {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String appId = "2021000118615515";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchantPrivateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCMDLWo/y2ujeezITgk9UuRHCJBStNeyittWXdeQjeiGm7J9gdMEA+ss7d4BFoD77dAgvK5YGnH36JHlO3mJ5WgSOCfyQSU4r9nCQ8Z98ODMWvTWrzLc33DgDCwHg9hu1ObBBcwRp5I4RYeNld0kcBtzXSVgO93kATpY+v+EG4TNrl6NcfAnPpiVz9gYfldWCj6mClju+5f7mwAA84dPBZG4BIo1C2cN/jS5IZL/uUnRRx1a+PlCJHm4+rlh+ea3LEcd0sCaFt6qO8B+f+/bQHmKAlzEElZ9k/W3j+UxNn6jTzM28BG9gOkBwnN06lyr64JgOOugKCvfQwrNBRTL959AgMBAAECggEAAi8YjiAcmEJCQLrd40Wn/gVQS3BdR3cjojro76FcuLxsyd16U6dUdz7+N3Lp7HHKi8j+/AES5TkJkNAzvYkUfkFkr1k1JxU6IuMzRxZkCCG3Kr6Zfxq39VqEZ9qwJrgDH8G6ktgnE7/e1m3Gg1zagVvY25P9KAIo8XLLdVTQQqBr8DMY6PddZUnYyaEy+LYyAQ19cJRhP4M+5sq97evK8ByIxvqSDeYYAO/6Nc8I2633gfUrazh7Dmzpio4tADv45VzA3FZdKY6PfptL0SkXvS5Lqi/inZVZuFqd6t1UaaNdVALtd/di6wk8dtE4VFahGT2aGuL2mDRt5n9B0N8KyQKBgQC+IaJzNG/2Qqu4/TLg4HTXazJtckn8o/YmiXNuCi6YmtXZhoItwuKSNPeEgfMmIzo8dNgVN+77y7IW5pWDCCi+3BvyNrcyl+ds2pO8Mrxf0w5gW51n8+tYTYDTKuNwGzSP47j8n4AiaPx1mubCMY0khJbLlStTTVrXIZ4PAw3TewKBgQC8kW16myLn58ALx2ILfBg+NdlWOiHUtFTZPgrvMC3W3sVdU9R++tywgN+xr67l6cjzDu+kfwj8FDBVYbXit4gT+8+Em8rVvkD+mu1sA7HpJSB2XzOOXoljUixCdhezRhpP4o8bGfhp5FVZkjC/yalxAPtKk03q5iKuiwysm5DYZwKBgC46A0M7VpVrNFEvznT6Xa4adBJEHVT9kFViSdxRQW79Bk1y9KenoGqn0h43ciStn/Ue+4J9LO+vYMdMnMFU9IMjBPBo+TWl1TCUHCM/uqkZqX8oBydFGM1JWq782HtjQe5SEaHqLb8wA2GcncpNPeHJEtjAld4VCqcPl1xTA8ONAoGAWCpe176FWYyf3GIC03/YDe47ifdwLfWK3nAFyMPsCtTpzrMNXbmJSfVXocqUqebfD9Xh8jV7S/Fcj+85jaFmPIG2CziUY8+qr+BcNe6cVngHb5Efl+dXsVqQWxrABhA26Tby1icUYN5jkZeqt2FgmfdthvbMrZGgkZ9t855g4O0CgYEAmDCnkAz6vxzwShNEF1l4bMiICYoIgU2zXsU7qgAzG3S0qPyX54TSHC5onOslxYITGayZ/sAyTwEq7lorhYcWbRVbKlzV7L9+JonjN7WpOZsgoWifQMcfckcM+q7wq06jJFkB5A1CrUo0IDHi4q5zamlJ3ZC8QKt8xgWJUlED2jo=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhT39VUKjYWThOEmMwCm04mEuLIAQFyXtSD4Qi9rMQT8l/6hOUPIuwVps8rWbSEBQjbtHhpib5QXg8zFm30o8yOMbldYaGR5/ojeI3jVcKdZGjCiPkg3ig2B796eZHkv0QhQMy4+DQEPWHI+VcrNDLOmP3Eu7/nl6D53h2V0jVD0QJoNQgDDedX3VjfXBKE1ZC3znR/GfN0w1TjXTFCCNA4UGOlXN/m//yCYVIUtdlkiR9H7mCN+Ta8yOTaJiIOPGLd8mXIKgqzD+r3w3Nayd1V3esm4kbSO3bz08BznqOYIGmMwXv5mgNJyLmE+WCN19tuhLTlH+aIeXVNTV5j2grQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notifyUrl = "http://fcu43y.natappfree.cc/pay/notify";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String returnUrl = "http://www.wzy.com/pay/return";
    // 签名方式
    public static String signType = "RSA2";


    // 字符编码格式
    public static String charset = "UTF-8";

    // 支付宝网关（沙箱环境）
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志
    public static String logPath = "C:\\";

    public static String getAppId() {
        return appId;
    }

    public static void setAppId(String appId) {
        PayProperties.appId = appId;
    }

    public static String getMerchantPrivateKey() {
        return merchantPrivateKey;
    }

    public static void setMerchantPrivateKey(String merchantPrivateKey) {
        PayProperties.merchantPrivateKey = merchantPrivateKey;
    }

    public static String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public static void setAlipayPublicKey(String alipayPublicKey) {
        PayProperties.alipayPublicKey = alipayPublicKey;
    }

    public static String getNotifyUrl() {
        return notifyUrl;
    }

    public static void setNotifyUrl(String notifyUrl) {
        PayProperties.notifyUrl = notifyUrl;
    }

    public static String getReturnUrl() {
        return returnUrl;
    }

    public static void setReturnUrl(String returnUrl) {
        PayProperties.returnUrl = returnUrl;
    }

    public static String getSignType() {
        return signType;
    }

    public static void setSignType(String signType) {
        PayProperties.signType = signType;
    }

    public static String getCharset() {
        return charset;
    }

    public static void setCharset(String charset) {
        PayProperties.charset = charset;
    }

    public static String getGatewayUrl() {
        return gatewayUrl;
    }

    public static void setGatewayUrl(String gatewayUrl) {
        PayProperties.gatewayUrl = gatewayUrl;
    }

    public static String getLogPath() {
        return logPath;
    }

    public static void setLogPath(String logPath) {
        PayProperties.logPath = logPath;
    }
}

