package wzy.crowd.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wzy.crowd.entity.po.AddressPO;
import wzy.crowd.entity.po.OrderPO;
import wzy.crowd.entity.po.OrderProjectPO;
import wzy.crowd.entity.vo.AddressVO;
import wzy.crowd.entity.vo.OrderProjectVO;
import wzy.crowd.entity.vo.OrderVO;
import wzy.crowd.mapper.AddressPOMapper;
import wzy.crowd.mapper.OrderPOMapper;
import wzy.crowd.mapper.OrderProjectPOMapper;
import wzy.crowd.service.OrderPoService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/9/10-13:05
 */
@Transactional(readOnly = true)
@Service
public class OrderPoServiceImpl implements OrderPoService {

    @Autowired
    private OrderPOMapper orderPOMapper;

    @Autowired
    private OrderProjectPOMapper orderProjectPOMapper;

    @Autowired
    private AddressPOMapper addressPOMapper;

    @Override
    public OrderProjectVO getOrderProjectVOByreturnId(Integer returnId){
        return orderProjectPOMapper.selectOrderProjectVO(returnId);
    }

    @Override
    public List<AddressVO> getAllAddressByMemberId(Integer memberId) {
       List<AddressPO> addressPOList = addressPOMapper.selectByMemberId(memberId);

        List<AddressVO> addressVOList = new ArrayList<>(addressPOList.size());
        for (AddressPO addressPO:
             addressPOList) {
            AddressVO addressVO = new AddressVO();
            BeanUtils.copyProperties(addressPO,addressVO);
            addressVOList.add(addressVO);
        }
        return addressVOList;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    @Override
    public AddressVO saveAddressInfo(AddressVO addressVO) {
        AddressPO addressPO = new AddressPO();
        BeanUtils.copyProperties(addressVO,addressPO);
        addressPOMapper.insert(addressPO);
        addressVO.setId(addressPO.getId());
        return addressVO;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    @Override
    public void saveOrderPo(OrderVO orderVO) {
        // 保存 OrderPO 到数据库
        // 并获取自增主键
        OrderPO orderPO = new OrderPO();
        BeanUtils.copyProperties(orderVO,orderPO);
        orderPOMapper.insert(orderPO);

        Integer orderId = orderPO.getId();

        // 复制
        OrderProjectVO orderProjectVO = orderVO.getOrderProjectVO();
        OrderProjectPO orderProjectPO = new OrderProjectPO();
        BeanUtils.copyProperties(orderProjectVO,orderProjectPO);
        // 把自增主键设给 orderProjectPO
        orderProjectPO.setOrderId(orderId);
        orderProjectPOMapper.insert(orderProjectPO);

    }
}
