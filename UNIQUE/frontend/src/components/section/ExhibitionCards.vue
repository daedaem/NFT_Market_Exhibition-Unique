<template>
  <div class="container mb-5" style="">
    <div class="card card-full d-flex flex-row align-items-center">
      <div class="card-image col-md-8 col-lg-8 col-xl-8">
        <!-- <img src="https://images.pexels.com/photos/1328876/pexels-photo-1328876.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500g" class="card-img-top" style="height: auto" alt="art image" /> -->
        <img
          src="http://lonelyplanet.co.kr/upload/moduleBasic/e36b45e8-f88f-45eb-9240-88d7bee2ddac.jpg"
          class="card-img-top"
          style="object-fit: fit; min-height: 300px; max-height: 500px; object-fit: cover"
          alt="art image"
        />
      </div>
      <div class="card-body p-4">
        <div class="d-flex">
          <h2 class="card-title text-truncate mb-0">2</h2>
          <h5 class="card-title text-truncate ms-2 mb-0">April</h5>
        </div>
        <!-- <h5 class="card-title text-truncate mb-0">{{ product.nft.title }}</h5> -->
        <div class="card-author mb-1 align-items-center">
          <br />
          <h1 class="mt me-1">This Week's Unique Steals:</h1>

          <hr style="height: 3px" />
          <div class="card-price-wrap align-items-center justify-content-sm-between mb-3">
            <div class="me-5 me-sm-2">
              <span class="card-price-title">CURATORS</span>
              <span class="card-price-number">Curabitur aliquet quam id dui posuere blandit. Donec rutrum congue leo eget malesuada.</span>
            </div>
          </div>
          <!-- end custom-tooltip-wrap -->
        </div>
        <!-- end card-author -->
        <div class="card-price-wrap align-items-center justify-content-sm-between mb-3">
          <div class="me-5 me-sm-2">
            <span class="card-price-title">CURATORS</span>
            <span class="card-price-number">sapien massa</span>
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
  data() {
    return {};
  },
};
</script>

<style></style>
