package jh.spring.mvc.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class MemberVO {
	
	private Long memberNo;
	private String memberId;
	private String password;
	private String memberName;
	private String email;
	private String regDate;

}
