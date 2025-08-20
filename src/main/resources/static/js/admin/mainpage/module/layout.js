const mainLayout = (() => {
    const showMain = async (result) => {
        const today = new Date();

        document.getElementById("todayVisitCount").textContent = result.todayVisited;
        document.getElementById("monthlyVisitCount").textContent = result.monthlyVisited[2].count
        document.getElementById("todayJoinCount").textContent = result.todayJoin;
        document.getElementById("monthlyJoinCount").textContent = result.monthlyJoins[2].count;
    }
    return {showMain: showMain}
})();

