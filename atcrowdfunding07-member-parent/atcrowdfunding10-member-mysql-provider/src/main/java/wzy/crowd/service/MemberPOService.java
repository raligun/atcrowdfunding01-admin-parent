package wzy.crowd.service;

import org.springframework.stereotype.Service;
import wzy.crowd.entity.po.MemberPO;

/**
 * @author 网中鱼
 * @date 2021/9/5-13:02
 */
@Service
public interface MemberPOService {
    MemberPO getMemberPOByLoginAcct(String loginAcct);

    void saveMember(MemberPO memberPO);
}
