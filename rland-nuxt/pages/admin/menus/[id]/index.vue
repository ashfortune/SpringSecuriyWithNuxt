<template>

  <Head>
    <Title>메뉴n번</Title>
  </Head>
  <main class="">

    <section class="">
      <header class="n-bar">
        <h1 class="n-heading:5">제품관리 / 메뉴관리</h1>
        <div class="ml:3 d:flex ai:center">
          <RouterLink to="/admin/menus" class="n-icon n-icon:arrow_left n-btn n-btn:rounded n-btn-size:small">
            뒤로
          </RouterLink>
        </div>
      </header>

      <section class="n-frame:rounded-shadow n-panel-detail">
        <header>
          <h1><span class="n-icon n-icon:description n-deco">메뉴상세</span></h1>
          <div>
            <a href="edit.html" class="n-icon n-icon:edit_square n-btn n-btn:rounded ml:3">수정</a>
          </div>
        </header>
        <dl class="n-list:dash-lined">
          <div>
            <dt>영문명</dt>
            <dd class="ml:1">aaaaa</dd>
          </div>
          <div>
            <dt>사진</dt>
            <dd class="ml:1">
              <ul class="n-bar flex-wrap:wrap">
                <li class=""><img src="/image/product/americano.svg"></li>
                <li class="active:border"><img src="/image/product/cafe-latte.svg"></li>
              </ul>
            </dd>
          </div>
          <div>
            <dt>가격</dt>
            <dd class="ml:1">1,000원</dd>
          </div>
          <div>
            <dt>등록일자</dt>
            <dd class="ml:1">2024-12-25 12:00:00</dd>
          </div>
        </dl>

      </section>
<!--      추천메뉴목록 import안하고 그냥 사용할수있음-->
     <AdminMenusDetailRcmdMenu/>
    </section>
  </main>
</template>

