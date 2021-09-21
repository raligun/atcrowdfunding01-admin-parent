package wzy.crowd.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wzy.crowd.entity.po.MemberPO;
import wzy.crowd.entity.vo.LoginMemberVO;
import wzy.crowd.entity.vo.MemberVO;
import wzy.crowd.service.MysqlRemoteService;
import wzy.crowd.service.RedisRemoteService;
import wzy.crowd.utils.CodeUtil;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.ResultSet;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * @author 网中鱼
 * @date 2021/9/5-22:11
 */
@Controller
public class MemberHandler {
    @Autowired
    private RedisRemoteService redisRemoteService;

    @Autowired
    private MysqlRemoteService mysqlRemoteService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    private Logger logger = LoggerFactory.getLogger(MemberHandler.class);

    @PostMapping("get/code")
    @ResponseBody
    public ResultSet getCode(@RequestParam("phoneNum")String phoneNum){
        String value = CodeUtil.getCode();

        String codeKey = Constant.CODE_REDIS_PREFIX + phoneNum;

        ResultSet resultSet = redisRemoteService.setRedisKeyValueWithTimeout(codeKey, value, 60 * 5, TimeUnit.SECONDS);
        if (resultSet.getCode() == 200){
            return ResultSet.success().addData(value);
        }else {
            return resultSet;
        }
    }


    @PostMapping("get/emailCode")
    @ResponseBody
    public ResultSet getEmailCode(@RequestParam("email")String email){
        String value = CodeUtil.getCode();
        String codeKey = Constant.EMAIL_CODE_REDIS_PREFIX + email;

        try {
            HtmlEmail htmlEmail = new HtmlEmail();
            // 126邮箱为smtp.126.com,163邮箱为163.smtp.com，QQ为smtp.qq.com

            htmlEmail.setHostName(Constant.EMAIL_126_HOSTNAME);

            htmlEmail.setCharset("UTF-8");

            // 收件地址
            htmlEmail.addTo(email);

            // 此处填 发送人（自己） 邮箱地址和用户名,用户名可以任意填写
            htmlEmail.setFrom(Constant.ATTR_MY_EMAIL,"尚筹网");

            // 此处填写邮箱地址和客户端授权码
            htmlEmail.setAuthentication(Constant.ATTR_MY_EMAIL,Constant.ATTR_MY_EMAIL_PASSWORD);

            htmlEmail.setSubject(Constant.MESSAGE_MY_EMAIL_TITLE);
            //此处填写邮件内容
            htmlEmail.setMsg(Constant.MESSAGE_EMAIL_CONTENT+value+Constant.MESSAGE_EMAIL_TAIL);

            htmlEmail.send();

            ResultSet resultSet = redisRemoteService.setRedisKeyValueWithTimeout(codeKey, value, 60 * 5, TimeUnit.SECONDS);
            if (resultSet.getCode() != 200){
                return resultSet;
            }
            return ResultSet.success();
        } catch (EmailException e) {
            return ResultSet.error(e.getMessage());
        }

    }

    @PostMapping("do/member/emailRegister")
    @ResponseBody
    public ResultSet memberEmailRegister(MemberVO memberVO){

        if (memberVO == null ||
                memberVO.getLoginacct() == null ||
                memberVO.getUserpswd() == null ||
                memberVO.getUsername() == null ||
                memberVO.getEmail() == null){
            return ResultSet.error(Constant.MESSAGE_STRING_INVALIDATE);
        }

        // 拼出key，并向 redis 获取 value
        String codeKey = Constant.EMAIL_CODE_REDIS_PREFIX + memberVO.getEmail();
        ResultSet redisData = redisRemoteService.getRedisByKey(codeKey);
//        判断获取值是否成功
        if (redisData == null && redisData.getCode() != 200){
            return redisData;
        }
        // 获取提交的验证码和数据库的验证码，
        // 比对
        String redisCode = (String) redisData.getData();
        String formCode = memberVO.getCode();
        if (redisCode == null || !redisCode.equals(formCode)){
            return ResultSet.error(Constant.MESSAGE_EMAIL_CODE_ERROR);
        }
        String rawPass = memberVO.getUserpswd();
        // 对原密码加密
        String encodePass = bCryptPasswordEncoder.encode(rawPass);
        memberVO.setUserpswd(encodePass);
        // 封装成 MemberPO
        MemberPO registerMember = new MemberPO();
        BeanUtils.copyProperties(memberVO,registerMember);
        // 放入数据库
        ResultSet registerResult = mysqlRemoteService.registerMember(registerMember);
        if (registerResult.getCode() == 200){
            // 删除数据库的验证码
            ResultSet deleteKey = redisRemoteService.deleteRedisByKey(codeKey);
            logger.debug("删除验证码结果："+ deleteKey.toString());

        }

        return registerResult;
    }


    @PostMapping("do/member/register")
    @ResponseBody
    public ResultSet memberRegister(MemberVO memberVO){

        if (memberVO == null ||
                memberVO.getLoginacct() == null ||
                memberVO.getUserpswd() == null ||
                memberVO.getUsername() == null ||
                memberVO.getEmail() == null){
            return ResultSet.error(Constant.MESSAGE_STRING_INVALIDATE);
        }

        // 拼出key，并向redis获取value
        String codeKey = Constant.CODE_REDIS_PREFIX + memberVO.getPhoneNum();
        ResultSet redisData = redisRemoteService.getRedisByKey(codeKey);
//        判断获取值是否成功
        if (redisData == null && redisData.getCode() != 200){
            return redisData;
        }
        // 获取提交的验证码和数据库的验证码，
        // 比对
        String redisCode = (String) redisData.getData();
        String formCode = memberVO.getCode();
        if (redisCode == null || !redisCode.equals(formCode)){
            return ResultSet.error(Constant.MESSAGE_CODE_ERROR);
        }
        String rawPass = memberVO.getUserpswd();
        // 对原密码加密
        String encodePass = bCryptPasswordEncoder.encode(rawPass);
        memberVO.setUserpswd(encodePass);
        // 封装成 MemberPO
        MemberPO registerMember = new MemberPO();
        BeanUtils.copyProperties(memberVO,registerMember);
        // 放入数据库
        ResultSet registerResult = mysqlRemoteService.registerMember(registerMember);
        if (registerResult.getCode() == 200){
            // 删除数据库的验证码
            ResultSet deleteKey = redisRemoteService.deleteRedisByKey(codeKey);
            logger.debug("删除验证码结果："+ deleteKey.toString());

        }

        return registerResult;
    }


    @PostMapping("do/login")
    @ResponseBody
    public ResultSet doLogin(MemberVO memberVO, HttpSession session){
        if (memberVO == null ||
                memberVO.getLoginacct() == null ||
                memberVO.getUserpswd() == null){
            return ResultSet.error(Constant.MESSAGE_STRING_INVALIDATE);
        }
        String loginacct = memberVO.getLoginacct();
        ResultSet resultSet = mysqlRemoteService.searchMemberPOByLoginacct(loginacct);
        if (resultSet != null && resultSet.getCode() != 200){
            return ResultSet.error(Constant.MESSAGE_LOGIN_FAILED);
        }

        String rawPassword = memberVO.getUserpswd();

        ObjectMapper objectMapper = new ObjectMapper();
        MemberPO mysqlMember = objectMapper.convertValue(resultSet.getData(), MemberPO.class);

        String mysqlPassword = mysqlMember.getUserpswd();

        boolean matches = bCryptPasswordEncoder.matches(rawPassword, mysqlPassword);

        if (!matches){
            return ResultSet.error(Constant.MESSAGE_LOGIN_FAILED);
        }
        LoginMemberVO loginMemberVO = new LoginMemberVO(mysqlMember.getId(),
                mysqlMember.getUsername(),
                mysqlMember.getEmail());

        session.setAttribute(Constant.ATTR_NAME_LOGIN_MEMBER,loginMemberVO);
        session.removeAttribute(Constant.MESSAGE);
        return ResultSet.success();
    }

    @RequestMapping("do/loginOut")
    public String doLoqinOut(HttpSession session){
        session.invalidate();
        return Constant.REDIRECT_TITLE + "to/member_login";
    }
}
