package jh.spring.mvc.vo;

import java.sql.Date;

public class BoardVO {
	
	private long boardNo;
	private String title;
	private String content;
	private Date regDate;
	private int readcount;
	private String memberId;
	
	public BoardVO() {
	}

	public long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(long boardNo) {
		this.boardNo = boardNo;
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

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		String frm = "BoardVO [boardNo=%s, title=%s, content=%s, regDate=%s, readcount=%s, memberId=%s]";
		
		String result = String.format(frm, boardNo, title, content, regDate, readcount, memberId);
		
		return result;
	}

}
