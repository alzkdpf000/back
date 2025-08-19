const inquiryLayout = (() => {
    const showInquiries = async (result, load) => {
        console.log("몇 번이 실행될까")
        const inquiriesBody = document.getElementById("inquiriesBody");
        const totalInquires = document.getElementById("countAmount");
        const replyCount = document.getElementById("replyCount");
        const noReplyCount = document.getElementById("noReplyCount");
        let text = ``;
        const inquiryMemberReplyDTOs = result.inquiryMemberReplyDTOs;
        replyCount.textContent = result.inquiriesCountDto ? result.inquiriesCountDto.answerCount : 0 ;
        noReplyCount.textContent = result.inquiriesCountDto ? result.inquiriesCountDto.noAnswerCount : 0;
        totalInquires.textContent = Number(replyCount.textContent) + Number(noReplyCount.textContent);
        if (inquiryMemberReplyDTOs.length === 0 && !load) {
            text += `
            <td class="text-center font-weight-bold" colspan="6" >문의 내역이 없습니다</td>
            `
            inquiriesBody.innerHTML = text;
        } else {
            inquiryMemberReplyDTOs.forEach(inquiryMemberReplyDTO => {

                const email = inquiryMemberReplyDTO.memberProvider === "goldentime" ? inquiryMemberReplyDTO.memberEmail : inquiryMemberReplyDTO.memberKakaoEmail;
                text += `
                <tr>
                    <td class="text-list">${inquiryMemberReplyDTO.id}</td>
                    <td>${inquiryMemberReplyDTO.inquiryTitle}</td>

                    <td>${email}</td>
                    <td>${inquiryMemberReplyDTO.createdDateTimeInquiry}</td>
                    <td>${inquiryMemberReplyDTO.hasAnswer ? "답변 완료" : "미답변"}</td>
                    <td>${inquiryMemberReplyDTO.hasAnswer ? inquiryMemberReplyDTO.answerDatetimeReply : "-"}</td>
                    <td class="td-action text-center">
                        <div id="modal-open" class="action-btn" data-inquiryId = "${inquiryMemberReplyDTO.id}">
                            <i id="modalbtn" class="mdi mdi-chevron-right"></i></div>
                    </td>
                </tr>
            `

            });
            inquiriesBody.innerHTML += text
        }

    }
    const showDetailInquiry = async (result) => {
        const hasAnswerTag = document.getElementById("hasAnswer");
        const inquiryIdTag = document.getElementById("inquiryId");
        const createdDateTag = document.getElementById("createdDate");
        const titleTag = document.getElementById("title");
        const memberEmailTag = document.getElementById("memberEmail");
        const contentTag = document.getElementById("content");
        const replyContentTag = document.getElementById("replyContent");
        const modalFooter = document.getElementById("modalFooter");
        const inquiryDetailDiv = document.getElementById("inquiryDetailDiv")
        const imgContainer = document.getElementById("imgContainer");
        let text = ``;
        result.files.forEach((file) => {
            text += `
            <div><img src="/api/files/display?filePath=${file.filePath}&fileName=${file.fileName}" width="150" height="150"></div>
            `
        });
        const email = result.memberProvider === "goldentime" ? result.memberEmail : result.memberKakaoEmail;
        imgContainer.innerHTML = text;
        hasAnswerTag.textContent = result.inquiryReplyContent ? "답변 완료" : "미답변"
        inquiryIdTag.textContent = result.id;
        createdDateTag.textContent = result.createdDateTimeInquiry;
        titleTag.textContent = result.inquiryTitle;
        memberEmailTag.textContent = email;
        contentTag.textContent = result.inquiryContent;

        if (result.inquiryReplyContent) {
            modalFooter.style.display = "none";
            hasAnswerTag.classList.remove("text-danger");
            hasAnswerTag.classList.add("text-primary");
            inquiryDetailDiv.style.display = "block";
            replyContentTag.textContent = result.inquiryReplyContent;
        } else {
            hasAnswerTag.classList.remove("text-primary");
            hasAnswerTag.classList.add("text-danger");
            inquiryDetailDiv.style.display = "none";
            replyContentTag.textContent = "";
            modalFooter.style.display = "flex"
        }


    }
    return {showInquiries: showInquiries, showDetailInquiry: showDetailInquiry}
})();

