<template>
  <div class="card card-full">
    <div class="card-image">
      <img src="@/images/thumb/nft-full.0609d1e0.jpg" alt="" />
      <!-- <img :src="`https://cloudflare-ipfs.com/ipfs/${product.nftMetadataUri}`" alt="" /> -->
    </div>
    <div class="card-body p-4">
      <h5 class="card-title text-truncate mb-0">{{ product.nftName }}</h5>
      <div class="card-author mb-1 d-flex align-items-center">
        <span class="me-1 card-author-by">By</span>
        <div class="custom-tooltip-wrap">
          <div class="card-generic custom-tooltip-dropdown">
            <div class="author-action d-flex flex-wrap align-items-center mb-3">
              <div class="flex-shrink-0 avatar"></div>
              <div class="ms-2"></div>
            </div>
            <div class="follow-wrap mb-3">
              <h6 class="mb-1 smaller text-uppercase">Followed by</h6>
              <div class="avatar-group"></div>
            </div>
            <!-- end follow-wrap  -->
          </div>
          <!-- end dropdown-menu -->
        </div>
        <!-- end custom-tooltip-wrap -->
      </div>
      <!-- end card-author -->
      <div class="card-price-wrap d-flex align-items-center justify-content-sm-between mb-3">
        <div class="me-5 me-sm-2">
          <span class="card-price-title">Price</span>
        </div>
        <div class="text-sm-end">
          <span class="card-price-title">Current bid</span>
        </div>
      </div>
      <!-- end card-price-wrap -->
      <span class="btn btn-sm btn-dark">Place Bid</span>
    </div>
  </div>
  <!-- end card -->
</template>
<script>
// import { createPopper } from "@popperjs/core";
export default {
  name: "Products",
  props: ["product"],
  // mounted() {
  //   /*============= Custom Tooltips =============== */
  //   function customTooltip(selector, active) {
  //     let elem = document.querySelectorAll(selector);
  //     if (elem.length > 0) {
  //       elem.forEach((item) => {
  //         const parent = item.parentElement;
  //         const next = item.nextElementSibling;
  //         createPopper(item, next);
  //         parent.addEventListener("mouseenter", function () {
  //           parent.classList.add(active);
  //         });
  //         parent.addEventListener("mouseleave", function () {
  //           parent.classList.remove(active);
  //         });
  //       });
  //     }
  //   }

  //   customTooltip(".custom-tooltip", "active");
  // },
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
</style>
