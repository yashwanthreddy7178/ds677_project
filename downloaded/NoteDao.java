package com.soft1841.web.blog.dao;

import com.soft1841.web.blog.entity.Note;
import com.soft1841.web.blog.util.SplitPage;

import java.util.HashMap;
import java.util.List;

public interface NoteDao {
    /**
     * 新增留言
     * @param note
     * @throws Exception
     * @return
     */
    public int insert(Note note) throws Exception;

    /**
     * 删除留言
     * @param id
     * @throws Exception
     * @return
     */
    public int delete(int id) throws Exception;

    /**
     * 根据Id查询留言
     * @param id
     * @return
     * @throws Exception
     */
    public Note queryById(int id) throws Exception;

    /**
     * 遍历留言
     * @return
     * @throws Exception
     */

    public List<Note> queryAll() throws Exception;

    public List<Note> findAll(SplitPage sp)throws Exception;

    public int getRows(HashMap tm) throws Exception;

    public List<Note>  queryByLike(HashMap<String, String> cond)throws Exception;

    public List<Note>  queryByLike(HashMap cond, SplitPage sp)throws Exception;


}
