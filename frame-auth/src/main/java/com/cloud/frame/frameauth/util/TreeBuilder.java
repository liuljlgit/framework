package com.cloud.frame.frameauth.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Liulj
 * @version v 0.1 2019/9/24 14:50
 * 使用方式：
 *  1.定义一个类M继承自Node节点类
 *  2.调用buildTree方法返回List<Node>
 *  3.将返回的List<Node>强转成自定义的类M
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class TreeBuilder {

    /**
     * 构建树形结构
     * @return
     */
    public static List<Node> buildTree(List<Node> allNodes,String rootPid) {
        List<Node> treeNodes = Lists.newArrayList();
        List<Node> rootNodes = getRootNodes(allNodes,rootPid);
        Map<String, List<Node>> pidMap = allNodes.stream()
                .filter(e->StringUtils.isNotEmpty(e.getPId()))
                .collect(Collectors.groupingBy(Node::getPId));
        for (Node rootNode : rootNodes) {
            buildChildNodes(rootNode,pidMap);
            treeNodes.add(rootNode);
        }
        return treeNodes;
    }

    /**
     * 获取集合中所有的根节点
     * @return
     * @param rootPid
     * @param allNodes
     */
    private static List<Node> getRootNodes(List<Node> allNodes,String rootPid) {
        List<Node> rootNodes = Lists.newArrayList();
        for (Node n : allNodes){
            if (rootPid.equals(n.getPId())) {
                rootNodes.add(n);
            }
        }
        return rootNodes.stream()
                .sorted(Comparator.comparing(Node::getWgt))
                .collect(Collectors.toList());
    }

    /**
     * 递归子节点
     * @param pNode
     * @param pidMap
     */
    private static void buildChildNodes(Node pNode, Map<String, List<Node>> pidMap) {
        List<Node> childNodes = pidMap.getOrDefault(pNode.getId(), Lists.newArrayList()).stream()
                .sorted(Comparator.comparing(Node::getWgt))
                .collect(Collectors.toList());
        if (CollectionUtils.isNotEmpty(childNodes)) {
            for(Node child : childNodes) {
                buildChildNodes(child, pidMap);
            }
            pNode.setChildrens(childNodes);
        }
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("树形节点")
    public static class Node {

        @ApiModelProperty(value = "主键",hidden = true)
        private String id;

        @ApiModelProperty(value = "父级主键",hidden = true)
        private String pId;

        @ApiModelProperty(value = "父级名称",hidden = true)
        private String pName;

        @ApiModelProperty(value = "权重",hidden = true)
        private Integer wgt = 1;

        @ApiModelProperty(value = "子节点")
        private List<Node> childrens;
    }

}
