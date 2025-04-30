package com.github.chipolaris.bootforum.jsf.bean;

import java.util.List;
import java.util.Map;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.chipolaris.bootforum.dao.QueryFilterMeta;
import com.github.chipolaris.bootforum.dao.QueryMeta;
import com.github.chipolaris.bootforum.dao.QuerySortMeta.SortOrder;
import com.github.chipolaris.bootforum.domain.Comment;
import com.github.chipolaris.bootforum.domain.Discussion;
import com.github.chipolaris.bootforum.service.GenericService;

public class CommentListLazyModel extends LazyDataModel<Comment> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = LoggerFactory.getLogger(CommentListLazyModel.class); 
	
	private GenericService genericService;
	
	private Discussion discussion;
	
	public CommentListLazyModel(GenericService genericService, Discussion discussion) {
		this.genericService = genericService;
		this.discussion = discussion;
	}

	@Override
	public int count(Map<String, FilterMeta> filterBy) {
		
		QueryMeta.Builder<Comment> builder = LazyModelUtil.queryBuilder(Comment.class, filterBy);
		
		builder.filterMeta("discussion", this.discussion, QueryFilterMeta.MatchMode.EQUALS);
		
		return this.genericService.countEntities2(builder.build()).getDataObject().intValue();
	}

	@Override
	public List<Comment> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
		logger.debug("first is " + first + ", pageSize is " + pageSize);
		
		QueryMeta.Builder<Comment> builder = LazyModelUtil.queryBuilder(Comment.class, sortBy, filterBy);
		
		builder.filterMeta("discussion", this.discussion, QueryFilterMeta.MatchMode.EQUALS);
		builder.sortMeta("id", SortOrder.ASCENDING, false);
		
		return this.genericService.getEntities2(builder.startIndex(first).
				maxResult(pageSize).build()).getDataObject();
	}
}
