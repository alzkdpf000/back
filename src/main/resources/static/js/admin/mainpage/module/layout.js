const mainLayout = (() => {
    const showInquiries = async (result,load) => {
        const inquiriesBody = document.getElementById("inquiriesBody");
        const totalInquires = document.getElementById("countAmount");
        const replyCount = document.getElementById("replyCount");
        const noReplyCount = document.getElementById("noReplyCount");
        let text = ``;
        const inquiryMemberReplyDTOs = result.inquiryMemberReplyDTOs;
        const scrollCriteria= result.scrollCriteria;
        replyCount.textContent = result.inquiriesCountDto.answerCount;
        noReplyCount.textContent = result.inquiriesCountDto.noAnswerCount;
        totalInquires.textContent = Number(replyCount.textContent) + Number(noReplyCount.textContent);
        if (inquiryMemberReplyDTOs.length === 0) {
            text += `
            <td class="text-center font-weight-bold" colspan="6" >문의 내역이 없습니다</td>
            `
        } else {
            inquiryMemberReplyDTOs.forEach((inquiryMemberReplyDTO,i) => {
                text += `
                <tr>
                    <td class="text-list">${inquiryMemberReplyDTO.id}</td>
                    <td>${inquiryMemberReplyDTO.inquiryTitle}</td>
                    <td>${inquiryMemberReplyDTO.createdDateTimeInquiry}</td>
                    <td>${inquiryMemberReplyDTO.hasAnswer ? "답변 완료" : "미답변"}</td>
                    <td>${inquiryMemberReplyDTO.hasAnswer ? inquiryMemberReplyDTO.answerDatetimeReply : "-" }</td>
                    <td class="td-action text-center">
                        <div id="modal-open" class="action-btn" data-inquiryId = "${inquiryMemberReplyDTO.id}">
                            <i id="modalbtn" class="mdi mdi-chevron-right"></i></div>
                    </td>
                </tr>
            `
            });
        }

        load ? inquiriesBody.innerHTML += text : inquiriesBody.innerHTML = text;

    }
    return {showInquiries: showInquiries}
})();


// <tr>
//     <!-- <td class="text-center font-weight-bold" colspan="5" >문의 내역이 없습니다</td> -->
//     <td className="text-list">12345678</td>
//     <td>안녕하세요</td>
//     <td>2025-08-03</td>
//     <td>답변완료</td>
//     <td>2025-08-04</td>
//     <td className="td-action text-center">
//         <div id="modal-open" className="action-btn">
//             <i id="modalbtn" className="mdi mdi-chevron-right"></i></div>
//     </td>
//
// </tr>