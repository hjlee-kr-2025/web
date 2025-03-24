package com.gyshop.board.vo;

import lombok.Data;

/* gyshop 데이터베이스에서 board 테이블에 대응하기 위해 만든 클래스
 * board 테이블에 구성되어있는 7개의 열이름을 모두 사용하였습니다.
 */
@Data
public class BoardVO {

	private Long no;
	private String title;
	private String content;
	private String writer;
	private String writeDate;
	private Long hit;
	private String pw;
}
