<template>
  <div class="mt:4 text-align:center">
    <ul class="n-bar">
      <!--            <li v-for="p in totalPage" :key="p">-->
      <!--              <a class="n-btn" href="" @click.prevent="pageButtonHandler(p)">{{p}}</a>-->
      <!--            </li>-->
      <li>
        <RouterLink class="n-btn" :to="`${href}?page=${(startNum-1 < 1) ? 1 : startNum-1}`"
                    @click.prevent="emit('pageChange',startNum-1)">이전
        </RouterLink>
      </li>
      <li v-for="p in pageNumbers" :key="p">
        <RouterLink class="n-btn" :to="`${href}?page=${p}`"
                    :class="{active: p == useRoute().query.page}"
                    @click.prevent="emit('pageChange',p)">{{ p }}
        </RouterLink>
      </li>
      <li>
        <RouterLink class="n-btn" :to="`${href}?page=${(startNum+5>totalPage) ? totalPage : startNum+5}`"
                    @click.prevent="emit('pageChange',startNum+5)">다음
        </RouterLink>
      </li>
    </ul>
  </div>
</template>
<script setup>
import {useRoute} from "#vue-router";


const props = defineProps({
  href: {
    type: String,
    default: "./"
  },
  pageNumbers: {
    type: Array,
    default: []
  },
  startNum: {
    type: Number,
    default: 1
  },
  totalPage: {
    type: Number,
    default: 0
  }
})

const emit = defineEmits(['pageChange']);
</script>