<template>
  <div class="card card-full">
    <div class="card-image" @click="moveToDetail(product.nftSeq)">
      <img :src="`https://j6e205.p.ssafy.io/${product.fileUrl}`" class="card-img-top" alt="art image" />
    </div>
    <div class="card-body p-4">
      <h3 class="card-title text-truncate mb-0">{{ product.nftName }}</h3>
      <!-- <h5 class="card-title text-truncate mb-0">{{ product.nft.title }}</h5> -->
      <div class="card-author mb-1 d-flex align-items-center">
        <!-- <span class="me-1 card-author-by">Create at: </span> -->
        <div class="me-1 card-author-by" style="">{{ newTime }}</div>
        <!-- end custom-tooltip-wrap -->
      </div>
      <!-- end card-author -->
      <div class="card-price-wrap d-flex align-items-center justify-content-sm-between mb-3">
        <div class="me-5 me-sm-2">
          <span class="card-price-title">Author</span>
          <router-link :to="{ name: 'profile', params: { id: `${product.nftAuthorSeq}` } }">
            <span class="card-price-number">{{ product.nftAuthorName }}</span>
          </router-link>
        </div>
        <div class="text-sm-end">
          <span class="card-price-title">NFT Type</span>
          <span class="card-price-number">{{ strsplit(product.nftType) }}</span>
        </div>
      </div>
      <!-- end card-price-wrap -->
      <!-- <span v-if="clicked" class="btn btn-sm" style="background-color: #00FF7F">Remove</span>
      <span v-else class="btn btn-sm btn-dark">Add</span> -->
    </div>
    <!-- end card-body -->
    <!-- <router-link
      class="details"
      :to="{
        name: 'ProductDetail',
        params: {
          id: product.id,
          title: product.title,
          metaText: product.metaText,
          price: product.price,
          priceTwo: product.priceTwo,
          imgLg: product.imgLg,
          metaText: product.metaText,
          metaTextTwo: product.metaTextTwo,
          metaTextThree: product.metaTextThree,
          content: product.content,
        },
      }"
    >
    </router-link> -->
  </div>
  <!-- end card -->
</template>
<script>
export default {
  name: "NftProducts",
  props: ["product"],
  data() {
    return{
    }
  },
  methods: {
    moveToDetail(productId) {
      this.$router.push({
        name: "ProductDetail",
        params: {
          id: productId,
        },
      });
    },
    strsplit(req) {
      const beforeStr = req;
      const afterStr = beforeStr.split("/");
      return afterStr[0];
    },
    calculatedReplyTime(res) {
      console.log(res,'22e2e2');
      let ReplynewTime = new Date(res);
      var ReplynowTime = new Date();
      const milliSeconds = ReplynowTime - ReplynewTime;
      const seconds = milliSeconds / 1000;
      if (seconds < 60) return ` Just now`;
      const minutes = seconds / 60;
      if (minutes < 60) return `${Math.floor(minutes)} min ago`;
      const hours = minutes / 60;
      if (hours < 24) return `${Math.floor(hours)}h ago`;
      const days = hours / 24;
      if (days < 7) return `${Math.floor(days)} d ago`;
      const weeks = days / 7;
      if (weeks < 5) return `${Math.floor(weeks)} w ago`;
      const months = days / 30;
      if (months < 12) return `${Math.floor(months)} mon ago`;
      const years = days / 365;
      return `${Math.floor(years)} y ago`;
    },
  },
  mounted() {

  },
  created: function () {
    this.newTime = this.calculatedReplyTime(this.product.regDt)
  },
};
</script>

<style lang="css" scoped>
.details {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
}
.author-link,
.card-price-wrap {
  z-index: 2;
  position: relative;
}
.card-img-top {
  height: 200px;
  object-fit: cover;
}
</style>
