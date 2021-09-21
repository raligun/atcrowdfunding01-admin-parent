package wzy.crowd.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import wzy.crowd.entity.po.MemberPO;
import wzy.crowd.service.MemberPOService;
import wzy.crowd.utils.Constant;
import wzy.crowd.utils.ResultSet;


/**
 * @author 网中鱼
 * @date 2021/9/5-13:06
 */
@RestController
public class MemberPOProviderHandler {
    @Autowired
    private MemberPOService memberPOService;

    private Logger logger = LoggerFactory.getLogger(MemberPOProviderHandler.class);

    @RequestMapping("comsumer/searchMemberPOByLoginAcct")
    public ResultSet searchMemberPOByLoginacct(
            @RequestParam("loginAcct")String loginAcct){
    try {
        MemberPO memberPO = memberPOService.getMemberPOByLoginAcct(loginAcct);
        return ResultSet.success().addData(memberPO);
    }catch (Exception e){
        logger.info(e.getMessage());
        return ResultSet.error(e.getMessage());
    }
    }

    @PostMapping("register/member")
    public ResultSet registerMember(@RequestBody MemberPO memberPO){
        try {
            memberPOService.saveMember(memberPO);
            return ResultSet.success();
        }catch (Exception e){
            if (e instanceof DuplicateKeyException){
                return ResultSet.error(Constant.MESSAGE_LOGINACCT_EXIST);
            }
            return ResultSet.error(e.getMessage());
        }


    }
}
