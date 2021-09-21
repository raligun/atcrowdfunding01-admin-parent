package wzy.crowd.service;

import wzy.crowd.entity.po.AddressPO;
import wzy.crowd.entity.vo.AddressVO;
import wzy.crowd.entity.vo.OrderProjectVO;
import wzy.crowd.entity.vo.OrderVO;
import wzy.crowd.utils.ResultSet;

import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/9/10-13:05
 */
public interface OrderPoService {

    public OrderProjectVO getOrderProjectVOByreturnId(Integer returnId);

    List<AddressVO> getAllAddressByMemberId(Integer memberId);

    AddressVO saveAddressInfo(AddressVO addressVO);

    void saveOrderPo(OrderVO orderVO);
}
