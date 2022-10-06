package jh.spring.mvc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jh.spring.mvc.dao.MemberDAO;
import jh.spring.mvc.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("msrv")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO mdao;
	
	@Override
	public boolean newMember(MemberVO mvo) {
		boolean isIntert = false;
		
		
		//회원가입 성공 시 true값 return.
		if(mdao.insertMember(mvo) > 0) isIntert = true;
		
		return isIntert;
	}

	@Override
	public MemberVO readOneMember(String memberId) {
		return mdao.selectOneMember(memberId);
	}

	@Override
	public boolean checkLogin(MemberVO mvo) {
		boolean loginResult = false;
		
		//회원이 존재한다면
		if((mdao.selectOneMember(mvo)) > 0) loginResult = true;
		
		
		return loginResult;
	}

	@Override
	public String checkMid(String mid) {

		return String.valueOf(mdao.selectCountMemberId(mid));
	}

	@Override
	public String findZipcode(String dong) throws JsonProcessingException {
		// 조회결과 출력방법 1 : csv (쉼표로 구분)
		// sido, gugun, dong, bunji
		// 서울, 강남구, 논현동, 123번지

		// 조회결과 출력방법 2 : xml
		// <zip><sido>서울</sido> <gugun>강남구</gugun>
		//      <dong>논현동</dong> <bunji>123번지</bunji></zip>

		// 조회결과 출력방법 3 : json (추천)
		// {'sido':'서울', 'gugun':'강남구',
		//  'dong':'논현동', 'bunji':'123번지'},
		// {'sido':'서울', 'gugun':'강남구',
		//  'dong':'논현동', 'bunji':'123번지'},
		// {'sido':'서울', 'gugun':'강남구',
		//  'dong':'논현동', 'bunji':'123번지'},

		// StringBuilder sb = new StringBuilder();
		// sb.append("{'sido':").append("'서울',")
		// .append("'gugun':").append("'강남구',")
		// .append("'dong':").append("'논현동',")
		// .append("'bunji':").append("'123번지',");
		// .append("}");

		// 조회 결과를 JSON 형태로 만들려면 상당히 복잡하고 어려움이 따른다.
		// ObjectMapper라는 라이브러리를 이용하면 손쉽게 JSON 형식의 데이터를 생성할 수 있다.
		ObjectMapper mapper = new ObjectMapper();
		String json = "";

		json = mapper.writeValueAsString(
				mdao.selectZipcode(dong + "%"));

		return json;
	}


}
