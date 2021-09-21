package wzy.crowd.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wzy.crowd.entity.po.MemberPO;
import wzy.crowd.mapper.MemberPOMapper;

/**
 * @author 网中鱼
 * @date 2021/9/5-11:14
 */
@RestController
public class TestHandler {

    @Autowired
    private MemberPOMapper memberPOMapper;

    @RequestMapping("/test1")
    public String test1(){


        MemberPO memberPO = new MemberPO("admin","admin",
                "admin","admin@qq.com",1,1,"fsjaof","fjaohf",1);
        memberPOMapper.insert(memberPO);

        return memberPO.toString();
    }
}
