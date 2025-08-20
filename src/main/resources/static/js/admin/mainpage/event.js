// 사이드바 메뉴 토글
const menuBtns = document.querySelectorAll(".menu-btn");
const allSubMenu = document.querySelector(".menu-sub-list");
let visitedData = null;
let visitedChart = null;
let visitedOptions = null;
let staticsData = null;
let joinData = null;
let joinChart = null;
let joinOptions = null;

document.getElementById("asd").addEventListener("click",(e)=>{
    console.log(123213412132)
})
document.querySelector(".boot-link.mr-3").addEventListener("click", async (e) => {
    e.preventDefault();
    inquiryScroll = false;
    allSubMenu.style.display = "none";
    document.querySelectorAll("div.wide-page").forEach((divTag) => {
        divTag.style.display = "none";
    })
    menuBtns.forEach((btn) => {
        btn.classList.remove("active");
    })
    document.getElementById("notices").style.display = "block";
    const contentWrap = document.getElementById("noticeBody");

    contentWrap.innerHTML = `<tr><td class="text-light-grey text-center" colspan="3">조회된 공지가 없습니다.</td></tr>`;
    loading.style.display = "block";
    setTimeout(async () => {
        await showNotices()
        loading.style.display = "none";
    }, 100)

})


menuBtns.forEach((btn) => {

    btn.addEventListener("click", async () => {
        inquiryScroll = false;
        categories = [];
        document.querySelectorAll(".show").forEach((show) => {
            show.classList.remove("show");
        });
        document.querySelectorAll(".active").forEach((active) => {
            active.classList.remove("active");
        });
        categorySet.clear();
        allSubMenu.style.display = "none";
        menuBtns.forEach((b) => b.classList.remove("active"));
        document.querySelectorAll("input[type=text].form-control").forEach((input) => {
            input.value = "";
        })
        let clickId = btn.classList[btn.classList.length - 1];
        console.log(clickId);
        document.querySelectorAll("div.wide-page").forEach((divTag) => {
            console.log(clickId);
            console.log(divTag);
            if (clickId !== "member")
                divTag.style.display = "none";
        })

        if (clickId === "inquiry") {
            document.getElementById(`${clickId}`).style.display = "block";
            page = 1;
            document.getElementById("inquiriesBody").innerHTML = "";
            document.getElementById("countAmount").textContent = 0;
            document.getElementById("replyCount").textContent = 0;
            document.getElementById("noReplyCount").textContent = 0;
            inquires = await showList();
            console.log("클릭 이벤트야")
            inquiryScroll = true;
            console.log(inquiryScroll)
        } else if (clickId === "approval") {
            document.getElementById(`${clickId}`).style.display = "block";
            document.getElementById("approvalTbody").innerHTML = "";
            await showPendingDoctors();
        } else if (clickId === "payment") {
            document.getElementById(`${clickId}`).style.display = "block";
            paymentSearch = await showPayments();
        } else if (clickId === "mainPage"){
            document.getElementById(`${clickId}`).style.display = "block";
            await drawChart();
        }
            btn.classList.add("active");


        const targetId = btn.getAttribute("aria-controls");
        const targetMenu = document.getElementById(targetId);
        if (targetMenu) targetMenu.style.display = "block";
    });
});


const icons = document.querySelectorAll(".icon-wrapper i");

// 상단 오른쪽 관리자 이메일 클릭 시 리스트 출력
// 출력된 리스트 다시 닫기
const userMenuWrapper = document.querySelector(".user-menu-wrapper");
const userMenuContent = document.querySelector(".user-menu-content");

userMenuWrapper.addEventListener("click", (e) => {
    e.preventDefault();
    if (userMenuContent.classList.contains("toggle")) {
        userMenuContent.classList.remove("toggle");
    } else {
        userMenuContent.classList.add("toggle");
    }
});



// 구글 차트 부분
function drawJoinChart() {
    joinData = new google.visualization.DataTable();
    joinData.addColumn('string', '년-월');
    joinData.addColumn('number', '가입자 수');

    joinOptions = {
        chart: {
            title: '최근 3개월 가입자 수',
            subtitle: '',
        },
        bar: { groupWidth: "25%" }
        ,
        width: 550,
        height: 250,
    };

    joinChart = new google.charts.Bar(document.getElementById('joinChart'));

    joinChart.draw(joinData, google.charts.Bar.convertOptions(joinOptions));
}


function drawVisitedChart() {
    visitedData = new google.visualization.DataTable();
    visitedData.addColumn('string', '년-월');
    visitedData.addColumn('number', '가입자 수');

    visitedOptions = {
        chart: {
            title: '최근 3개월 방문자 수',
            subtitle: '',
        },
        bar: { groupWidth: "25%" }
        ,
        width: 550,
        height: 250,
    };

    visitedChart = new google.charts.Bar(document.getElementById('visitedChart'));

    visitedChart.draw(visitedData, google.charts.Bar.convertOptions(visitedOptions));
}


window.addEventListener('DOMContentLoaded', async () => {
    await drawChart();
});


const drawChart =async  ()=>{
    google.charts.load("current", {'packages':['bar']});
    const today = new Date();
    staticsData = await mainService.getMain(mainLayout.showMain);
    await google.charts.setOnLoadCallback(drawJoinChart);
    await google.charts.setOnLoadCallback(drawVisitedChart);
    console.log(typeof (today.getFullYear()+"/"+String(staticsData.monthlyVisited[0].date).padStart(2,'0')));
    console.log(today.getFullYear()+String(staticsData.monthlyVisited[0].date).padStart(2,'0'))
    for(let i = 0; i<3; i++){
        visitedData.addRow([today.getFullYear()+"/"+String(staticsData.monthlyVisited[i].date).padStart(2,'0'),Number(staticsData.monthlyVisited[i].count)])
        joinData.addRow([today.getFullYear()+"/"+String(staticsData.monthlyJoins[i].date).padStart(2,'0'),Number(staticsData.monthlyJoins[i].count)])
    }
    visitedChart.draw(visitedData,visitedOptions);
    joinChart.draw(joinData,joinOptions);
}