package com.haolinbang.modules.sns.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.haolinbang.common.persistence.CrudDao;
import com.haolinbang.common.persistence.annotation.MyBatisDao;
import com.haolinbang.modules.sns.entity.WyComment;

/**
 * 评论和回复表DAO接口
 * @author nlp
 * @version 2017-07-18
 */
@MyBatisDao
public interface WyCommentDao extends CrudDao<WyComment> {

	List<WyComment> getByTypeAndId(@Param("bizType")String bizType, @Param("relationId")String relationId);

	List<WyComment> getByCommentId(@Param("commentId")String commentId);

	List<WyComment> getAllByTypeAndId(@Param("bizType")String bizType, @Param("relationId")String relationId);
	
}
