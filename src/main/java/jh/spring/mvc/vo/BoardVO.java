package jh.spring.mvc.vo;

import groovy.transform.ToString;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@ToString
public class BoardVO {
	
	private long boardNo;
	private String title;
	private String content;
	private Date regDate;
	private int readcount;
	private String memberId;

}
