$(".answerWrite input[type=submit]").click(addAnswer);

function addAnswer(e) {
    e.preventDefault();
    var queryString = $(".submit-write").serialize();

    $.ajax({ // Controller로 보냄.
        type: 'post',
        url: '/api/qna/addAnswer',
        data: queryString,
        dataType: 'json',
        error: onError,
        success: onSuccess,
    });
}

function onSuccess(json, status) {
    var answerTemplate = $("#answerTemplate").html();
    var template = answerTemplate.format(
        json.writer,
        new Date(json.createdDate),
        json.contents,
        json.answerId,
        json.answerId
        );
    $(".qna-comment-kuit-articles .article").append(template);
    var countOfAnswer = document.getElementsByTagName("strong").item(0);
    let number = parseInt(countOfAnswer.innerText, 10);
    number += 1;
    countOfAnswer.textContent = number.toString();
}

function onError(xhr, status) {
//    alert("Error");
    console.error("에러 상태:", status);
    console.error("서버 응답 내용:", xhr.responseText);
    alert("에러 발생: " + xhr.status + " - " + xhr.responseText);
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