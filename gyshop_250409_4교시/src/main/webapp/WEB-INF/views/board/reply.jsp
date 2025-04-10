<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="card mt-3">
		<div class="card-header">
			<c:if test="${!empty login }">
				<span class="btn btn-primary float-right"
					id="replyWriteBtn">등록</span>
			</c:if>
			<h5><i class="fa fa-comment-o"></i> 댓글 리스트</h5>
		</div>
		<div class="card-body">
			<c:if test="${empty replyList }">
				등록된 댓글이 없습니다.
			</c:if>
			<c:if test="${!empty replyList }">
				<!-- 댓글리스트에 있는 만큼 반복처리 -->
				<c:forEach items="${replyList }" var="replyVO">
					<div class="card replyDataRow mb-1" data-rno="${replyVO.rno }">
						<div class="card-header">
							<c:if test="${replyVO.id == login.id }">
								<span class="btn btn-danger float-right"
									id="replyDeleteBtn">삭제</span>
								<span class="btn btn-success float-right mr-1"
									id="replyUpdateBtn">수정</span>
							</c:if>
							<b class="replyId">${replyVO.id }</b>
						</div>
						<div class="card-body">
							<pre class="replyContent">${replyVO.content }</pre>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
		<div class="card-footer">
		</div>
	</div>

<!-- 댓글 등록/수정/삭제를 위한 모달 -->
<!-- The Modal -->
<div class="modal" id="boardReplyModal">
  <div class="modal-dialog">
    <div class="modal-content">

      <!-- Modal Header -->
      <div class="modal-header">
      	<!-- 클릭된 버튼에 따라서 title명을 변경합니다. -->
        <h4 class="modal-title">Modal Heading</h4>
        <button type="button" class="close" data-dismiss="modal">&times;</button>
      </div>

			<form method="post" id="boardReplyForm">
				<input type="hidden" name="no" value="${param.no }">
				<input type="hidden" name="id" value="${login.id }">
	      <!-- Modal body -->
	      <div class="modal-body">
	        <div class="form-group" id="contentDiv">
	        	<label for="content">댓글내용</label>
	        	<textarea rows="3" class="form-control"
	        		id="content" name="content" required></textarea>
	        </div>
	      </div>
	
	      <!-- Modal footer -->
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-primary"
	      		id="replyModalWriteBtn">등록</button>
	        <button type="button" class="btn btn-warning"
	         data-dismiss="modal">취소</button>
	      </div>
			</form>
    </div>
  </div>
</div>

<script>
$(function(){
	// 댓글리스트에서 등록버튼 클릭시 이벤트 처리
	$("#replyWriteBtn").click(function(){
		// 모달창 제목 등록
		$("#boardReplyModal").find(".modal-title").text("댓글 등록");
		// 모달창의 내용을 지웁니다.
		$("#content").val("");
		// 모달창이 모이도록 합니다.
		$("#boardReplyModal").modal("show");
	});
	
	
	// 모달창의 등록버튼 클릭시 이벤트 처리
	$("#replyModalWriteBtn").click(function(){
		// 댓글 처리를 위한 경로 지정
		$("#boardReplyForm").attr("action", "/boardreply/write.do");
		// 데이터 전송을 실행하라- 폼태그의 submit이벤트
		$("#boardReplyForm").submit();
	});
});
</script>









