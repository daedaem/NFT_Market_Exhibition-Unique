<template>
  <div class="Mainsection-head" :class="classname">
    <h2 :class="isMargin" v-html="text"></h2>
    <p v-if="content">{{ content }}</p>
  </div>
  <!-- end section-head -->
</template>
<script>
export default {
  name: "MainSectionHeading",
  props: ["text", "content", "isMargin", "classname"],
};
</script>
<style scoped>
h2 {
  color: #fafafa;
}
v-html {
  color: #fafafa;
}
p {
  color: #fafafa;
}
</style>
