package wzy.crowd.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import wzy.crowd.entity.po.MemberPO;
import wzy.crowd.entity.vo.AddressVO;
import wzy.crowd.entity.vo.OrderVO;
import wzy.crowd.entity.vo.ProjectVO;
import wzy.crowd.utils.ResultSet;

/**
 * @author 网中鱼
 * @date 2021/9/5-12:56
 */
@FeignClient("wzy-crowd-mysql")
@Controller
public interface MysqlRemoteService {

    @RequestMapping("comsumer/searchMemberPOByLoginAcct")
    public ResultSet searchMemberPOByLoginacct(
            @RequestParam("loginAcct")String loginAcct);

    @PostMapping("register/member")
    public ResultSet registerMember(
            @RequestBody MemberPO memberPO);

    @PostMapping("save/mysql/projectVO")
    public ResultSet saveProjectVORemote(@RequestBody ProjectVO projectVO,
                                         @RequestParam("memberId") Integer memberId);

    @RequestMapping("get/portal/projectInfo")
    public ResultSet getPortalProjectInfo();

    @RequestMapping("remote/get/detail/projectInfo")
    public ResultSet getDetailProjectInfo(@RequestParam("projectId")Integer projectId);


    @RequestMapping("remote/get/OrderProjectVO")
    public ResultSet getOrderPoService(@RequestParam("returnId") Integer returnId);

    @RequestMapping("remote/get/allAddress")
    public ResultSet getAllAddress(@RequestParam("memberId")Integer memberId);

    @RequestMapping("remote/save/addressInfo")
    public ResultSet saveAddressInfo(@RequestBody AddressVO addressVO);

    @RequestMapping("remote/save/OrderInfo")
    public ResultSet saveOrderVO(@RequestBody OrderVO orderVO);
}
