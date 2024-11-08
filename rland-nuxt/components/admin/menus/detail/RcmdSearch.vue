<template>
  <section v-if="show" class="n-frame:rounded-shadow n-panel:card-list bg-color:base-2 margin-bottom:7">
    <header class="d:flex">
      <h1>추천메뉴 추가 패널</h1>
      <div class="d:flex flex-grow:1">
        <button @click="addMenuHandler" class="ml:3 n-icon n-icon:add_task n-btn n-btn:rounded n-btn-size:small">추가
        </button>
        <button @click="toggleHandler" class="n-icon n-icon:x n-btn n-btn:rounded n-btn-size:small">추가</button>
        <button
            class="ml:auto n-icon n-icon:arrow_left n-btn n-btn:rounded n-btn-size:small">추가
        </button>
        <button
            class="ml:2 n-icon n-icon:arrow_right n-btn n-btn:rounded n-btn-size:small">추가
        </button>
      </div>
      <nav class="w:100p mt:3">
        <h1 class="d:none">필터링</h1>
        <ul class="n-bar">
          <li><a class="n-btn active" href="">전체</a></li>
          <li><a class="n-btn" href="">커피</a></li>
          <li><a class="n-btn" href="">케익</a></li>
          <li><a class="n-btn" href="">수제청</a></li>
          <li><a class="n-btn" href="">쿠키</a></li>
        </ul>
      </nav>
    </header>
    <div class="d:flex">
      <!---- 추천 메뉴 목록 ------------------------------------------>
      <section v-for="m in menus">
        <header>
          <h1 class="d:none">아메리카노</h1>
          <label class="mb:5">
            <input type="checkbox" v-model="m.checked" class="n-toggle n-toggle-type:check-box n-toggle-size:1">
            <span>선택</span>
          </label>
        </header>
        <div><img src="/image/product/latte.svg"></div>
        <dl class="n-list:dash-lined">
          <div>
            <dt>한글명</dt>
            <dd>{{ m.korName }}</dd>
          </div>
          <div>
            <dt>등록일자</dt>
            <dd>{{ m.regDate.split('T')[0] }}</dd>
          </div>
        </dl>
      </section>
      <!-- ---------------------------------------------- -->
    </div>
  </section>
</template>

<script setup>


//============reactive variable==============================
const menus = ref([
  {
    "id": 1798,
    "korName": "올드올드",
    "engName": "oldmericano",
    "price": 3000,
    "regDate": "2024-10-27T12:55:17Z",
    "categoryId": null,
    "regMemberId": null,
    "images": [],
    checked: false
  },
  {
    "id": 1797,
    "korName": "뉴드뉴드",
    "engName": "newamericano",
    "price": 3500,
    "regDate": "2024-10-27T12:45:11Z",
    "categoryId": null,
    "regMemberId": null,
    "images": [],
    checked: false
  },

]);



//===============component attribute=======================


const props = defineProps({
  show: Boolean,
  default: false
})

const emit = defineEmits(['add','close']);

const toggleHandler = () => {
  emit('close');
}

const addMenuHandler = () => {

  let filterdMenus = menus.value.filter(menu => menu.checked);
  console.log("test", filterdMenus);
  emit("add", filterdMenus);

  menus.value = menus.value.filter(menu => !menu.checked);

  // for (let i = menus.value.length - 1; i >= 0; i--) {
  //   if (menus.value[i].checked) {
  //     menus.value.splice(i, 1);
  //   }
  // }
}

</script>