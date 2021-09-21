package wzy.crowd.handler;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import wzy.crowd.config.PayProperties;
import wzy.crowd.entity.vo.LoginMemberVO;
import wzy.crowd.entity.vo.OrderProjectVO;
import wzy.crowd.entity.vo.OrderVO;
import wzy.crowd.service.MysqlRemoteService;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 网中鱼
 * @date 2021/9/10-23:24
 */
@Controller
public class OrderPayHandler {
    @Autowired
    private PayProperties payProperties;
    @Autowired
    private MysqlRemoteService mysqlRemoteService;

    private Logger logger = LoggerFactory.getLogger(OrderPayHandler.class);

    @RequestMapping("do/generate/order")
    @ResponseBody
    public String doGenerateOrder(OrderVO orderVO,
                                  HttpSession session,
                                  HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        // 从 session 中拿到 orderProjecVO 对象；
        OrderProjectVO orderProjectVO = (OrderProjectVO) session.getAttribute(Constant.ATTR_ORDERPROJECT_KEY);
        // 设置给 orderVO
        orderVO.setOrderProjectVO(orderProjectVO);
        session.removeAttribute(Constant.ATTR_ORDERPROJECT_KEY);

        // 生成订单号
        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        LoginMemberVO loginMember = (LoginMemberVO) session.getAttribute(Constant.ATTR_NAME_LOGIN_MEMBER);
        Integer mid = loginMember.getMID();

        String orderNum = date + "memberId" + mid;
        if (orderNum.length() > 63) {
            orderNum = orderNum.substring(0, 63);
        }
        orderVO.setOrderNum(orderNum);

        // 计算订单金额
        double totalPrice = orderProjectVO.getReturnCount() * orderProjectVO.getSupportPrice() + orderProjectVO.getFreight();
        orderVO.setOrderAmount(totalPrice);

        session.setAttribute("orderVO",orderVO);

        return sendRequestToAliPay( orderNum,
                                    totalPrice,
                                    orderProjectVO.getProjectName(),
                                    orderProjectVO.getReturnContent());
    }

    /**
     * 调用支付宝的支付功能
     * @param outTradeNo 商户订单号，商户网站订单系统中唯一订单号，必填
     * @param totalAmount 付款金额，必填
     * @param subject 订单名称，必填
     * @param body 商品描述，可空
     * @return
     * @throws UnsupportedEncodingException
     * @throws AlipayApiException
     */
    private String sendRequestToAliPay(String outTradeNo,
                                       double totalAmount,
                                       String subject,
                                       String body) throws UnsupportedEncodingException, AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(payProperties.gatewayUrl,
                payProperties.appId,
                payProperties.merchantPrivateKey,
                "json",
                payProperties.charset,
                payProperties.alipayPublicKey,
                payProperties.signType);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(payProperties.returnUrl);
        alipayRequest.setNotifyUrl(payProperties.notifyUrl);

      /*  //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
        //商品描述，可空
        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
        若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        		+ "\"total_amount\":\""+ total_amount +"\","
        		+ "\"subject\":\""+ subject +"\","
        		+ "\"body\":\""+ body +"\","
        		+ "\"timeout_express\":\"10m\","
        		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节  */

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\","
                + "\"total_amount\":\""+ totalAmount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");


        //请求
        return alipayClient.pageExecute(alipayRequest).getBody();

        //输出
//        out.println(result);
    }



    @RequestMapping("/return")
    @ResponseBody
    public String returnUrl(HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params,
                payProperties.alipayPublicKey,
                payProperties.charset,
                payProperties.signType); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            // 将 orderVO 保存到数据库
            HttpSession session = request.getSession();
            OrderVO orderVO = (OrderVO) session.getAttribute("orderVO");
            orderVO.setPayOrderNum(trade_no);

            ResultSet resultSet = mysqlRemoteService.saveOrderVO(orderVO);

            if (resultSet.getCode() != 200){
                logger.debug("保存失败的订单信息"+orderVO.toString());
            }
            session.removeAttribute("orderVO");

            return "trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount;
        }else {
            return "验签失败";
        }
        //——请在这里编写您的程序（以上代码仅作参考）——
    }

    @RequestMapping("/notify")
    public void notifyUrlMethod(HttpServletRequest request) throws
            UnsupportedEncodingException, AlipayApiException {
//获取支付宝 POST 过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
//乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(
                params,
                payProperties.getAlipayPublicKey(),
                payProperties.getCharset(),
                payProperties.getSignType()); //调用 SDK 验证签名
        //——请在这里编写您的程序（以下代码仅作参考）——
        /* 实际验证过程建议商户务必添加以下校验：
        1、需要验证该通知数据中的 out_trade_no 是否为商户系统中创建的订单号，
        2、判断 total_amount 是否确实为该订单的实际金额（即商户订单创建时的金额），
        3、校验通知中的 seller_id（或者 seller_email) 是否为 out_trade_no 这笔单据的对应的
        操作方（有的时候，一个商户可能有多个 seller_id/seller_email）
        4、验证 app_id 是否为该商户本身。
        */
        if(signVerified) {//验证成功
//商户订单号
            String out_trade_no = new
                    String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
//支付宝交易号
            String trade_no = new
                    String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
//交易状态
            String trade_status = new
                    String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            logger.info("out_trade_no="+out_trade_no);
            logger.info("trade_no="+trade_no);
            logger.info("trade_status="+trade_status);
        }else {//验证失败
//调试用，写文本函数记录程序运行情况是否正常
//String sWord = AlipaySignature.getSignCheckContentV1(params);
//AlipayConfig.logResult(sWord);
            logger.info("验证失败");
        }
    }

}
