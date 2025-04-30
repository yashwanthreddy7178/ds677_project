package com.qfedu.babyfood.service.impl;

import com.qfedu.babyfood.entity.TKnowledge;
import com.qfedu.babyfood.dao.TKnowledgeMapper;
import com.qfedu.babyfood.service.TKnowledgeService;
import com.qfedu.babyfood.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jesion
 * @since 2019-06-05
 */
@Service
public class TKnowledgeServiceImpl extends ServiceImpl<TKnowledgeMapper, TKnowledge> implements TKnowledgeService {

    @Autowired(required = false)
    private TKnowledgeMapper tKnowledgeMapper;

    @Override
    public R queryAll() {

        return R.setOK("OK", tKnowledgeMapper.queryAll());
    }
}
