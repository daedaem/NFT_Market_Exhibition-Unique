<template>
  <div class="card card-full">
    <div class="card-image" @click="moveToDetail(`${product.nft.nftSeq}`)">
      <!-- <img :src="`https://cloudflare-ipfs.com/ipfs/${product.nft.nftWorkUri}`" class="card-img-top" alt="art image" /> -->
      {{ product.nft.nftWorkUri }}
    </div>
    <div class="card-body p-4">
      <h5 @click="moveToDetail(`${product.marketId}`)" class="card-title text-truncate mb-0">{{ product.nft.nftName }}</h5>
      <!-- <h5 class="card-title text-truncate mb-0">{{ product.nft.title }}</h5> -->
      <div class="card-author mb-1 d-flex align-items-center">
        <span class="me-1 card-author-by">By</span>
        <div class="custom-tooltip-wrap">
          <router-link :to="{ name: 'profile', params: { id: `${product.nft.nftAuthorName}` } }" class="custom-tooltip author-link">{{ product.nft.nftAuthorName }}</router-link>
          <!-- <div class="card-generic custom-tooltip-dropdown">
            <div class="author-action d-flex flex-wrap align-items-center mb-3">
              <div class="flex-shrink-0 avatar">
                <img :src="product.avatar" alt="avatar" />
              </div>
              <div class="ms-2">
                <span class="author-username">{{ product.userName }}</span>
                <span class="author-follow-text">{{ product.followersText }}</span>
              </div>
            </div>
            <h6 class="author-name mb-1">{{ product.authorName }}</h6>
            <p class="author-desc smaller mb-3">{{ product.desc }}</p>
            <div class="follow-wrap mb-3">
              <h6 class="mb-1 smaller text-uppercase">Followed by</h6>
              <div class="avatar-group">
                <router-link :to="avatar.path" v-for="avatar in product.avatars" :key="avatar.id">
                  <img :src="avatar.img" alt="avatar" />
                </router-link>
              </div>
            </div>
            <router-link :to="product.authorLink" class="btn btn-sm bg-dark-dim">Follow</router-link>
          </div> -->
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
      <span class="btn btn-sm btn-dark">Purchase</span>
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
import { createPopper } from "@popperjs/core";
export default {
  name: "Products",
  props: ["product"],
  methods: {
    moveToDetail(productId) {
      console.log(productId);
      this.$router.push({
        name: "ProductDetail",
        params: {
          id: productId,
        },
      });
    },
  },
  mounted() {
    /*============= Custom Tooltips =============== */
    function customTooltip(selector, active) {
      let elem = document.querySelectorAll(selector);
      if (elem.length > 0) {
        elem.forEach((item) => {
          const parent = item.parentElement;
          const next = item.nextElementSibling;
          createPopper(item, next);
          parent.addEventListener("mouseenter", function () {
            parent.classList.add(active);
          });
          parent.addEventListener("mouseleave", function () {
            parent.classList.remove(active);
          });
        });
      }
    }

    customTooltip(".custom-tooltip", "active");
  },
  created: function () {
    // console.log(this.product);
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
