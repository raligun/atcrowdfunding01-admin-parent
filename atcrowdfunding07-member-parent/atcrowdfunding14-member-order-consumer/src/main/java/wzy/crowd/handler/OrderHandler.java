package wzy.crowd.handler;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import wzy.crowd.entity.vo.AddressVO;
import wzy.crowd.entity.vo.LoginMemberVO;
import wzy.crowd.entity.vo.OrderProjectVO;
import wzy.crowd.service.MysqlRemoteService;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.ResultSet;

import javax.servlet.http.HttpSession;

/**
 * @author 网中鱼
 * @date 2021/9/10-13:14
 */
@Controller
public class OrderHandler {
    @Autowired
    private MysqlRemoteService mysqlRemoteService;

    @RequestMapping("get/confirm/returnInfo")
    public String getOrderConfirmReturnInfo(@RequestParam("returnId")Integer returnId,
                                            HttpSession session){
        ResultSet resultSet = mysqlRemoteService.getOrderPoService(returnId);

        if (resultSet.getCode() != 200){
            session.setAttribute(Constant.MESSAGE,resultSet.getMessage());
        }else {
            session.setAttribute(Constant.ATTR_ORDERPROJECT_KEY,resultSet.getData());
        }

        return "confirm_return";
    }

    @RequestMapping("to/get/address/info")
    public String getAddressInfo(@RequestParam(value = "returnCount",defaultValue = "1") Integer returnCount,
                                 HttpSession session,
                                 Model model){
        LoginMemberVO loginMemberVO = (LoginMemberVO) session.getAttribute(Constant.ATTR_NAME_LOGIN_MEMBER);
        Integer mid = loginMemberVO.getMID();
        ObjectMapper objectMapper = new ObjectMapper();
        OrderProjectVO orderProjectVO = objectMapper.convertValue(session.getAttribute(Constant.ATTR_ORDERPROJECT_KEY),OrderProjectVO.class);

        // 修改订单数量
        orderProjectVO.setReturnCount(returnCount);
        session.setAttribute(Constant.ATTR_ORDERPROJECT_KEY,orderProjectVO);

        ResultSet resultSet = mysqlRemoteService.getAllAddress(mid);
        if (resultSet.getCode() != 200){
            model.addAttribute(Constant.MESSAGE,resultSet.getMessage());
        }else {
            model.addAttribute("addressVOList",resultSet.getData());
        }
        return "confirm_order";
    }

    @RequestMapping("to/save/address/info")
    @ResponseBody
    public ResultSet saveAddressInfo(AddressVO addressVO){
        ResultSet resultSet = mysqlRemoteService.saveAddressInfo(addressVO);

        if (resultSet.getCode() != 200){
            return resultSet;
        }

        return ResultSet.success().addData(resultSet.getData());
    }
}
