<template>
  <main>
    <section class="">
      <header class="n-bar">
        <h1 class="n-heading:5">제품관리 / 메뉴관리</h1>
        <div class="ml:3 d:flex">
          <RouterLink class="n-icon n-icon:plus n-btn n-btn:rounded n-btn-size:small" to="menus/new">추가</RouterLink>
        </div>
      </header>

      <section class="n-frame:rounded-shadow">
        <header>
          <h1><span class="n-icon n-icon:search n-deco">검색</span></h1>
          <div class="ml:auto">
            <label class="n-icon n-icon:caret_down cursor:pointer">
              <span>확장버튼</span>
              <input class="d:none n-panel-expander" type="checkbox">
            </label>
          </div>
        </header>
        <form action="list" method="get" class="n-form n-label-pos:left">
          <div>
            <label>
              <span>한글명</span>
              <input v-model="keyWord" name="q" type="text">
            </label>
          </div>
          <div class="d:flex flex-direction:row jc:center">
            <label class="">
              <input type="checkbox" value="0" class="fl-grow:0">
              <span class="w:auto">전체</span>
            </label>
            <label>
              <input type="checkbox" name="c" class="fl-grow:0">
              <span class="w:auto">쿠키</span>
            </label>
          </div>
          <div class="d:flex">
            <button class="n-btn n-btn-color:main" @click.prevent="searchButtonClickHandler">검색</button>
            <button class="n-btn ml:1">취소</button>
          </div>
        </form>
      </section>

      <section class="n-frame:rounded-shadow">
        <header>
          <h1 class="d:none2"><span class="n-icon n-icon:view_list n-deco n-deco-gap:2">메뉴목록</span>
          </h1>
          <div>
            <span class="ml:1 n-heading:6">({{ totalCount }})</span>
            <button class="n-btn ml:3" @click="addButtonClickHandler">추가</button>
            <button class="n-btn ml:3" @click="delButtonClickHandler">삭제</button>
          </div>
        </header>


        <table class="n-table n-table:expandable">
          <thead>
          <tr>
            <th class="w:1">번호</th>
            <th class="w:0 md:w:4 overflow:hidden">사진</th>
            <th>한글명</th>
            <th class="w:0 md:w:2 n-heading-truncate">공개</th>
            <th class="w:3">비고</th>
          </tr>
          </thead>

          <tbody v-for="m in menus">
          <tr class="vertical-align:middle">
            <td>2</td>
            <td class="w:0 md:w:4 overflow:hidden"><img class="w:100p h:0 md:h:3 object-fit:cover"
                                                        src="/image/product/americano.svg"
                                                        th:src="@{/image/product/{img}(img=${m.img})}"></td>
            <td class="text-align:start n-heading-truncate text-indent:4 text-align:cetner">
              <RouterLink :to="`menus/${m.id}`">{{ m.korName }}</RouterLink>
            </td>
            <td class="w:0 md:w:2 n-heading-truncate">
              <label>
                <span class="d:none">공개</span>
                <input type="checkbox" class="n-toggle ml:auto">
              </label>
            </td>
            <td>
                <span class="d:inline-flex align-items:center">
                    <label class="n-icon n-icon:caret_down n-icon-size:2 n-btn mr:2">
                        <input type="checkbox" class="d:none n-row-expander">
                        <span>상세보기</span>
                    </label>
                    <RouterLink class="n-icon n-icon:edit_square n-icon-color:base-6"
                                :to="`menus/${m.id}/edit`">수정</RouterLink>
                    <form action="del" method="post" class="d:flex ai:center">
                        <input type="hidden" name="id">
                        <button class="n-icon n-icon:delete n-icon-color:base-6"
                                type="submit">삭제</button>
                    </form>
                </span>
            </td>
          </tr>
          <tr>
            <td colspan="5" class="bg-color:base-2">
              <section class="">
                <h1 class="d:none">상세내용</h1>
                <dl class="n-list:dash-lined">
                  <div class="md:d:none">
                    <dt>공개</dt>
                    <dd class="ml:1">
                      <label>
                        <span class="d:none">공개</span>
                        <input type="checkbox" class="n-toggle n-toggle-size:1 ml:auto">
                      </label>
                    </dd>
                  </div>
                  <div>
                    <dt>영문명</dt>
                    <dd class="ml:1">{{ m.engName }}</dd>
                  </div>
                  <div>
                    <dt>사진</dt>
                    <dd class="ml:1">
                      <ul class="n-bar flex-wrap:wrap">
                        <li th:each="img : ${m.images}" th:classappend="'active:border'">
                          <img class="max-width:5"
                               th:src="@{/image/product/{img}(img=${img.src})}"
                               src="/image/product/americano.svg"></li>
                      </ul>
                    </dd>
                  </div>
                  <div>
                    <dt>가격</dt>
                    <!--/*
                    참고자료
                    타임리프 숫자포맷 유틸객체 :  https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf#numbers
                    */-->
                    <dd class="ml:1">{{ m.price }}</dd>
                  </div>
                  <div>
                    <dt>등록일자</dt>
                    <!--/*
                    참고자료
                    타임리프 날짜포맷 유틸객체 : https://www.thymeleaf.org/doc/tutorials/3.1/usingthymeleaf#dates
                    자바 날짜포맷 문자들 : https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/text/SimpleDateFormat
                    */-->

                    <dd class="ml:1">
                      2024-12-25 12:00:00
                    </dd>
                  </div>
                </dl>

              </section>
            </td>
          </tr>
          </tbody>
        </table>
        <Pager :href="`menus`" :page-numbers="pageNumbers" :start-num="startNum" :total-page="totalPage"
        @pageChange="pageClickHandler"/>


      </section>

    </section>
  </main>
</template>

<script setup>
import {ref} from "vue";

const config = useRuntimeConfig();
const menus = ref([]);
const pageNumbers = ref([]);
const totalCount = ref(0);
let query = {};
let startNum = 0;
let totalPage = 0;

const {data, refresh} = useAuthFetch("admin/menus", {
   params: query,
});

watchEffect(() => {
  if (data.value) {
    menus.value = data.value.menus;
    pageNumbers.value = data.value.pages;
    totalCount.value = data.value.totalCount;
    startNum = data.value.pages[0];
    totalPage = data.value.totalPages;
  }

})

// 화면에 바꿀부분만 변수로 마련한다


// let list = reactive([]);


// let startNum = ref(0);

// const keyWord = ref([]);

// let query = useRoute().query;


// const page = ref({p: ``});

// let model = reactive({
//   totalCount:0,
//   menus: []
// })


//----------------------Data Functions--------------------

// const queryString = () => {
//   // let query = useRoute().query;
//
//   return `?q=${query.q || ''}&p=${query.p || 1}`;
// }

// const pageString = () => {
//   return encodeURIComponent(pageNums.value.p)
// }
//
// watch(
//     [() => query.value,()=>pageNums],
//     (newQuery, oldQuery) => {
//       console.log(`Page changed from ${oldQuery} to ${newQuery}`);
//       fetchMenus();
//     }
// );


///////////////life-cycle///////////////////////
// onBeforeMount(() => {
//   console.log("before mount")
// })
//
// onMounted(() => {
//   console.log("onmount")
//   // let page = useRoute().query.p || 1;
//   // let offset = (page - 1) % 5;
//   // startNum = page - offset;
//   // let nums = Array.from({length: 5}, (_, i) => i + startNum);
//   // pageNums.value = nums;
//   fetchMenus();
//
//   // console.log(startNum);
//   // console.log(pageNums);
// })
//
// onBeforeUpdate(() => {
//   console.log("before update")
// })
//
// onUpdated(() => {
//   console.log("onupdate");
//
//   // query.value = useRoute().query;
// })

//---------------------------funtions-------------------
// const fetchMenus = async () => {
//   const url = `http://localhost:8080/api/v1/admin/menus${queryString()}`;
//   const response = await fetch(url);
//   const result = await response.json();
//
//   menus.value = result.menus;
//   totalCount.value = result.totalCount;
//   pageNums.value = result.pages;
//
//   totalPage = result.totalPages;
//   startNum = result.pages[0];
//   // console.log(pageNums);
//
//
// }

//-------------axios api--------------------------------
// const axiosMenus = async () => {
//   const response = await axios.get("http://localhost:8080/api/v1/admin/menus");
//   menus.value = response.data.menus;
//   totalCount.value = response.data.elementsPerPage;
//   console.log(response.data.menus);
// }


// ----------------------callback functions----------------

// const searchButtonClickHandler = async () => {
//   await fetchMenus();
// }
//
const pageClickHandler = async (p) => {
  if (p < 1) {
    query.page = p;
    await refresh();
    alert("첫 페이지입니다");
    // fetchMenus();
    return;
  }
  if (p > totalPage) {
    query.page = p - 1;
    await refresh();
    alert("마지막페이지입니다.");
    // fetchMenus();
    return;

  }
  query.page = p;
  await refresh();
  // fetchMenus();
  // console.log(page.value.p)
}


const addButtonClickHandler = () => {
  // menus.push({
  //   korName: "아샷추",
  //   engName: "iceteaplusshot",
  //   price: 3700
  // });
  // count = menus.length;

  // count.value = list.length
  // console.log(list.length);

  //1. DOM으로 처리하는 방법
//   let trTemplate = `
//   <tbody>
//     <tr>
//     <td>1</td>
// </tr>
// </tbody>`
//
//   const table = document.querySelector("table");
//   table.insertAdjacentHTML("beforeend",trTemplate);
}
const delButtonClickHandler = (e) => {

  // if (list.length === 1)
  //   alert("1개 이상의 메뉴는 남아있어야합니다.")
  // else
  //   list.pop();
  // count = list.length;

}
</script>

<!--<script>      OPTIONS API -->
<!--export default {-->
<!--  data() {-->
<!--    return { a: 1 }-->
<!--  },-->
<!--  methods: {-->
<!--    plus() {-->
<!--      this.a++-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->