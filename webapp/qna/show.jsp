<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="ko">
<%@ include file="/include/header.jspf" %>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
    <header class="qna-header">
        <h2 class="qna-title">${question.title}</h2>
    </header>
    <div class="content-main">
        <article class="article">
            <div class="article-header">
                <div class="article-header-thumb">
                    <img src="../img/picture.jpeg" class="article-author-thumb" alt="">
                </div>
                <div class="article-header-text">
                    <span class="article-author-name">${question.writer}</span>
                    <span class="article-header-time">${question.createdDate}</span>
                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${question.createdDate}"/>
                    <i class="icon-link"></i>
                </div>
            </div>
            <div class="article-doc">
                <p>${question.contents}</p>
            </div>

            <div class="article-util">
                <ul class="article-util-list">
                    <c:if test="${not empty sessionScope.user}">
                        <c:if test="${sessionScope.user.name == question.writer}">
                            <li>
                                <a class="link-modify-article"
                                   href="/qna/form?questionId=${question.questionId}">수정</a>
                            </li>
                            <li>
                                <a class="link-modify-article"
                                   href="/qna/delete?questionId=${question.questionId}">삭제</a>
                            </li>
                        </c:if>
                    </c:if>
                    <li>
                        <a class="link-modify-article" href="/">목록</a>
                    </li>
                </ul>
            </div>
        </article>

        <div class="qna-comment">
            <div class="qna-comment-kuit">
                <p class="qna-comment-count"><strong>${question.countOfAnswer}</strong>개의 의견</p>

                <div class="qna-comment-kuit-articles">
                    <c:forEach items="${answers}" var="each">
                        <article class="article">
                            <div class="article-header">
                                <div class="article-header-thumb">
                                    <img src="https://graph.facebook.com/v2.3/1324855987/picture"
                                         class="article-author-thumb" alt="">
                                </div>
                                <div class="article-header-text">
                                        ${each.writer}
                                    <div class="article-header-time">
                                        <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${each.createdDate}"/>
                                    </div>
                                </div>
                            </div>
                            <div class="article-doc comment-doc">
                                <p>${each.contents}</p>
                            </div>
                            <div class="article-util">
                                <ul class="article-util-list">
                                    <li>
                                        <a class="link-modify-article"
                                           href="/api/qna/updateAnswer?answerId=${each.answerId}">수정</a>
                                    </li>
                                    <li>
                                        <form class="form-delete" action="/api/qna/deleteAnswer" method="POST">
                                            <input type="hidden" name="answerId" value="${each.answerId}"/>
                                            <button type="submit" class="link-delete-article">삭제</button>
                                        </form>
                                    </li>
                                </ul>
                            </div>
                        </article>
                    </c:forEach>
                    <div class="answerWrite">
                        <form class="submit-write">
                            <input type="hidden" name="questionId" value="${question.questionId}">
                            <div class="form-group col-lg-4" style="padding-top:10px;">
                                <input class="form-control" id="writer" name="writer" value=${sessionScope.user.name} readonly>
                            </div>
                            <div class="form-group col-lg-12">
                                <textarea name="contents" id="contents" class="form-control" placeholder=""></textarea>
                            </div>
                            <input class="btn btn-success pull-right" type="submit" value="답변하기" />
                            <div class="clearfix" />
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/template" id="answerTemplate">
    <article class="article">
        <div class="article-header">
            <div class="article-header-thumb">
                <img src="/img/picture.jpeg" class="article-author-thumb" alt="">
            </div>
            <div class="article-header-text">
                {0}
                <div class="article-header-time">{1}</div>
            </div>
        </div>
        <div class="article-doc comment-doc">
            {2}
        </div>
        <div class="article-util">
            <ul class="article-util-list">
                <li>
                    <a class="link-modify-article" href="/api/qna/updateAnswer/{3}">수정</a>
                </li>
                <li>
                    <form class="form-delete" action="/api/qna/deleteAnswer" method="POST">
                        <input type="hidden" name="answerId" value="{4}"/>
                        <button type="submit" class="link-delete-article">삭제</button>
                    </form>
                </li>
            </ul>
        </div>
    </article>
</script>
<script src="https://code.jquery.com/jquery-2.2.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
<script src="/js/scripts.js"></script>
<script>
    //자바 스크립트
    $(".answerWrite input[type=submit]").click(addAnswer);

    function addAnswer(e) {
        e.preventDefault();
        var queryString = $(".submit-write").serialize();

        $.ajax({
            type : 'post',
            url : '/api/qna/addAnswer',
            data : queryString,
            dataType : 'json',
            error: onError,
            success : onSuccess,
        });
    }

    //요청이 성공했을 때 아래 함수를 실행 시킴. 즉, 동적으로 화면 생성
    function onSuccess(json, status){
        var answerTemplate = $("#answerTemplate").html();
        var template = answerTemplate.format(json.answer.writer, new Date(json.answer.createdDate), json.answer.contents, json.answer.answerId, json.answer.answerId);
        $(".qna-comment-kuit-articles").prepend(template);
        var countOfAnswer = document.getElementsByTagName("strong").item(0);
        let number = parseInt(countOfAnswer.innerText,10);
        number += 1;
        countOfAnswer.textContent = number.toString();
    }

    function onError(xhr, status) {
        alert("error");
    }

    String.prototype.format = function() {
        var args = arguments;
        return this.replace(/{(\d+)}/g, function(match, number) {
            return typeof args[number] != 'undefined'
                ? args[number]
                : match
                ;
        });
    };
</script>
</body>
</html>
