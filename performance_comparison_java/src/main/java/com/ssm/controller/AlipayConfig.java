package com.ssm.controller;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102300741560";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCd3XflB4CQlHLpKaC1CLD8BxPn9InjjOacs7Qd+Xc9FDynGab6GVrkFRROyu3qulW+i/Bz/3d7ElCN7GnGZKcJFEuWizqoHPFyDoCX+yr2ZLvRjaGIyAQn3fYQ7AdRi5h+lYo9FBUNDJdogSIC01l0Yf2p0SKgo4j2s6XbwNG4JOY55LvLyIEEq7tJIsImqk0nCLDANnPADZXjh6lQkkHYn8S1ys+bCocUniB7b2CKbYbd0kcZi1KIPVMx7OIkaQRmwRzGJx0ixzvz/rOBHa8AjZiZPUah+t04PE9LfTCrkCfpTnsX10QzDkDj8X5CHaaRPlrc+6FYPYL5LgNB+dVbAgMBAAECggEAFQRIEvi1uAnbZolBOPjIPzVeBBJ9TQsUJKaUAy2UH1CTZpe2sDMx842O35KKrCZUhgKxZ3AKYxskGFDyIBSEXGQUUB2HGJyJ1Gpz2kf5B0aP7q0RZVljnD8jpEsYClYwXiHgfpMu3kbf3Wzbr06d6S2mjLYoCwTaliWCnkgEY6RoPI6yag8VZ3GSDgswO+Gx7MevXxEWZh5/p9oxLDKaSc8VD7KiHpxngzt5HpkRpW9cL+4Hhq3nVnVKMArjGIQ4vkRvsAKR19D57sn76VGr+YRU1Y0E9mBA5mMEO4iVFl2ShhEiDp6bg1RuRHZtZVMmZUKgCrJeJvbFM12kbT8ZgQKBgQDbDOSItGItUtfGoeaAUGYGW9qHHlnksCJlCYRfLopB0kkM2rb+xi+sGHRY9/ynJOwIRcVdavLqigv7eghdcyzV5cE0Ktrb2YzizfPfR5vchOt6WHc45+7oL3f82UMExn3b0IrrM5tKbNkj1pgUi40AA3zcfTDBgXMoDsU0xCngwQKBgQC4fnQJ3986PZRq96bfxwLwmvSIUqgkZtRmRW9DfY1mNRFrKjgOpU6hms9tnFLh0EcHbobPqkUEx841u1LHFEBmLcEIQKBwR2oBXgb+fv+vtVaGiMfbkkiJxjHVcAC0IGErRlcsQcD5dA3s/qiMuWUlaX1iZx2ez0gMCwqKRuZhGwKBgCIcyeI4JSnlwtCeGeVVn+3RlCCCq4EfpNuTNdgUyVcixlmIHgNjHb1xOX05cVEeQNuw8eFwbE/2G5OYF4FmFE9jLklPezHNmVEgvZ/B6RXYZUtyR27HP79M1SS9OOCfgK4gteGW8dIPTWl1W82nRdgAswsrO9KGtEZCtpdXDSaBAoGAesIxRcy/hMoqsk4crDOIYj5IzVhqZuZBF9fFo9urtUFEWNEbwar+ugr/UEi+4WArkudZLl52VCr6q8f72VlBTHvPQEV4O51ZDJXbA4XPbcO0Ugo7KX+ZYosM558B6NjOrvO710fdNl6hUXtU/WXyxkT91/cSv3Pe1ZyXI9DvQ9cCgYAycGCO4U4DE9LUTEknUAtBSgWEhARUkz/4G6xF3diCcAAza+0I5P62aAy4BRaYPaEBTOtUlwuH6ysb7R8AGn1xCQnBDuTptPL+RAMN6QDTwn1+KRfonRg3lk9wy9XCjneF+IPuySBzYXV8OJIN8jsuvFVQ3SjTSZAlyzVzpsTwQQ==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl8jTvCQ6DPDesHBy2ZSAFjc5cbAIKgnuu4V1ts1MWVAfl8hsmgnXwui3giTJHLcgsVglAmH71NjFnTO+dn4H8Cl7CgZeaqgg9cZTEgyoFqlCj2XAH0CfRYnO1IDEj4RPH7KY2SfwRyJWy7/B2eLFofI/Hj+1NxzNe+sfhqdLqQMaTZDx3rtTR2ILiJChM3WC04+cEwxlDxHEKqsOVcr/YhiNHFQHgZhfNrZPZ87OSpY9/3J1HMMMU/OFCq4VeKvdzUPZ51MrYFA3BhmnmWhFkjSEkpgh1zVlWTEu76QIMAqlHH33DD+0z38u4KXwHE9IKEk1hUYEEFFTg8/bgbIZ2QIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "https://www.baidu.com";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "https://www.baidu.com";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

