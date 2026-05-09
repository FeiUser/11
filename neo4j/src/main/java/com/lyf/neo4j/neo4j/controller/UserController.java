package com.lyf.neo4j.neo4j.controller;

import com.lyf.neo4j.neo4j.entity.Know;
import com.lyf.neo4j.neo4j.entity.UserNode;
import com.lyf.neo4j.neo4j.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * @Description 添加节点
     **/
    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public UserNode addUserNode(@RequestBody UserNode userNode) {
        return userService.create(userNode);
    }

    /**
     * @Description 根据id删除
     **/
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public int delUserNodeById(@RequestParam(value = "id") long id) {
        userService.deleteById(id);
        System.out.println(id);
        return 1;
    }

    /**
     * @Description 根据id更新
     **/
    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public UserNode updateUserNodeByNode(@RequestBody UserNode userNode) {
        return userService.updateByNode(userNode);
    }

    /**
     * @Description 根据id查询单个节点
     **/
    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public UserNode getUserNodeById(@RequestParam(value = "id") long id) {
        Optional<UserNode> optionalUserNode = userService.findById(id);
        if (optionalUserNode.isPresent()) {
            return optionalUserNode.get();
        } else {
            return null;
        }
    }

    /**
     * @Description 查找所有节点
     **/
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<UserNode> getUserNodeList() {
        return userService.findAll();
    }

    /**
     * @Description 给已有的两个节点建立联系
     **/
    @RequestMapping(path = "/addKnows", method = RequestMethod.POST)
    public Know addKnowsById(@RequestParam(value = "from") long fromId, @RequestParam(value = "to") long toId) {
        Optional<UserNode> fromOpt = userService.findById(fromId);
        Optional<UserNode> toOpt = userService.findById(toId);
        if (fromOpt.isPresent()&&toOpt.isPresent()) {
            return userService.addIKnows(fromOpt.get(),toOpt.get());
        } else {
            return null;
        }
    }

    /**
     * @Author keke
     * @Description 删除两个节点之间的关系
     * @Date 2021/6/7
     * @Param [fromId, toId]
     * @Return java.lang.String
     **/
    @RequestMapping(path = "/delKnows", method = RequestMethod.POST)
    public String deleteKnowsByNodeId(@RequestParam(value = "from") long fromId, @RequestParam(value = "to") long toId) {
        Optional<UserNode> fromOpt =  userService.findById(fromId);
        Optional<UserNode> toOpt =  userService.findById(toId);
        if (fromOpt.isPresent()&&toOpt.isPresent()) {
            userService.deleteKnowByNodeId(fromId,toId);
            return "ok";
        } else {
            return "false";
        }
    }
}
