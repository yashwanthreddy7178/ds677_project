package cn.com.ctrl.yjjy.project.system.echarsDP.entity;
import lombok.Data;
/**
 * echarts故障图表
 *
 * @author zzmh
 * @date 2018-12-07
 */
@Data
public class EchartsDevice {
    //当前设备总数
    private String device_all;
    //当前设备在线数
    private String device_online;
    //设备在线率（在线数的设备数量/总设备）
    private String rate_online;
    //设备事故率（发生事故的设备数量/总设备）
    private String rate_crash;
}
