package com.guan.weibo.message.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OrderBy;
import org.springframework.web.util.HtmlUtils;

import com.guan.weibo.user.domain.User;
import com.guan.weibo.user.domain.UserFav;
import com.guan.weibo.utils.RichTextProcessor;

@Entity(name="message")
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;

	public Message() {
		super();
	}
	
	public Message(User user,String content,int type) {
		this.user = user;
		this.content = content;
		this.type = type;
		this.time = new Timestamp(System.currentTimeMillis());
		this.scan_num = 0;
		this.reply_num = 0;
	}
	
	//消息表主键（uuid）
	@Id
	@GeneratedValue(generator = "ud")
	@GenericGenerator(name = "ud", strategy = "uuid")
	private String m_id;
	
	//消息用户
	@ManyToOne
	@JoinColumn(name="u_id")
	private User user;
	
	//消息标题
	@Column(name="title",length=100)
	private String title;
	
	//消息内容
	@Column(name="content",length=5000)
	private String content;
	
	//消息发布时间
	@Column(name="time")
	private Timestamp time;
	
	//消息类型
	@Column(name="type",length=1)
	private int type;
	
	//浏览次数
	@Column(name="scan_num")
	private int scan_num;
	
	//回复次数
	@Column(name="reply_num")
	private int reply_num;
	
	//用户关注消息表(关注此消息的多个用户)
	@OneToMany(fetch=FetchType.EAGER,mappedBy="message")
	private Set<UserFav> faveds;
	
	//图片表
	@OneToMany(fetch=FetchType.EAGER,mappedBy="message")
	private Set<Image> images;
	
	//评论表
	@OneToMany(fetch=FetchType.EAGER,mappedBy="message")
	@OrderBy(clause = "time desc")
	private Set<Reply> replys;
	
	//消息标签表
	@OneToMany(fetch=FetchType.EAGER,mappedBy="message")
	private Set<MessageLabel> labels;
	

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getScan_num() {
		return scan_num;
	}

	public void setScan_num(int scan_num) {
		this.scan_num = scan_num;
	}

	public int getReply_num() {
		return reply_num;
	}

	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}

	public Set<Image> getImages() {
		return images;
	}

	public void setImages(Set<Image> images) {
		this.images = images;
	}

	public Set<UserFav> getFaveds() {
		return faveds;
	}

	public void setFaveds(Set<UserFav> faveds) {
		this.faveds = faveds;
	}

	public Set<Reply> getReplys() {
		return replys;
	}

	public void setReplys(Set<Reply> replys) {
		this.replys = replys;
	}


	public Set<MessageLabel> getLabels() {
		return labels;
	}

	public void setLabels(Set<MessageLabel> labels) {
		this.labels = labels;
	}
	
	public String getSrc(){
		content = HtmlUtils.htmlUnescape(content);
		List<String> list = RichTextProcessor.getImgSrc(content);
		if(list!=null&&list.size()!=0){
			return list.get(0);
		}
		return "";
	}
	
	public String getHtml(){
		return RichTextProcessor.getHtml(content);
	}
	
	@Override
	public String toString() {
		return "Message [m_id=" + m_id + ", user=" + user.getNickname() + ", title=" + title + ", content=" + content + ", time=" + time + ", type=" + type
				+ ", scan_num=" + scan_num + ", reply_num=" + reply_num + ", faveds=" + faveds.size() + ", images=" + images.size()
				+ ", replys=" + replys.size() + ", labels=" + labels.size() + "]";
	}
	
	
	
}
