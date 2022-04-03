<template>
  <div class="container mb-5">
    <div class="card card-full d-flex flex-row align-items-center">
      <div class="card-image col-sm-6 col-md-8 col-lg-8 col-xl-8 d-flex justify-content-center">
        <!-- <img src="https://images.pexels.com/photos/1328876/pexels-photo-1328876.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500g" class="card-img-top" style="height: auto" alt="art image" /> -->
        <img :src="`${EXHIBITION_CARDS.cardImage}`" class="ExhibitionCard-img-top" alt="art image" />
      </div>
      <div class="card-body p-4">
        <div class="d-flex">
          <h2 class="card-title text-truncate mb-0">{{ EXHIBITION_CARDS.dateDay }}</h2>
          <h5 class="card-title text-truncate ms-2 mb-0">{{ EXHIBITION_CARDS.dateMonth }}</h5>
        </div>
        <!-- <h5 class="card-title text-truncate mb-0">{{ product.nft.title }}</h5> -->
        <div class="card-author mb-1 align-items-center">
          <br />
          <h1 class="mt me-1">{{ EXHIBITION_CARDS.title }}</h1>

          <hr style="height: 3px" />
          <div class="card-price-wrap align-items-center justify-content-sm-between mb-3">
            <div class="me-5 me-sm-2">
              <span class="card-price-title">Featured Creators</span>
              <span class="card-price-number">{{ EXHIBITION_CARDS.creators }}</span>
            </div>
          </div>
          <!-- end custom-tooltip-wrap -->
        </div>
        <!-- end card-author -->
        <div class="card-price-wrap align-items-center justify-content-sm-between mb-3">
          <div class="me-5 me-sm-2">
            <span class="card-price-title">CURATORS</span>
            <span class="card-price-number">{{ EXHIBITION_CARDS.curators }}</span>
          </div>
        </div>
        <!-- end card-price-wrap -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ExhibitionCards",
  props: ["EXHIBITION_CARDS"],
  data() {
    return {
      // EXHIBITION_CARDS: this.$store.state.EXHIBITION_CARDS,
    };
  },
  // created() {
  //   this.EXHIBITION_CARDS = this.$store.state.ExhibitionsCards;
  // },
  // computed: {
  //   ...mapState(["ExhibitionsCards"]),
  // },
};
</script>

<style>
.ExhibitionCard-img-top {
  object-fit: fill;
  min-width: auto;
  min-height: 300px;
  max-height: 500px;
  border-top-left-radius: 0.375rem;
  border-top-right-radius: 0.375rem;
  box-shadow: 0px 0px 20px 0px rgb(24 24 24 / 7%);
}
.card-body {
  border-left: 1px solid rgb(24 24 24 / 3%);
}
</style>
