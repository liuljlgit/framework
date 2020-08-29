package com.cloud.frame.resourceclient.entity;

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
@ApiModel("Holiday")
public class Holiday extends BaseQuery {

	@ApiModelProperty("")
    @PrimaryKey
    private Long hId;

	@ApiModelProperty("年份")
    private Integer year;

	@ApiModelProperty("天数")
    private Byte count;

	@ApiModelProperty("节假日类型:1-元旦；2-春节；3-清明节; 4-劳动节;5-端午节;6-中秋节;7-国庆节;8-其它;9-检修计划;10-停产计划;11-增加容量;12-减少容量;13-调班;14-周末加班")
    private Byte holidayType;

	@ApiModelProperty("描述")
    private String descript;

	@ApiModelProperty("状态 (0) 弃用 （1）正常")
    private Byte status;

	@ApiModelProperty("]创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

	@ApiModelProperty("状态时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date statusTime;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String H_ID = "h_id";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String YEAR = "year";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String COUNT = "count";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String HOLIDAY_TYPE = "holiday_type";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String DESCRIPT = "descript";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String STATUS = "status";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String CREATE_TIME = "create_time";

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    public static final transient String STATUS_TIME = "status_time";


    public void andHId(Opt opt) {
        addConditGroup(H_ID,opt);
    }

    public void andHId(Opt opt,Long hId) {
        addConditGroup(H_ID,opt,hId);
    }

    public void andHId(Opt opt,List<Long> list) {
        addConditGroup(H_ID,opt,list);
    }

    public void andHId(Opt opt,Long firstParam,Long secondParam) {
        addConditGroup(H_ID,opt,firstParam,secondParam);
    }

    public void andYear(Opt opt) {
        addConditGroup(YEAR,opt);
    }

    public void andYear(Opt opt,Integer year) {
        addConditGroup(YEAR,opt,year);
    }

    public void andYear(Opt opt,List<Integer> list) {
        addConditGroup(YEAR,opt,list);
    }

    public void andYear(Opt opt,Integer firstParam,Integer secondParam) {
        addConditGroup(YEAR,opt,firstParam,secondParam);
    }

    public void andCount(Opt opt) {
        addConditGroup(COUNT,opt);
    }

    public void andCount(Opt opt,Byte count) {
        addConditGroup(COUNT,opt,count);
    }

    public void andCount(Opt opt,List<Byte> list) {
        addConditGroup(COUNT,opt,list);
    }

    public void andCount(Opt opt,Byte firstParam,Byte secondParam) {
        addConditGroup(COUNT,opt,firstParam,secondParam);
    }

    public void andHolidayType(Opt opt) {
        addConditGroup(HOLIDAY_TYPE,opt);
    }

    public void andHolidayType(Opt opt,Byte holidayType) {
        addConditGroup(HOLIDAY_TYPE,opt,holidayType);
    }

    public void andHolidayType(Opt opt,List<Byte> list) {
        addConditGroup(HOLIDAY_TYPE,opt,list);
    }

    public void andHolidayType(Opt opt,Byte firstParam,Byte secondParam) {
        addConditGroup(HOLIDAY_TYPE,opt,firstParam,secondParam);
    }

    public void andDescript(Opt opt) {
        addConditGroup(DESCRIPT,opt);
    }

    public void andDescript(Opt opt,String descript) {
        addConditGroup(DESCRIPT,opt,descript);
    }

    public void andDescript(Opt opt,List<String> list) {
        addConditGroup(DESCRIPT,opt,list);
    }

    public void andDescript(Opt opt,String firstParam,String secondParam) {
        addConditGroup(DESCRIPT,opt,firstParam,secondParam);
    }

    public void andStatus(Opt opt) {
        addConditGroup(STATUS,opt);
    }

    public void andStatus(Opt opt,Byte status) {
        addConditGroup(STATUS,opt,status);
    }

    public void andStatus(Opt opt,List<Byte> list) {
        addConditGroup(STATUS,opt,list);
    }

    public void andStatus(Opt opt,Byte firstParam,Byte secondParam) {
        addConditGroup(STATUS,opt,firstParam,secondParam);
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

    public void andStatusTime(Opt opt) {
        addConditGroup(STATUS_TIME,opt);
    }

    public void andStatusTime(Opt opt,Date statusTime) {
        addConditGroup(STATUS_TIME,opt,statusTime);
    }

    public void andStatusTime(Opt opt,List<Date> list) {
        addConditGroup(STATUS_TIME,opt,list);
    }

    public void andStatusTime(Opt opt,Date firstParam,Date secondParam) {
        addConditGroup(STATUS_TIME,opt,firstParam,secondParam);
    }

}