package wzy.crowd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wzy.crowd.entity.vo.AddressVO;
import wzy.crowd.entity.vo.OrderProjectVO;
import wzy.crowd.entity.vo.OrderVO;
import wzy.crowd.service.OrderPoService;
import wzy.crowd.utils.ResultSet;

import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/9/10-13:08
 */
@RestController
public class OrderPoProviderHandler {
    @Autowired
    private OrderPoService orderPoService;

    @RequestMapping("remote/get/OrderProjectVO")
    public ResultSet getOrderPoService(@RequestParam("returnId") Integer returnId){
        try {
            OrderProjectVO orderProjectVO = orderPoService.getOrderProjectVOByreturnId(returnId);
            return ResultSet.success().addData(orderProjectVO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultSet.error(e.getMessage());
        }
    }

    @RequestMapping("remote/get/allAddress")
    public ResultSet getAllAddress(@RequestParam("memberId")Integer memberId){
        try {
            List<AddressVO> addressVOList = orderPoService.getAllAddressByMemberId(memberId);
            return ResultSet.success().addData(addressVOList);
        }catch (Exception e){
            e.printStackTrace();
            return ResultSet.error(e.getMessage());
        }
    }

    @RequestMapping("remote/save/addressInfo")
    public ResultSet saveAddressInfo(@RequestBody AddressVO addressVO){
        try {
            addressVO = orderPoService.saveAddressInfo(addressVO);
            return ResultSet.success().addData(addressVO);
        }catch (Exception e){
            e.printStackTrace();
            return ResultSet.error(e.getMessage());
        }
    }

    @RequestMapping("remote/save/OrderInfo")
    public ResultSet saveOrderVO(@RequestBody OrderVO orderVO){
        try {
            orderPoService.saveOrderPo(orderVO);
            return ResultSet.success();
        }catch (Exception e){
            e.printStackTrace();
            return ResultSet.error(e.getMessage());
        }
    }
}
