<template>
  <div class="card card-full">
    <div>{{product.nft.nftSeq}}</div>
    <div class="card-image" @click="moveToDetail(`${product.marketId}`)">
      <img src="http://img.khan.co.kr/news/2020/10/16/2020101601001687000138341.jpg" class="card-img-top" alt="art image" />
      <!-- {{ product.nft.nftWorkUri }} -->
    </div>
    <div class="card-body p-4">
      <h5 @click="moveToDetail(`${product.marketId}`)" class="card-title text-truncate mb-0">{{ product.nft.nftName }}</h5>
      <!-- <h5 class="card-title text-truncate mb-0">{{ product.nft.title }}</h5> -->
      <div class="card-author mb-1 d-flex align-items-center">
        <span class="me-1 card-author-by">By</span>
        <div class="">
          <router-link :to="{ name: 'profile', params: { id: `${product.nft.nftAuthorName}` } }" class="custom-tooltip author-link">{{ product.nft.nftAuthorName }}</router-link>
          <!-- end dropdown-menu -->
        </div>
        <!-- end custom-tooltip-wrap -->
      </div>
      <!-- end card-author -->
      <div class="card-price-wrap d-flex align-items-center justify-content-sm-between mb-3">
        <div class="me-5 me-sm-2">
          <span class="card-price-title">Price</span>
          <span class="card-price-number">&dollar;{{ product.price }}</span>
        </div>
        <div class="text-sm-end">
          <span class="card-price-title">NFT Type</span>
          <span class="card-price-number">{{ product.nft.nftType }}</span>
        </div>
      </div>
      <!-- end card-price-wrap -->
      <span v-if="clicked" class="btn btn-sm" @click="[clicked = !clicked, removeId(product.nft.nftSeq)]" style="background-color: #00FF7F">Remove</span>
      <span v-else class="btn btn-sm btn-dark" @click="[clicked = !clicked, insertId(product.nft.nftSeq)]">Add</span>
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
  name: "SelectedProducts",
  props: ["product", "selectedIds"],
  data() {
    return{
      clicked: false,
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
    removeId(Id) {
      this.$emit('removeId',Id)
    },
    insertId(Id) {
      this.$emit('insertId',Id)
    },
    isClicked() {
      if (this.selectedIds.includes(this.product.marketId)){
        this.clicked = true
      }
    }
  },
  mounted() {

  },
  created: function () {
    console.log(this.product, 'w조');
    this.isClicked()
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
