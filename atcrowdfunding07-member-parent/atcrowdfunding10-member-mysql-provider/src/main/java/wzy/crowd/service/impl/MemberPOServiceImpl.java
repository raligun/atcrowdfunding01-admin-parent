package wzy.crowd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import wzy.crowd.entity.po.MemberPO;
import wzy.crowd.entity.po.MemberPOExample;
import wzy.crowd.mapper.MemberPOMapper;
import wzy.crowd.service.MemberPOService;

import java.util.List;

/**
 * @author 网中鱼
 * @date 2021/9/5-13:04
 */
@Transactional(readOnly = true)
@Service
public class MemberPOServiceImpl implements MemberPOService {
    @Autowired
    private MemberPOMapper memberPOMapper;

    @Override
    public MemberPO getMemberPOByLoginAcct(String loginAcct) {

        MemberPOExample example = new MemberPOExample();
        MemberPOExample.Criteria criteria = example.createCriteria();
        criteria.andLoginacctEqualTo(loginAcct);

        List<MemberPO> memberPOS = memberPOMapper.selectByExample(example);
        return memberPOS.get(0);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class)
    @Override
    public void saveMember(MemberPO memberPO) {
        memberPOMapper.insertSelective(memberPO);
    }
}
