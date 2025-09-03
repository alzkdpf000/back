const paymentLayout = (() => {
    const showPayments = async (result) => {
        let text = ``;

        const payments = result.payments;
        console.log(payments)
        const total = result.total;
        const criteria = result.criteria;
        const paymentCount = document.getElementById("paymentCount");
        const pageWrap = document.querySelector(".pagination.bootpay-pagination.payment-pagination");
        const tbody = document.getElementById("paymentsTbody");
        document.getElementById("paymentKeyword").value = result.search.keyword;
        paymentCount.textContent = total;
        document.getElementById("cancelMoney").textContent = 0;
        document.getElementById("successMoney").textContent = 0;
        if (result.amounts.length >0) {
            result.amounts.forEach((amount) => {
                document.getElementById(`${amount.paymentStatus}Money`).textContent = amount.paymentAmount;
            })
        }


        if (payments.length === 0) {
            text +=
                `
            <td class="text-center font-weight-bold" colspan="8" > 결제 정보가 존재하지 않습니다</td>
            `
        } else {
            payments.forEach((payment) => {
                let paymentStatus = "";
                let color = "";
                switch (payment.paymentStatus) {
                    case "pending":
                        paymentStatus = "결제 대기";
                        color = "#292929";
                        successMoney += payment.paymentAmount;
                        break;
                    case "success":
                        paymentStatus = "결제 성공";
                        color = "#507cf3";
                        break;
                    case "cancel":
                        paymentStatus = "결제 취소";
                        color = "#fe657e";
                        cancelMoney += payment.paymentAmount;
                        break;
                }
                text += `
            <tr>
                                <!-- 결과가 없을 때 화면 -->
                                <!-- <td class="text-center" colspan="9">조회된 결제가 없습니다.</td> -->

                                <!-- 결제 내역이 있을 때 화면  -->
                                <td class="td-name">
                                    <div class="good-name">
                                        ${payment.paymentProductName}
                                    </div>
                                    <div class="receipt-id">
                                       ${payment.paymentTransactionId}
                                    </div>
                                </td>
                                <td class="td-amount text-right pr-4 font-weight-bold" style="color:${color}">
                                    ${payment.paymentAmount}
                                    <span class="amount-unit">

                                                            원
                                                        </span>
                                </td>
                                <td class="td-method">
                                    <div class="pq">${payment.paymentMethod}</div>
                                    <div class="api-version">
                                        <span class="version-2">API v2</span>
                                    </div>
                                </td>
                                <td class="td-status">
                                    <div class="label-form">
                                        <span class="badge-label text-nowrap text-dark" style="color:${color}">${paymentStatus}</span>
                                    </div>
                                </td>
                                <td class="td-at text-center">
                                    <div class="date-at text-dark">${payment.createdDatetime}</div>
                                </td>
                                <td class="td-buyer text-center text-dark">
                                    <div class="buyer-wrapper">
                                        <div class="user-name">${payment.memberName}</div>
                                        <div class="user-phone fs-1">${payment.memberEmail ? payment.memberEmail : payment.memberKakaoEmail}</div>
                                    </div>
                                </td>
                            </tr>
            `
            })
        }

        tbody.innerHTML = text;
        text = ``;
        if (criteria.hasPreviousPage) {
            text = `
            <li class="page-item page-num">
                <a data-page="${criteria.startPage - 1}" class="paging">이전</a>
            </li>`

        }

        for (let i = criteria.startPage; i <= criteria.endPage; i++) {
            text += `
            <li class="page-item page-num ${i === criteria.page && "active"}">
                <a data-page="${i}" class="paging">${i}</a>
            </li>
        `;
        }

        if (criteria.hasNextPage) {
            text += `
            <li class="page-item page-num">
                <a data-page="${criteria.endPage + 1}" class="paging">다음</a>
            </li>`
        }
        pageWrap.innerHTML = text;


    }

    return {showPayments: showPayments};
})();