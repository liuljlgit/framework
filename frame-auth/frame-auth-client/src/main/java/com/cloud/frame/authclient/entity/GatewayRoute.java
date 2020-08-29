package com.cloud.frame.authclient.entity;

import com.cloud.ftl.ftlbasic.annotation.PrimaryKey;
import com.cloud.ftl.ftlbasic.constant.PatternConst;
import com.cloud.ftl.ftlbasic.enums.Opt;
import com.cloud.ftl.ftlbasic.webEntity.BaseQuery;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

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

	@ApiModelProperty("资源服务器配置的资源id")
    private String reourceId;

	@ApiModelProperty("eureka中注册的id")
    private String instanceId;

	@ApiModelProperty("路由规则，多个的时候用逗号划分")
    private String predicates;

	@ApiModelProperty("备注")
    private String remark;

	@ApiModelProperty("创建时间")
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
    private Date createTime;

	@ApiModelProperty("修改时间")
    @JsonFormat(pattern = PatternConst.NORM_DATETIME,timezone = PatternConst.TIMEZONE)
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
    public static final transient String REOURCE_ID = "reource_id";

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


    public void andGrId(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(GR_ID,opt);
        } else if(values.length == 1){
            addConditGroup(GR_ID,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(GR_ID,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ GR_ID + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andRouteId(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(ROUTE_ID,opt);
        } else if(values.length == 1){
            addConditGroup(ROUTE_ID,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(ROUTE_ID,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ ROUTE_ID + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andReourceId(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(REOURCE_ID,opt);
        } else if(values.length == 1){
            addConditGroup(REOURCE_ID,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(REOURCE_ID,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ REOURCE_ID + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andInstanceId(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(INSTANCE_ID,opt);
        } else if(values.length == 1){
            addConditGroup(INSTANCE_ID,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(INSTANCE_ID,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ INSTANCE_ID + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andPredicates(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(PREDICATES,opt);
        } else if(values.length == 1){
            addConditGroup(PREDICATES,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(PREDICATES,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ PREDICATES + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andRemark(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(REMARK,opt);
        } else if(values.length == 1){
            addConditGroup(REMARK,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(REMARK,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ REMARK + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andCreateTime(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(CREATE_TIME,opt);
        } else if(values.length == 1){
            addConditGroup(CREATE_TIME,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(CREATE_TIME,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ CREATE_TIME + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andModifyTime(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(MODIFY_TIME,opt);
        } else if(values.length == 1){
            addConditGroup(MODIFY_TIME,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(MODIFY_TIME,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ MODIFY_TIME + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andRegexpUrl(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(REGEXP_URL,opt);
        } else if(values.length == 1){
            addConditGroup(REGEXP_URL,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(REGEXP_URL,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ REGEXP_URL + "’ 的SQL入参个数不正确 ");
        }
    }

    public void andStatus(Opt opt,Object... values) {
        if(values.length == 0){
            addConditGroup(STATUS,opt);
        } else if(values.length == 1){
            addConditGroup(STATUS,opt,values[0]);
        } else if(values.length == 2){
            addConditGroup(STATUS,opt,values[0],values[1]);
        } else {
            throw new RuntimeException("‘"+ STATUS + "’ 的SQL入参个数不正确 ");
        }
    }

}