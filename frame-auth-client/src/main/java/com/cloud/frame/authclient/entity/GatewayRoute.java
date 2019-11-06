package com.cloud.frame.authclient.entity;

import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.cloud.ftl.ftlbasic.enums.Opt;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.cloud.ftl.ftlbasic.annotation.PrimaryKey;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel("GatewayRoute")
public class GatewayRoute extends BaseQuery {

	@ApiModelProperty("")
    @PrimaryKey
    private Long grId;

	@ApiModelProperty("路由ID")
    private String routeId;

	@ApiModelProperty("eureka中注册的id")
    private String instanceId;

	@ApiModelProperty("路由规则，多个的时候用逗号划分")
    private String predicates;

	@ApiModelProperty("备注")
    private String remark;

	@ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

	@ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date modifyTime;

	@ApiModelProperty("网关请求过来的url前缀")
    private String regexpUrl;

	@ApiModelProperty("是否有效")
    private BigDecimal status;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String GR_ID = "gr_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String ROUTE_ID = "route_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String INSTANCE_ID = "instance_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String PREDICATES = "predicates";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String REMARK = "remark";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String CREATE_TIME = "create_time";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String MODIFY_TIME = "modify_time";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String REGEXP_URL = "regexp_url";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String STATUS = "status";


    public void andGrId(Opt opt) {
        addConditGroup(GR_ID,opt);
    }

    public void andGrId(Opt opt,Long grId) {
        addConditGroup(GR_ID,opt,grId);
    }

    public void andGrId(Opt opt,List<Long> list) {
        addConditGroup(GR_ID,opt,list);
    }

    public void andGrId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(GR_ID,opt,firstParam,secondParam);
    }

    public void andRouteId(Opt opt) {
        addConditGroup(ROUTE_ID,opt);
    }

    public void andRouteId(Opt opt,String routeId) {
        addConditGroup(ROUTE_ID,opt,routeId);
    }

    public void andRouteId(Opt opt,List<String> list) {
        addConditGroup(ROUTE_ID,opt,list);
    }

    public void andRouteId(Opt opt,String firstParam,String secondParam) {
        addConditGroup(ROUTE_ID,opt,firstParam,secondParam);
    }

    public void andInstanceId(Opt opt) {
        addConditGroup(INSTANCE_ID,opt);
    }

    public void andInstanceId(Opt opt,String instanceId) {
        addConditGroup(INSTANCE_ID,opt,instanceId);
    }

    public void andInstanceId(Opt opt,List<String> list) {
        addConditGroup(INSTANCE_ID,opt,list);
    }

    public void andInstanceId(Opt opt,String firstParam,String secondParam) {
        addConditGroup(INSTANCE_ID,opt,firstParam,secondParam);
    }

    public void andPredicates(Opt opt) {
        addConditGroup(PREDICATES,opt);
    }

    public void andPredicates(Opt opt,String predicates) {
        addConditGroup(PREDICATES,opt,predicates);
    }

    public void andPredicates(Opt opt,List<String> list) {
        addConditGroup(PREDICATES,opt,list);
    }

    public void andPredicates(Opt opt,String firstParam,String secondParam) {
        addConditGroup(PREDICATES,opt,firstParam,secondParam);
    }

    public void andRemark(Opt opt) {
        addConditGroup(REMARK,opt);
    }

    public void andRemark(Opt opt,String remark) {
        addConditGroup(REMARK,opt,remark);
    }

    public void andRemark(Opt opt,List<String> list) {
        addConditGroup(REMARK,opt,list);
    }

    public void andRemark(Opt opt,String firstParam,String secondParam) {
        addConditGroup(REMARK,opt,firstParam,secondParam);
    }

    public void andCreateTime(Opt opt) {
        addConditGroup(CREATE_TIME,opt);
    }

    public void andCreateTime(Opt opt,Date createTime) {
        addConditGroup(CREATE_TIME,opt,createTime);
    }

    public void andCreateTime(Opt opt,List<Date> list) {
        addConditGroup(CREATE_TIME,opt,list);
    }

    public void andCreateTime(Opt opt,Date firstParam,Date secondParam) {
        addConditGroup(CREATE_TIME,opt,firstParam,secondParam);
    }

    public void andModifyTime(Opt opt) {
        addConditGroup(MODIFY_TIME,opt);
    }

    public void andModifyTime(Opt opt,Date modifyTime) {
        addConditGroup(MODIFY_TIME,opt,modifyTime);
    }

    public void andModifyTime(Opt opt,List<Date> list) {
        addConditGroup(MODIFY_TIME,opt,list);
    }

    public void andModifyTime(Opt opt,Date firstParam,Date secondParam) {
        addConditGroup(MODIFY_TIME,opt,firstParam,secondParam);
    }

    public void andRegexpUrl(Opt opt) {
        addConditGroup(REGEXP_URL,opt);
    }

    public void andRegexpUrl(Opt opt,String regexpUrl) {
        addConditGroup(REGEXP_URL,opt,regexpUrl);
    }

    public void andRegexpUrl(Opt opt,List<String> list) {
        addConditGroup(REGEXP_URL,opt,list);
    }

    public void andRegexpUrl(Opt opt,String firstParam,String secondParam) {
        addConditGroup(REGEXP_URL,opt,firstParam,secondParam);
    }

    public void andStatus(Opt opt) {
        addConditGroup(STATUS,opt);
    }

    public void andStatus(Opt opt,BigDecimal status) {
        addConditGroup(STATUS,opt,status);
    }

    public void andStatus(Opt opt,List<BigDecimal> list) {
        addConditGroup(STATUS,opt,list);
    }

    public void andStatus(Opt opt,BigDecimal firstParam,BigDecimal secondParam) {
        addConditGroup(STATUS,opt,firstParam,secondParam);
    }

}