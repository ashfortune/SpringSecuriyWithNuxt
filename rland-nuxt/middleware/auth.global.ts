// import userDetails, {actions, actions as auth} from "~/gvar/userDetails."


export default defineNuxtRouteMiddleware(async (to, from) => {
    const userDetails = useUserDetails();

    console.log(to);

    //1. 인증정보요청
    // 1-1 없다면 signin으로 보내기
    // 1-2 가져오면 2단계로 진행
        if (to.path.startsWith("/admin")) { //url 이 admin으로 시작하고

            if (userDetails.isAnonymous()) {  // 로그인되어있지않다면
                return navigateTo(`/signin?returnURL=${to.fullPath}`) //로그인하라고 리턴
            }

            if (!userDetails.hasRole("ROLE_ADMIN")) {
                return navigateTo('/error403')
            }

        }


    // 2. /admin** 이 목적지라면 관리자인지?
    // 2-1 그렇다면 -> 통과
    // 2-2 아니라면 403에러


})
