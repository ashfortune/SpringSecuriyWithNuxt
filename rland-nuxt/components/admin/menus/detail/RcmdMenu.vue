<template>
  <section class="n-frame:rounded-shadow n-panel:card-grid">
    <header>
      <h1><span class="n-icon n-icon:recommend n-deco">추천목록</span></h1>
      <div class="ml:3 d:flex ai:center">
        <a class="n-icon n-icon:plus n-btn n-btn:rounded n-btn-size:small" @click.prevent="isPanelShow = true">추가</a>
      </div>
    </header>
    <AdminMenusDetailRcmdSearch :show="isPanelShow" @add="addHandler" @close="toggleHandler"/>
    <div>
      <section v-for="m in menus">
        <header>
          <h1 class="d:none">아메리카노</h1>
          <form method="post" class="mb:5 n-icon n-icon:remove_selection n-deco">
            <input type="hidden" value="1">
            <button>삭제</button>
          </form>
        </header>
        <div><img src="/image/product/latte.svg"></div>
        <dl>
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
    </div>
  </section>
</template>

<script setup>
const menus = ref([
  // {
  //   "id": 1795,
  //   "korName": "올드올드메리카노",
  //   "engName": "oldmericano",
  //   "price": 3000,
  //   "regDate": "2024-10-27T12:53:56Z",
  //   "categoryId": null,
  //   "regMemberId": null,
  //   "images": [],
  //   checked: false
  // },
  // {
  //   "id": 1794,
  //   "korName": "뉴네오1111",
  //   "engName": "newneo",
  //   "price": 222000,
  //   "regDate": "2024-10-24T11:07:58Z",
  //   "categoryId": null,
  //   "regMemberId": null,
  //   "images": [],
  //   checked: false
  // },
  {
    "id": 1793,
    "korName": "뉴네오1111",
    "engName": "newneo",
    "price": 222000,
    "regDate": "2024-10-24T09:41:19Z",
    "categoryId": null,
    "regMemberId": null,
    "images": [],
    checked: false
  },
  {
    "id": 1792,
    "korName": "aaaaa",
    "engName": null,
    "price": 2000,
    "regDate": "2024-10-22T12:16:52Z",
    "categoryId": null,
    "regMemberId": null,
    "images": [],
    checked: false
  }
])

const isPanelShow = ref(false);

const toggleHandler = () => {
  isPanelShow.value = !isPanelShow.value;
}

const addHandler = (addMenu) => {
  console.log("addHandler", addMenu);
  menus.value.push(...addMenu);

}

</script>
