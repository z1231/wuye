package com.ssm.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.ssm.entity.PcPay;
import com.ssm.vo.res.SysResult;

/**
 * <p>
 * 业务支付
 * </p>
 *
 * @author hello
 * @since 2020-03-07
 */
@RestController
@RequestMapping("/money")
public class SmMoneyController {

		//支付
		@PostMapping("/pay")
		public String pay(@RequestBody PcPay pc) throws AlipayApiException, UnsupportedEncodingException {
			
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			
			//设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
			
			//商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = new String(pc.getOut_trade_no().getBytes("ISO-8859-1"),"UTF-8");
			//付款金额，必填
			String total_amount = new String(pc.getTotal_amount().getBytes("ISO-8859-1"),"UTF-8");
			//订单名称，必填
			String subject= new String(pc.getSubject().getBytes("ISO-8859-1"),"UTF-8");
			//商品描述，可空
//			String body = new String(pc.getBody().getBytes("ISO-8859-1"),"UTF-8");
			
			alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
					+ "\"total_amount\":\""+ total_amount +"\"," 
					+ "\"subject\":\""+ subject +"\"," 
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			
//			AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
//			//商户订单号，商户网站订单系统中唯一订单号，必填
//			model.setOutTradeNo(pc.getOut_trade_no());
//			//付款金额，必填
//			model.setTotalAmount(pc.getTotal_amount());
//			//订单名称，必填
//			model.setSubject(pc.getSubject());
//			//商品描述，可空
//			model.setBody(pc.getBody());
//			
//			alipayRequest.setBizModel(model);
			
			//请求
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			return result;
		}
	

}
