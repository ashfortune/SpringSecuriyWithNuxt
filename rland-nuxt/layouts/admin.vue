<script setup>
import adminAside from '~/components/admin/AdminAside.vue';
import adminFooter from '~/components/admin/AdminFooter.vue';
import adminHeader from '~/components/admin/AdminHeader.vue';
</script>


<template>
    <div class="n-layout n-aside-size:full1 n-aside-float1 n-aside-pos:right1">
        <adminHeader/>
        <div class="xl:w:xlarge">
            <adminAside/>
            <slot/>
        </div>
        <adminFooter/>
    </div>
</template>
