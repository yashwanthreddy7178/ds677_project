package com.backrolemanager.model;

import java.util.List;

public interface BackRoleDAO_interface {
	public void insert(BackRoleVO backRoleVO);
	public void update(BackRoleVO backRoleVO);
	public void delete(String manpurview);
	public BackRoleVO findByPrimaryKey(String manpurview);
	public List<BackRoleVO> getAll();
}
