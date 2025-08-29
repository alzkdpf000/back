const consultationMainPageLayout = (() => {
    const showList = (result) => {
        const consultationPostContainer = document.querySelector("#intersectionObserver");
        let text = ``;

        result.consultationPosts.forEach(post => {
            let categoryText = ``;
            let imgText = ``;
            console.log(post.consultationPostFiles);
            post.consultationPostFiles.forEach((file) => {
                imgText += `
                <li>
                    <img src="/api/files/display?filePath=${file.filePath}&fileName=${file.fileName}" width="161" height="161" alt="">
                </li>
                `
            })
            if(post.categories.length===0){
                categoryText += "기타"
            }else{
                post.categories.forEach((category, index) => {
                    categoryText += `
                ${category}${index === post.categories.length - 1 ? '' : ' ·'}
                `
                })
            }

            console.log(post.memberProvider)
            let providerImgSrc;
            if(post.memberProvider === "kakao"){
                providerImgSrc = post.memberFilePath;
            }else{
                if(post.memberFilePath){
                    const file = post.memberFilePath.split("/");
                    const fileName = file.pop();
                    const filePath = file.join("/");
                    providerImgSrc = `/api/files/display?filePath=${filePath}&fileName=${fileName}`;
                }else{
                    providerImgSrc = "https://media.a-ha.io/aha-qna/images/v3/product/default-profile-image.webp";
                }

            }

            text += `
            <li>
                        <a href="/consultation-post/detail/${post.id}">
                            <div class="answer-list-container contain-imgs">
                            <img src="${providerImgSrc}" class="answer-client-img">
                                <div class="answer-list-content-tag">
                                    <div class="answer-writer-cotent-wrap">
                                        <div class="category-wrap">
                                                <span id="categoryList">
                                                    ${categoryText}
                                                </span>
                                        </div>
                                        <div class="category-bottom-wrap">
                                                <span class="writer-name">
                                                    ${post.memberName}
                                                </span>
                                            <svg fill="none" height="3" viewBox="0 0 2 3" width="2"
                                                 xmlns="http://www.w3.org/2000/svg">
                                                <circle cx="1" cy="1.5" fill="#282b2f" r="1"></circle>
                                            </svg>
                                            <span class="write-create-time write-date">${post.relativeDate}</span>
                                        </div>
                                    </div>
                                    <div class="answer-content-container">
                                        <span class="post-title">${post.consultationPostTitle}</span>
                                        <span class="post-content">${post.consultationPostContent}</span>
                                    </div>
                                    <ul class="answer-imag-container" id="consultationPostFile">
                                        ${imgText}
                                    </ul>
                                    <div class="answer-cnt-wrap">
                                        <div class="answer-icon-cnt-wrap">
                                            <svg fill="none" height="24" viewBox="0 0 24 24" width="24"
                                                 xmlns="http://www.w3.org/2000/svg">
                                                <path clip-rule="evenodd"
                                                      d="M6.4999 8.40161C6.20816 8.40161 5.92837 8.5175 5.72208 8.72379L3.72208 10.7238C3.55997 10.8859 3.45254 11.0947 3.41485 11.3209L3.37156 11.5826C3.34409 11.7494 3.30488 11.9886 3.25784 12.2787C3.16379 12.8586 3.03829 13.643 2.91269 14.4593C2.78726 15.2747 2.66098 16.1268 2.5658 16.8406C2.4753 17.5193 2.3999 18.1628 2.3999 18.5016C2.3999 19.264 2.55128 20.096 2.98927 20.8406C3.4396 21.6061 4.15553 22.213 5.15205 22.5452C5.46073 22.6481 5.88459 22.7119 6.3056 22.7589C6.75442 22.809 7.29695 22.8494 7.89444 22.8813C9.09107 22.9451 10.5489 22.9766 11.9999 22.9766C13.4509 22.9766 14.9087 22.9451 16.1054 22.8813C16.7029 22.8494 17.2454 22.809 17.6942 22.7589C18.1152 22.7119 18.5391 22.6481 18.8478 22.5452C19.8443 22.213 20.5602 21.6061 21.0105 20.8406C21.4485 20.096 21.5999 19.264 21.5999 18.5016C21.5999 18.1628 21.5245 17.5193 21.434 16.8406C21.3388 16.1268 21.2125 15.2747 21.0871 14.4593C20.9615 13.643 20.836 12.8586 20.742 12.2787C20.6949 11.9886 20.6557 11.7494 20.6282 11.5826L20.5851 11.322L20.5849 11.3208C20.5472 11.0946 20.4398 10.8859 20.2777 10.7238L18.2777 8.72379C18.0714 8.5175 17.7916 8.40161 17.4999 8.40161H6.4999Z"
                                                      fill="#5682EF" fill-rule="evenodd"></path>
                                                <path clip-rule="evenodd"
                                                      d="M22.2222 10.205C23.5888 9.3906 23.5888 7.4115 22.2222 6.5971L13.4864 1.39111C12.5087 0.80843 11.2902 0.808432 10.3125 1.39111L1.57671 6.5971C0.210103 7.41151 0.210112 9.39061 1.57671 10.205L10.3125 15.411C11.2902 15.9937 12.5087 15.9937 13.4864 15.411L22.2222 10.205Z"
                                                      fill="#2553E5" fill-rule="evenodd"></path>
                                                <path clip-rule="evenodd"
                                                      d="M15 8.5C15 8.69258 14.9274 8.87669 14.7952 9.04586L19.0242 11.6484C19.32 11.8304 19.5001 12.1528 19.5001 12.5V15.3821C19.8069 15.6567 20 16.0558 20 16.5V18.5C20 19.3284 19.3284 20 18.5 20C17.6716 20 17 19.3284 17 18.5V16.5C17 16.0557 17.1932 15.6565 17.5001 15.3818V13.0588L12.4964 9.97956C12.3349 9.99301 12.1691 10 12 10C10.3431 10 9 9.32843 9 8.5C9 7.67157 10.3431 7 12 7C13.6569 7 15 7.67157 15 8.5Z"
                                                      fill="white" fill-rule="evenodd"></path>
                                            </svg>
                                            <span class="doctor-cnt"> <span class="strong"> ${post.consultationPostAnswerCount}명</span>의 의사가 답변했어요</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </a>
                    </li>
            
            `
        })
        consultationPostContainer.innerHTML += text;
    }
    return {showList: showList};
})();


