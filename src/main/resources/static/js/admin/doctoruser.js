// 사이드바 메뉴 토글
const menuBtns = document.querySelectorAll(".menu-btn");
const allSubMenus = document.querySelectorAll(".menu-sub-list");

menuBtns.forEach((btn) => {
    btn.addEventListener("click", function () {
        allSubMenus.forEach((submenu) => (submenu.style.display = "none"));
        menuBtns.forEach((b) => b.classList.remove("active"));

        this.classList.add("active");

        const targetId = this.getAttribute("aria-controls");
        const targetMenu = document.getElementById(targetId);
        if (targetMenu) targetMenu.style.display = "block";
    });
});

const homeButton = document.getElementById("menu-home");
const sideMenuButtons = document.querySelectorAll(".menu-btn");
const sideSubLists = document.querySelectorAll(".menu-sub-list");
const sideSubLinks = document.querySelectorAll(".boot-link");
const tabNames = document.querySelectorAll(".tab-name");
const icons = document.querySelectorAll(".icon-wrapper i");

// 사이드 바 메인 메뉴 클릭 시 리스트 열고 닫기 + 아이콘
// 사이드 바 서브 링크 클릭 시 이벤트 + 다른 리스트 닫기
// 상단 tab바 이벤트
sideMenuButtons.forEach((sideMenuButton) => {
    sideMenuButton.addEventListener("click", (e) => {
        e.preventDefault();
        //기존(a 태그)의 이벤트를 멈추고 js로 직접 처리하기 위해 사용

        const targetId = sideMenuButton.getAttribute("aria-controls");
        // aria-controls와 같은 속성 가져올 땐 getAttribute();
        const targetSubList = document.getElementById(targetId);
        const targetIcon = sideMenuButton.querySelector(".icon-wrapper i");

        if (targetSubList.classList.contains("show")) {
            targetSubList.classList.remove("show");
            targetIcon.classList.remove("mdi-chevron-down");
            targetIcon.classList.add("mdi-chevron-right");
        } else {
            targetSubList.classList.add("show");
            targetIcon.classList.remove("mdi-chevron-right");
            targetIcon.classList.add("mdi-chevron-down");
        }
    });
});

sideSubLinks.forEach((sideSubLink) => {
    sideSubLink.addEventListener("click", (e) => {
        e.preventDefault();

        // 모든 서브 링크 active 초기화
        sideSubLinks.forEach((resetSubLink) => {
            resetSubLink.classList.remove("active");
        });
        sideSubLink.classList.add("active");

        // 상위 메뉴 버튼 current 초기화 + 설정
        sideMenuButtons.forEach((resetMenuButton) => {
            resetMenuButton.classList.remove("current");
        });

        const parentSubList = sideSubLink.parentElement.parentElement;
        const parentButton = document.querySelector(
            `.menu-btn[aria-controls = "${parentSubList.id}"]`
        );
        parentButton.classList.add("current");

        // 다른 리스트 닫기
        sideSubLists.forEach((sideSubList) => {
            if (sideSubList !== parentSubList) {
                sideSubList.classList.remove("show");

                const nonTargetButton = document.querySelector(
                    `.menu-btn[aria-controls="${sideSubList.id}"]`
                );
                if (nonTargetButton) {
                    const nonTargetIcon =
                        nonTargetButton.querySelector(".icon-wrapper i");
                    nonTargetIcon.classList.remove("mdi-chevron-down");
                    nonTargetIcon.classList.add("mdi-chevron-right");
                }
            }
        });

        // 상단 tab-name 활성화
        const linkText = sideSubLink.textContent.trim().replace("-", "").trim();
        // 링크 이름에서 기호(-)랑 공백 제거한 텍스트
        tabNames.forEach((tabName) => tabName.classList.remove("active"));
        tabNames.forEach((tabNameCheck) => {
            if (tabNameCheck.textContent.trim() === linkText) {
                tabNameCheck.classList.add("active");
            }
        });
    });
});

// 상단 tab-name 누르면 사이드 바 따라가는 이벤트
tabNames.forEach((headerTabname) => {
    headerTabname.addEventListener("click", (e) => {
        e.preventDefault();

        const tabText = headerTabname.textContent.trim();

        // 상단 탭 active 초기화
        tabNames.forEach((headerTab) => headerTab.classList.remove("active"));
        headerTabname.classList.add("active");

        // 사이드 링크 active 초기화 + 같은 텍스트만 active
        sideSubLinks.forEach((subLink) => {
            const linkText = subLink.textContent.trim().replace("-", "").trim();
            if (linkText === tabText) {
                subLink.classList.add("active");

                // 메뉴도 열고 current 붙이기
                const parentSubLink = subLink.parentElement.parentElement;
                const parentSubBtton = document.querySelector(
                    `.menu-btn[aria-controls="${parentSubLink.id}"]`
                );
                parentSubBtton.classList.add("current");
                parentSubLink.classList.add("show");

                const checkSubIcon =
                    parentSubBtton.querySelector(".icon-wrapper i");
                checkSubIcon.classList.remove("mdi-chevron-right");
                checkSubIcon.classList.add("mdi-chevron-down");
            } else {
                subLink.classList.remove("active");
            }
        });
    });
});

// 페이지 번호 클릭 이벤트
const pageNums = document.querySelectorAll(".page-num");
const pageItemNums = document.querySelectorAll(".page-item-num");

pageItemNums.forEach((pageItemNum) => {
    pageItemNum.addEventListener("click", (e) => {
        e.preventDefault();

        pageNums.forEach((pageNum) => {
            pageNum.classList.remove("active");
        });

        pageItemNum.parentElement.classList.add("active");
    });
});

// 일반회원 상세 모달 창 열고 닫는 이벤트
const modal = document.querySelector(".member-modal");
const actionButtons = document.querySelectorAll(".action-btn");
const closeButtons = document.querySelectorAll(".close");
const closeFooterButton = document.querySelector(".btn-close");

actionButtons.forEach((actionButton) => {
    actionButton.addEventListener("click", (e) => {
        console.log(222);
        modal.style.display = "block";

        // 일정 시간이 지난 후 한번만 실행
        setTimeout(() => {
            modal.classList.add("show");
            modal.style.background = "rgba(0,0,0,0.5)";
            document.body.classList.add("modal-open");
        }, 100);
    });
});

closeButtons.forEach((closeButton) => {
    closeButton.addEventListener("click", (e) => {
        modal.classList.remove("show");
        document.body.classList.remove("modal-open");

        setTimeout(() => {
            modal.style.display = "none";
        }, 100);
    });
});

modal.addEventListener("click", (e) => {
    if (e.target === modal) {
        modal.classList.remove("show");
        document.body.classList.remove("modal-open");

        setTimeout(() => {
            modal.style.display = "none";
        }, 100);
    }
});

closeFooterButton.addEventListener("click", (e) => {
    modal.classList.remove("show");
    document.body.classList.remove("modal-open");

    setTimeout(() => {
        modal.style.display = "none";
    }, 100);
});

// 상단 오른쪽 관리자 이메일 클릭 시 리스트 출력
// 출력된 리스트 다시 닫기
const userMenuWrapper = document.querySelector(".user-menu-wrapper");
const userMenuContent = document.querySelector(".user-menu-content");

userMenuWrapper.addEventListener("click", (e) => {
    e.preventDefault();
    if (userMenuContent.classList.contains("show")) {
        userMenuContent.classList.remove("show");
    } else {
        userMenuContent.classList.add("show");
    }
});

document.addEventListener("click", (e) => {
    e.preventDefault();
    if (
        // userMenuContent 안넣어주면 안에 걸 눌러도 리스트가 닫힌다.
        !userMenuWrapper.contains(e.target) &&
        !userMenuContent.contains(e.target)
    ) {
        userMenuContent.classList.remove("show");
    }
});
