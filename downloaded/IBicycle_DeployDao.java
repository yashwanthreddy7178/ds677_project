package com.rentbikes.dao.bike;

import com.rentbikes.model.Bicycle_Deploy;

public interface IBicycle_DeployDao {
	public void fixOut(Bicycle_Deploy bicycle_deploy);//填写维修调出调度单，同时填写状态为1，意为已调出
	public void fixIn(Bicycle_Deploy bicycle_deploy);//补完维修调度单，即修改--状态为1的调度单，最后修改状态为0，即既有调出也有调入
	
	int addForBicycleToPile(Bicycle_Deploy deploy);	//购入的车辆调入
}
