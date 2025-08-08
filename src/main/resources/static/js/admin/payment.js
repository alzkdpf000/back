const menubtn1 = document.getElementById("menubtn1");
const sublist1 = document.getElementById("sublist1");
const menubtn2 = document.getElementById("menubtn2");
const sublist2 = document.getElementById("sublist2");
const menubtn3 = document.getElementById("menubtn3");
const sublist3 = document.getElementById("sublist3");
const menubtn4 = document.getElementById("menubtn4");
const sublist4 = document.getElementById("sublist4");
const submenus = document.querySelectorAll(".boot-link");
const btnfilterstatus = document.getElementById("btn-filter-status");
const allchecked1 = document.getElementById("allchecked1");
const allflasechecked1 = document.getElementById("allflasechecked1");
const allchecked2 = document.getElementById("allchecked2");
const allflasechecked2 = document.getElementById("allflasechecked2");
const checkboxactive1 = document.getElementById("checkboxactive1");
const checkboxactive2 = document.getElementById("checkboxactive2");
const bootpopbtn1 = document.getElementById("btn-filter-pm");
const popmenubt1 = document.getElementById("pop-menu-bt1");
const popmenubt2 = document.getElementById("pop-menu-bt2");
const modalclose = document.getElementById("close");
const body = document.getElementById("body");
const modal = document.getElementById("modal");
const modalopen = document.getElementById("modal-open");
const usermenubtn = document.getElementById("usermenubtn");
const usermenu = document.getElementById("usermenu");

// 메뉴 버튼 이벤트
if (menubtn1) {
    menubtn1.addEventListener("click", (e) => {
        sublist1?.classList.toggle("show");
        e.preventDefault();
    });
}
if (menubtn2) {
    menubtn2.addEventListener("click", (e) => {
        sublist2?.classList.toggle("show");
        e.preventDefault();
    });
}
if (menubtn3) {
    menubtn3.addEventListener("click", (e) => {
        sublist3?.classList.toggle("show");
        e.preventDefault();
    });
}
if (menubtn4) {
    menubtn4.addEventListener("click", (e) => {
        sublist4?.classList.toggle("show");
        e.preventDefault();
    });
}

// 유저 메뉴 버튼
if (usermenubtn && usermenu) {
    usermenubtn.addEventListener("click", (e) => {
        usermenu.classList.toggle("show");
    });
}

// 서브메뉴 active 토글
submenus.forEach((submenu) => {
    submenu.addEventListener("click", (e) => {
        e.preventDefault();
        submenus.forEach((active) => active.classList.remove("active"));
        submenu.classList.add("active");
    });
});

// 결제 상태 선택 토글
const paySelectBtn = document.getElementById("btn-filter-status");
const paySelect = document.querySelector(".bt-pop-menu-context");

paySelectBtn.addEventListener("click", () => {
    paySelect.classList.toggle("show");
});

// 전체 선택  전체 해제
const selectAllBtn = document.getElementById("btn-select-all");
const deselectAllBtn = document.getElementById("btn-deselect-all");
const checkBoxes = document.querySelectorAll(".boot-check-box");

selectAllBtn.addEventListener("click", () => {
    checkBoxes.forEach((box) => {
        const icon = box.querySelector("i.mdi-check");
        if (icon) {
            icon.style.display = "inline-block";
            box.classList.add("active");
        }
        box.classList.add("flash");
        box.addEventListener(
            "animationend",
            () => {
                box.classList.remove("flash");
            },
            { once: true }
        );
    });
    selectAllBtn.classList.add("flash");
    selectAllBtn.addEventListener(
        "animationend",
        () => {
            selectAllBtn.classList.remove("flash");
        },
        { once: true }
    );

    selectAllBtn.classList.add("active");
});

deselectAllBtn.addEventListener("click", () => {
    checkBoxes.forEach((box) => {
        const icon = box.querySelector("i.mdi-check");
        if (icon) {
            icon.style.display = "none";
            box.classList.remove("active");
        }
    });
    selectAllBtn.classList.remove("active");
});

// 그룹별 상위 체크박스 관련 변수
const checkAll = document.querySelectorAll(".all-check-btn");
const pays = ["collapse_payloading", "collapse_payFail", "collapse_cancel"];

const paySections = pays.map((id) => document.getElementById(id));

// 그룹별 전체 선택 버튼 클릭 시
checkAll.forEach((btn, index) => {
    btn.addEventListener("click", () => {
        const section = paySections[index];
        const icons = section.querySelectorAll("i.mdi-check");

        const isAnyUnchecked = Array.from(icons).some(
            (icon) => icon.style.display !== "inline-block"
        );

        icons.forEach((icon) => {
            icon.style.display = isAnyUnchecked ? "inline-block" : "none";
            const box = icon.closest(".boot-check-box");
            box?.classList.toggle("active", isAnyUnchecked);
        });

        const parentIcon = btn.querySelector("i.mdi-check");
        if (parentIcon) {
            parentIcon.style.display = isAnyUnchecked ? "inline-block" : "none";
            btn.classList.toggle("active", isAnyUnchecked);
        }
    });
});

// 개별 체크박스 클릭 시 - 상위 체크 상태 자동 갱신
document.querySelectorAll(".boot-check-box").forEach((box) => {
    box.addEventListener("click", () => {
        const icon = box.querySelector("i.mdi-check");
        const isChecked = icon.style.display === "inline-block";

        icon.style.display = isChecked ? "none" : "inline-block";
        box.classList.toggle("active", !isChecked);

        // 🔁 상위 체크 상태 갱신
        paySections.forEach((section, index) => {
            if (section.contains(box)) {
                const icons = section.querySelectorAll(
                    ".boot-check-box i.mdi-check"
                );
                const allChecked = Array.from(icons).every(
                    (i) => i.style.display === "inline-block"
                );

                const parentIcon = checkAll[index].querySelector("i.mdi-check");
                if (parentIcon) {
                    parentIcon.style.display = allChecked
                        ? "inline-block"
                        : "none";
                    checkAll[index].classList.toggle("active", allChecked);
                }
            }
        });
    });
});

// 결제 상세 선택 - +버튼 토글
const payBtnIcons = document.querySelectorAll(".mdi.mdi-plus");

payBtnIcons[0].addEventListener("click", () => {
    paySections[0].classList.toggle("show");
});
payBtnIcons[1].addEventListener("click", () => {
    paySections[1].classList.toggle("show");
});
payBtnIcons[2].addEventListener("click", () => {
    paySections[2].classList.toggle("show");
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
