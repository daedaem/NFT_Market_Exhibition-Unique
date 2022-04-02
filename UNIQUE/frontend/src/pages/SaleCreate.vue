<template>
  <div class="page-wrap">
    <!-- header  -->
    <header class="header-section has-header-main bg-pattern-3">
      <!-- Header main -->
      <HeaderMain></HeaderMain>
    </header>
    <!-- create -->
    <section class="create-section section-space-b pt-4 pt-md-5 mt-md-4">
      <div class="d-flex SaleCreateSection">
        <div class="container d-flex justify-content-end">
          <div class="row justify-content-center align-self-start">
            <div class="col-lg-8">
              <div class="section-head-sm">
                <a :href="`/detail/${$route.params.id}`" class="btn-link fw-semibold"><em class="ni ni-arrow-left"></em> Back</a>
                <h1 class="mt-2">List item for sale</h1>
              </div>
            </div>
            <!-- end col -->
            <div class="col-lg-8">
              <form action="#" class="form-create mb-5 mb-lg-0">
                <div class="form-item mb-4">
                  <h5 class="mb-3">Price</h5>
                  <div class="mb-4 d-flex">
                    <div class="col-4 pe-1">
                      <select class="form-select" disabled aria-label="Default select example">
                        <option selected>&#8383;&ensp;SSF</option>
                        <option value="1">ETH</option>
                      </select>
                    </div>
                    <div class="col">
                      <input type="price" class="form-control form-control-s1" v-model="form.price" placeholder="Amount" />
                    </div>
                  </div>
                </div>
                <!-- end form-item -->
                <div class="form-item mb-4">
                  <div class="mb-4">
                    <label class="mb-2 form-label">Duration</label>
                    <Datepicker v-model="date" placeholder="시작과 종료날짜를 모두 입력해주세요" range />
                  </div>
                </div>
                <!-- end form-item -->
                <button type="button" data-bs-toggle="modal" data-bs-target="#saleCreateNftModal" @click="checkInputData" class="btn btn-dark d-block">Complete listing</button>
              </form>
            </div>
            <!-- endn col -->
          </div>
          <!-- row-->
        </div>
        <div class="container">
          <div class="card card-full" style="max-width: 500px; height: auto">
            <div class="card-image">
              <img src="https://images.pexels.com/photos/1328876/pexels-photo-1328876.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500g" class="card-img-top" style="height: auto" alt="art image" />
            </div>
            <div class="card-body p-4">
              <h5 class="card-title text-truncate mb-0">제목</h5>
              <!-- <h5 class="card-title text-truncate mb-0">{{ product.nft.title }}</h5> -->
              <div class="card-author mb-1 d-flex align-items-center">
                <span class="me-1 card-author-by">By</span>
                <div class="custom-tooltip-wrap">
                  <div class="custom-tooltip author-link">작가</div>
                  <!-- end dropdown-menu -->
                </div>
                <!-- end custom-tooltip-wrap -->
              </div>
              <!-- end card-author -->
              <div class="card-price-wrap d-flex align-items-center justify-content-sm-between mb-3">
                <div class="me-5 me-sm-2">
                  <span class="card-price-title">NFT</span>
                  <span class="card-price-number">type</span>
                </div>
                <div class="text-sm-end">
                  <span class="card-price-title">NFT Price</span>
                  <span class="card-price-number">&dollar; 123</span>
                </div>
              </div>
              <!-- end card-price-wrap -->
            </div>
          </div>
        </div>
      </div>
      <!--  -->
    </section>
    <!-- create-section -->
    <!-- Footer  -->
    <Footer classname="bg-dark on-dark"></Footer>
    <!-- first Modal -->
    <!-- 가격과 duraition을 지정해야만 프라이빗키를 입력하는 모달이 뜸 -->

    <div v-if="this.date && this.form.price" class="modal fade" id="saleCreateNftModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">Confirm Your list</h4>
            <button type="button" class="btn-close icon-btn" data-bs-dismiss="modal" aria-label="Close">
              <em class="ni ni-cross"></em>
            </button>
          </div>
          <!-- end modal-header -->
          <div class="modal-body">
            <p class="mb-3">Before list item for sale, Please <strong>input your Private Key</strong><br /></p>
            <div class="mb-3">
              <label class="form-label">{{ SectionData.createNftModal.labelText }}</label>
              <input type="text" class="form-control form-control-s1" v-model="authorPrivateKey" placeholder="please typing your Private Key" />
            </div>
            <button class="btn btn-dark d-block" @click="submitSaleCreateNFT" data-bs-target="#saleCreateNftModal2" data-bs-toggle="modal">Confirm</button>
            <!-- <a :href="SectionData.createNftModal.btnLink" class="btn btn-dark d-block" @click="submitCreateNFT"></a> -->
          </div>
          <!-- end modal-body -->
        </div>
        <!-- end modal-content -->
      </div>
      <!-- end modal-dialog -->
    </div>
    <!-- end firstmodal -->
    <!-- start second modal -->
  </div>
  <!-- end page-wrap -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import { ref, onMounted, watch } from "vue";
import axios from "axios";
import Web3 from "web3";
import SectionData from "@/store/store.js";
import getAddressFrom from "../utils/AddressExtractor";
// import ABI from "../../common/ABI";
import SsafyToken from "../../smart-contracts/build/contracts/SsafyToken.json";
import SsafyNFT from "../../smart-contracts/build/contracts/SsafyNFT.json";
import SaleFactory from "../../smart-contracts/build/contracts/SaleFactory.json";
import Sale from "../../smart-contracts/build/contracts/Sale.json";
// const abi = ABI.CONTRACT_ABI.NFT_ABI;
let TOKEN_ABI = SsafyToken.abi;
let TOKEN_CA = SsafyToken.networks["1337"].address;
let NFT_ABI = SsafyNFT.abi;
let NFT_CA = SsafyNFT.networks["1337"].address;
let SALE_FACTORY_ABI = SaleFactory.abi;
let SALE_FACTORY_CA = SaleFactory.networks["1337"].address;
let SALE_ABI = Sale.abi;
// let SALE_CA = Sale.networks["1337"].address;

// 네트워크 연결
let web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:8545"));
// let webs = new Web3("http://127.0.0.1:7545");

export default {
  name: "SaleCreate",
  setup() {
    // 반응형 데이터 초기화
    const date = ref(); //
    onMounted(() => {
      const startDate = new Date();
      const endDate = new Date(new Date().setDate(startDate.getDate() + 7));
      date.value = [startDate, endDate];
    });
    // const getItemDetail= () => {
    //   axios.get
    // }
    // date값이 변할 때마다 실행되는 함수
    watch(date, () => {
      if (date.value) {
        let startTime = date.value[0];
        let endTime = date.value[1];
        console.log(startTime);
        let a = Date.parse(startTime);
      }
    });
    return {
      date,
      // form,
      // checkInputData,
    };
  },
  data() {
    return {
      // myAccount: null,
      thisItemData: [],
      id: "",
      SectionData,
      form: { price: "" },
      authorPrivateKey: null,
      newtokenId: null,
    };
  },
  methods: {
    checkInputData() {
      console.log(this.date, this.form.price);
      if (this.date.length < 2 || !this.form.price) {
        alert("Please Input information for sales registration");
        this.date = ref();
        this.form.price = null;
      } else {
        console.log("통과");
      }
      // if (!this.date.value && !this.form.price) {
      //   console.log("뭐냐");
      // } else {
      //   console.log("나이스");
      // }
    },
    async submitSaleCreateNFT() {
      const checkPubKey = await getAddressFrom("0x" + this.authorPrivateKey);
      const temp = await web3.eth.getAccounts();
      const myAccount = temp[0];
      // 공개키가 유효하다면 정보 등록
      if (checkPubKey === myAccount) {
        // 싸피토큰 확인
        // const tokenContract = await new web3.eth.Contract(TOKEN_ABI, TOKEN_CA);
        // 컨트랙트한테 권한 부여
        // 구매입장
        // tokenContract.methods.approve("createsaleCA에", "가격, {from: 구매자}");

        // salefactory 계약 인스턴스 호출
        const saleFactoryContract = await new web3.eth.Contract(SALE_FACTORY_ABI, SALE_FACTORY_CA);
        // createsale 메소드 호출
        const saleContractInstance = await saleFactoryContract.methods.createSale(this.$route.params.id, this.form.price, startTime, endTime, TOKEN_CA, "현재 NFTADDRESS");
        // 호출 후 CA 저장 후 백엔드에 보내기
        const saleCA = saleContractInstance.logs[0].args._saleContract;
        // 백엔드에 해당 sale contract adress 저장해야함

        // 해당 ssafy 토큰 컨트랙트의 권한 승인 부여
        // 1. 해당 nft contract adress백엔드에서 호출해오기
        // 2.권한 부여
        const NFTContractInstance = await new web3.eth.Contract(NFT_ABI, NFT_CA);
        const resultOfRegisterApprove = await NFTContractInstance.methods.approve(saleCA, 토큰아이디);
        console.log(resultOfRegisterApprove);
      }
      // 프라이빗 키와 다르다면
      else {
        alert("Please check your private key");
      }
    },
  },
  mounted() {
    // async function loadMyAccount() {
    //   await web3.eth.getAccounts().then((res) => (this.myAccount = res[0]));
    //   // console.log(myAccounts[0]);
    //   await console.log(this.myAccount);
    //   // this.myAccount = await myAccounts[0];
    //   // console.log(myAccounts, "내계좌주소");
    //   // console.log(mintResult, "결과 확인");
    //   // 함수결과값
    //   // 메타 url:
    // }
    // loadMyAccount();
    // async function hello() {
    //   const result = await new Web3.eth.Contract(abi, CA);
    //   console.log(result);
    //   return result;
    // }
    // hello().then((res) => console.log(res, "뒤"));
    // async function hello2() {
    //   const results = await Web3.eth.Contract(ABI, CA);
    //   console.log(results);
    //   return results;
    // }
    // hello2().then((res) => console.log(res, "뒤2"));
    // 현재 작가는 해당 아이디 이후 변경예정
    // console.log(web3.eth.getAccounts(), "요기");
    // function getAccount() {
    // web3.eth.getAccounts(function)}
  },
};
</script>
<style scoped>
/* .container {
  max-width: 800px;
} */
@media (min-width: 996px) {
  .SaleCreateSection {
    /* max-width: 1700px; */
    margin: auto;
  }
}
@media (max-width: 996px) {
  .SaleCreateSection {
    max-width: 700px;
    margin: auto;
  }
}
@media (max-width: 764px) {
  .SaleCreateSection {
    flex-wrap: wrap-reverse;
  }
  .container {
    margin-bottom: 30px;
  }
}
.container > container-imagebox {
  max-width: 400px;
}
/* .card-img-top {
} */
.card-image {
  border-bottom: 2px solid #ddd;
}
</style>
