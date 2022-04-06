<template>
  <div class="page-wrap">
    <!-- header  -->
    <header class="header-section has-header-main">
      <!-- Header main -->
      <HeaderMain></HeaderMain>
    </header>
    <div style="max-width: 1200px; margin: auto">
      <div class="loginbar d-flex justify-content-end align-items-center" style="position: sticky; top: 70px; height: 60px; padding-right: 50px; background: white">
        <!-- sell버튼 시작 난중에 내가 이 nft의 주인이면 조건걸기 -->
        <router-link v-if="true" :to="{ name: 'SaleCreate', params: { id: this.$route.params.id } }" class="btn btn-dark d-block mb-2">Sell</router-link>
        <!-- sell버튼 끝-->
      </div>
    </div>
    <div class="d-flex flex-column"></div>
    <section class="item-detail-section section-space">
      <div class="container">
        <div class="row">
          <div class="col-lg-6 pe-xl-5">
            <div class="item-detail-content">
              <div class="item-detail-img-container mb-4">
                <img :src="imgLg" />
              </div>
              <!-- end item-detail-img-container -->
              <!-- 사진아래1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111 -->
              <div class="item-detail-tab">
                <ul class="nav nav-tabs nav-tabs-s1" id="myTab" role="tablist">
                  <li class="nav-item" role="presentation" v-for="list in SectionData.itemDetailData.itemDetailTabNav" :key="list.id">
                    <button class="nav-link" :class="list.isActive" :id="list.slug" data-bs-toggle="tab" :data-bs-target="list.bsTarget" type="button">{{ list.title }}</button>
                  </li>
                </ul>
                <div class="tab-content mt-3" id="myTabContent">
                  <div class="tab-pane fade show active" id="owners" role="tabpanel" aria-labelledby="owners-tab">
                    <div class="item-detail-tab-wrap">
                      <div class="card-media card-media-s2 mb-3">
                        <router-link :to="itemDetailList[1].path" class="card-media-img flex-shrink-0 d-block">
                          <img :src="itemDetailList[1].avatar" alt="avatar" />
                        </router-link>
                        <div class="card-media-body text-truncate">
                          <p class="fw-semibold text-truncate">
                            <a :href="itemDetailList[1].path" class="text-black">{{ itemDetailList[1].title }}</a>
                          </p>
                          <p class="small">{{ itemDetailHistoryList[0].subTitle }}</p>
                        </div>
                      </div>
                      <!-- end card -->
                    </div>
                    <!-- end item-detail-tab-wrap -->
                  </div>
                  <!-- end tab-pane -->
                  <!-- end tab-pane -->
                  <div class="tab-pane fade" id="history" role="tabpanel" aria-labelledby="history-tab">
                    <div class="item-detail-tab-wrap">
                      <div class="card-media card-media-s2 mb-3" v-for="item in itemDetailHistoryList" :key="item.id">
                        <router-link :to="item.path" class="card-media-img flex-shrink-0 d-block">
                          <img :src="item.avatar" alt="avatar" />
                        </router-link>
                        <div class="card-media-body text-truncate">
                          <p class="fw-semibold text-black text-truncate">{{ item.title }}</p>
                          <p class="small text-truncate">{{ item.subTitle }}</p>
                        </div>
                      </div>
                      <!-- end card -->
                    </div>
                    <!-- end item-detail-tab-wrap -->
                  </div>
                  <div class="tab-pane fade" id="detail" role="tabpanel" aria-labelledby="detail-tab">
                    <div class="item-detail-tab-wrap">
                      <ul>
                        <li>
                          <p>Contract Address : {{ nftCA }}</p>
                        </li>
                        <li>
                          <p>Token ID : {{ id }} SSF</p>
                        </li>
                        <li>
                          <p>Price : {{ price }} SSF</p>
                        </li>
                        <li><p>Token Standard : ERC - 721</p></li>
                        <li><p>BlockChain : SSAFY TOKEN</p></li>
                      </ul>
                      <!-- end card -->
                    </div>
                    <!-- end item-detail-tab-wrap -->
                  </div>
                  <!-- end tab-pane -->
                </div>
              </div>
            </div>
            <!-- end item-detail-content -->
          </div>
          <!-- end col -->
          <div class="col-lg-6">
            <div class="item-detail-content mt-4 mt-lg-0">
              <div class="d-flex flex-row">
                <h1 class="item-detail-title mb-2">{{ title }}</h1>
              </div>
              <div class="item-detail-meta d-flex flex-wrap align-items-center mb-3">
                <span class="item-detail-text-meta">{{ onsale }}</span>
                <span class="dot-separeted"></span>
                <span class="item-detail-text-meta">{{ price }}</span>
                <span class="dot-separeted"></span>
                <span class="item-detail-text-meta" v-html="metaTextThree"></span>
              </div>
              <p class="item-detail-text mb-4">{{ content }}</p>
              <div class="item-credits">
                <div class="row g-4">
                  <div class="col-xl-6" v-for="item in itemDetailList" :key="item.id">
                    <div class="card-media card-media-s1">
                      <router-link :to="item.path" class="card-media-img flex-shrink-0 d-block">
                        <img :src="item.avatar" alt="avatar" />
                      </router-link>
                      <div class="card-media-body">
                        <router-link :to="item.path" class="fw-semibold">@{{ item.title }}</router-link>
                        <p class="fw-medium small">{{ item.subTitle }}</p>
                      </div>
                    </div>
                    <!-- end card -->
                  </div>
                  <!-- end col-->
                  <!-- end col-->
                </div>
                <!-- end row -->
              </div>
              <!-- end row -->
              <div class="item-detail-btns mt-4">
                <ul class="btns-group d-flex">
                  <li class="flex-grow-1">
                    <DetailPurchase :product="product"></DetailPurchase>
                  </li>
                </ul>
              </div>
              <!-- end item-detail-btns -->
            </div>
            <!-- end item-detail-content -->
          </div>
          <!-- end col -->
        </div>
        <!-- end row -->
      </div>
      <!-- .container -->
    </section>
    <!-- end item-detail-section -->
    <!-- Related product -->
    <RelatedProduct></RelatedProduct>
    <!-- Footer  -->
    <Footer classname="bg-black on-dark"></Footer>
    <!-- Modal -->
    <div class="modal fade" id="placeBidModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">{{ SectionData.placeBidModal.title }}</h4>
            <button type="button" class="btn-close icon-btn" data-bs-dismiss="modal" aria-label="Close">
              <em class="ni ni-cross"></em>
            </button>
          </div>
          <!-- end modal-header -->
          <div class="modal-body">
            <p class="mb-3" v-html="SectionData.placeBidModal.content"></p>
            <div class="mb-3">
              <label class="form-label">{{ SectionData.placeBidModal.labelText }}</label>
              <input type="text" class="form-control form-control-s1" placeholder="Enter bid" />
            </div>
            <div class="mb-3">
              <label class="form-label" v-html="SectionData.placeBidModal.labelTextTwo"></label>
              <input type="text" class="form-control form-control-s1" value="1" />
            </div>
            <ul class="total-bid-list mb-4">
              <li v-for="(list, i) in SectionData.placeBidModal.totalBidList" :key="i">
                <span>{{ list.title }}</span> <span>{{ list.price }}</span>
              </li>
            </ul>
            <a :href="SectionData.placeBidModal.btnLink" class="btn btn-dark d-block">{{ SectionData.placeBidModal.btnText }}</a>
          </div>
          <!-- end modal-body -->
        </div>
        <!-- end modal-content -->
      </div>
      <!-- end modal-dialog -->
    </div>
    <!-- end modal-->
  </div>
  <!-- end page-wrap -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import axios from "axios";
import SectionData from "@/store/store.js";
import { mapState } from "vuex";
import DetailPurchase from "@/components/common/DetailPurchase";

export default {
  name: "ProductDetail",
  props: ["product"],
  data() {
    return {
      SectionData,
      id: this.$route.params.id,
      nftCA: null,
      title: "작품 이름", // 작품이름
      imgLg: "https://img1.daumcdn.net/thumb/R1280x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/42iI/image/fgznKzwTj38hXdylF03JXH2Gv5E",
      // imgLg: "@/images/favicon.png",
      onsale: "Not for sale", // 판매 중 인지
      price: "500 editions",
      metaTextThree: "500 editions",
      content: 'Digital-only* "CB Galaxy" style Coke Boys LA sneakers wearable in the Decentraland metaverse',
      ownerlist: [
        {
          id: 1,
          title: "French Montana",
          subTitle: "450 editions not for sale",
          avatar: require("@/images/thumb/avatar-4.jpg"),
          path: "/author",
        },
      ],
      itemDetailHistoryList: [
        // {
        //   id: 1,
        //   title: "Listed 1 edition for 0.35 ETH",
        //   subTitle: "by Medicom 21 hours ago",
        //   avatar: require("@/images/thumb/avatar-4.jpg"),
        //   path: "/author",
        // },
        // {
        //   id: 2,
        //   title: "Purchased 1 edition for 0.1 ETH",
        //   subTitle: "by Medicom 21 hours ago",
        //   avatar: require("@/images/thumb/avatar-5.jpg"),
        //   path: "/author",
        // },
        // {
        //   id: 3,
        //   title: "Purchased 1 edition for 0.1 ETH",
        //   subTitle: "by French Montana 23/09/2021",
        //   avatar: require("@/images/thumb/avatar-6.jpg"),
        //   path: "/author",
        // },
      ],
      itemDetailList: [
        {
          id: 1,
          title: "작가 이름",
          subTitle: "Creator",
          avatar: require("@/images/thumb/avatar.jpg"),
          path: "/author",
        },
        {
          id: 2,
          title: "보유자 이름",
          subTitle: "Collection",
          avatar: require("@/images/thumb/avatar-2.jpg"),
          path: "/author",
        },
      ],
    };
  },
  components: {
    Purchase,
  },
  created() {
    this.getItmeDetail();
  },
  methods: {
    getItmeDetail() {
      axios({
        method: "GET",
        url: `${SERVER_URL}/api/nft/detail/${this.$route.params.id}`,
        headers: {
          // Authorization: token,
          Authorization: this.authToken,
        },
      }).then((res) => {
        console.log(res.data, "eeeeeeeeeeeeeeee");
        this.itemDetailList[0].title = res.data.AuthorMember.memberId;
        this.itemDetailList[1].title = res.data.OwnerMember.memberId;
        // this.itemDetailList[0].avatar = res.data.AuthorMember.profile
        this.itemDetailList[0].avatar = "https://img1.daumcdn.net/thumb/R1280x0.fjpg/?fname=http://t1.daumcdn.net/brunch/service/user/42iI/image/fgznKzwTj38hXdylF03JXH2Gv5E";
        this.title = res.data.nft.nftName;
        this.content = res.data.nft.nftDescription;
        this.nftCA = res.data.nft.nftContractAddress;
        if (res.data.marketList.length >= 1) {
          this.price = res.data.marketList[res.data.marketList.length - 1].price;
        }
        if (res.data.nft.onsale) {
          this.onsale = "On Sale";
        } else {
          this.onsale = "Not for Sale";
        }
        for (var i = 0; i < res.data.marketList.length; i++) {
          if (i === 0) {
            this.itemDetailHistoryList.push({
              id: i,
              title: `Listed 1 edition for ${res.data.marketList[i].price} SSF`,
              subTitle: `By Unique at ${res.data.marketList[i].endTime.date.year}/${res.data.marketList[i].endTime.date.month}/${res.data.marketList[i].endTime.date.day}`,
              avatar: require("@/images/thumb/avatar-4.jpg"),
              path: "/author",
              // avatar: res.data.buyerList[i].profileImageUrl
            });
          } else {
            this.itemDetailHistoryList.push({
              id: i,
              title: `Purchased 1 edition for ${res.data.marketList[i].price} SSF`,
              subTitle: `By Unique at ${res.data.marketList[i].endTime.date.year}/${res.data.marketList[i].endTime.date.month}/${res.data.marketList[i].endTime.date.day}`,
              avatar: require("@/images/thumb/avatar-4.jpg"),
              path: "/author",
              // avatar: res.data.buyerList[i].profileImageUrl
            });
          }
        }
        // this.itemDetailHistoryList = res.data.marketList
      });
    },
  },
  computed: {
    ...mapState(["authToken"]),
  },
};
</script>
