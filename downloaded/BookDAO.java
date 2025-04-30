package com.han.DAO;

import java.util.List;

import javax.annotation.Resource;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.han.domain.Book;




@Repository
public class BookDAO {
    
    @Resource
    private HibernateTemplate hibernateTemplate;
    
    //根据id查找数据
    public Book getBook(int bid){
        System.out.println(bid);
        return hibernateTemplate.get(Book.class, bid);
    }
    //获取总的记录数
    public int getCount(){
        return hibernateTemplate.execute(new HibernateCallback(){

            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                    String hql = "select count(*) from Book";
                    Query query = session.createQuery(hql);
                    int count = ((Long)query.iterate().next()).intValue();
                    return count;
            }
            
    });
    }
    //分页显示所有书籍信息
    public List<Book> getBookList(final int currentpage,final int currentnum){
        return hibernateTemplate.execute(new HibernateCallback(){

                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                        String hql = "select b from Book b";
                        Query query = session.createQuery(hql);
                        query.setFirstResult(currentpage);
                        query.setMaxResults(currentnum);
                        List list = query.list(); 
                        return list;
                }
                
        });
    }
        
     //根据ID删除
       public void deleteById(final int bid){
           hibernateTemplate.execute(new HibernateCallback(){

               @Override
               public Object doInHibernate(Session session) throws HibernateException {
                       
                       String hql = "delete from book where bid = ?";
                       Query query = session.createSQLQuery(hql);
                       query.setInteger(0,bid);
                       query.executeUpdate();
                       
                       return null;
               }
               
       });
      }
       //注册书籍信息
       public void RegisterBook(final Book book){
           hibernateTemplate.execute(new HibernateCallback(){
               
               @Override
               public Object doInHibernate(Session session) throws HibernateException {
                   
                   session.save(book);
                  
                   
                   return null;
               }
               
           });
       }
       
     //更新书籍信息
       public void UpdateBook(final Book book){
           hibernateTemplate.execute(new HibernateCallback(){
               
               @Override
               public Object doInHibernate(Session session) throws HibernateException {

                   String hql = "update book set bname = ?,btype = ?, bauthor = ?,"
                           + " bprice = ?,bdiscount = ?, bnumber = ?, btext = ? where bid = ?";
                   Query query = session.createSQLQuery(hql);
                   query.setString(0, book.getBname());
                   query.setString(1, book.getBtype());
                   query.setString(2, book.getBauthor());
                   query.setDouble(3, book.getBprice());
                   query.setString(4, book.getBdiscount());
                   query.setInteger(5, book.getBnumber());
                   query.setString(6, book.getBtext());
                   query.setInteger(7, book.getBid());

                   query.executeUpdate();

                   
                   return null;
               }
               
           });
       }
       
       

}
