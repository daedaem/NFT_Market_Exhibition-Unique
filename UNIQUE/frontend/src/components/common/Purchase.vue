<template>
  <div>
    <div>{{product.marketId}}</div>
    <button type="button" data-bs-toggle="modal" data-bs-target="#purchaseNFTModal" class="btn btn-sm btn-dark d-block">Purchase</button>
    <div class="modal fade" id="purchaseNFTModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <div>{{product.marketId}}</div>
            <h4 class="modal-title">Complete checkout</h4>
            <button type="button" class="btn-close icon-btn" data-bs-dismiss="modal" aria-label="Close">
              <em class="ni ni-cross"></em>
            </button>
          </div>
          <!-- end modal-header -->
          <div class="modal-body">
            <p class="mb-3"></p>
            <div class="mb-3">
              <!-- <label class="form-label">잔액조회</label> -->
              <!-- <input type="text" class="form-control form-control-s1" v-model="authorPrivateKey" placeholder="please typing your Private Key" /> -->
            </div>
            <div class="mb-3">
              <label class="form-label">아아</label>
              <input type="text" class="form-control form-control-s1" v-model="authorPrivateKey" placeholder="please typing your Private Key" />
            </div>
            <div class="mb-3">
              <label class="form-label">개인키 입력</label>
              <input type="text" class="form-control form-control-s1" v-model="authorPrivateKey" placeholder="please typing your Private Key" />
            </div>
            <button class="btn btn-dark d-block" @click="purchaseNFT" data-bs-target="#purchaseNFTModal" data-bs-toggle="modal">Confirm</button>
            <!-- <a :href="SectionData.purchaseNFTModal.btnLink" class="btn btn-dark d-block" @click="purchaseNFT"></a> -->
          </div>
          <!-- end modal-body -->
        </div>
        <!-- end modal-content -->
      </div>
      <!-- end modal-dialog -->
    </div>
    <!-- end firstmodal -->
    <!-- start second modal -->
    <div class="modal fade" id="purchaseNFTModal" tabindex="-1" aria-hidden="true" v-if="authorPrivateKey">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header d-flex flex-column">
            <h4 class="modal-title d-flex justify-content-center">You created {{}}</h4>

            <button type="button" class="btn-close icon-btn" data-bs-dismiss="modal" aria-label="Close">
              <em class="ni ni-cross"></em>
            </button>
            <p class="d-flex justify-content-center">Wow! You just create your NFT</p>
          </div>
          <!-- end second modal-header -->
          <div class="modal-body d-flex flex-column">
            <div class="mb-3 d-flex justify-content-center">
              <img src="" class="justify-content-center" alt="" />
              <!-- <img src=`${}` alt="" /> -->
            </div>
            <!-- <a :href="SectionData.purchaseNFTModal.btnLink" class="btn btn-dark d-block" @click="purchaseNFT"></a> -->
          </div>
          <!-- end second modal-body -->
        </div>
        <!-- end second modal-content -->
      </div>
      <!-- end second modal-dialog -->
    </div>
  </div>
</template>

<script>
const SERVER_URL = process.env.VUE_APP_SERVER_URL;

import axios from "axios";
import Web3 from "web3";
import SectionData from "@/store/store.js";
import getAddressFrom from "../../utils/AddressExtractor";
// import ABI from "../../common/ABI";
import SsafyToken from "../../../smart-contracts/build/contracts/SsafyToken.json";
import SsafyNFT from "../../../smart-contracts/build/contracts/SsafyNFT.json";
import SaleFactory from "../../../smart-contracts/build/contracts/SaleFactory.json";
import Sale from "../../../smart-contracts/build/contracts/Sale.json";
let TOKEN_ABI = SsafyToken.abi;
let TOKEN_CA = "0x6c927304104cdaa5a8b3691e0ade8a3ded41a333";
// let NFT_ABI = SsafyNFT.abi;
// let NFT_CA = SsafyNFT.networks["202112031219"].address;
// let SALE_FACTORY_ABI = SaleFactory.abi;
// let SALE_FACTORY_CA = SaleFactory.networks["202112031219"].address;
let SALE_ABI = Sale.abi;
// 네트워크 연결
const GANACHE_SERVER_URL = process.env.GANACHE_SERVER_URL;
let web3 = new Web3(new Web3.providers.HttpProvider(GANACHE_SERVER_URL));
export default {
  name: "Purchase",
  data() {
    return {
      authorPrivateKey: null,
      marketContractAddress: this.product.marketContractAddress,
      price: this.product.price,
      nftOwnerAddress: this.product.nftOwnerAddress,
      nftTokenId: this.product.nftTokenId,
    };
  },
  props: ["product"],
  methods: {
    async check() {
      const ssafyToken1 = await new web3.eth.Contract(TOKEN_ABI, TOKEN_CA);

      const getbalance2 = await ssafyToken1.methods.balanceOf("0x96c6d8B4DF69AfBA140cA3057c3A16907b6a7441").call();

      console.log(getbalance2, "맞나이거");
      console.log(TOKEN_CA, "토큰");
    },

    async purchaseNFT() {
      console.log(this.product.marketId)
      const checkPubKey = await getAddressFrom("0x" + this.authorPrivateKey);
      const temp = await web3.eth.getAccounts();
      const myAccount = temp[0];
      if (checkPubKey === myAccount) {
        // 싸피토큰컨트랙트 인스턴스
        const tokenContract = await new web3.eth.Contract(TOKEN_ABI, TOKEN_CA);
        // 내 토큰 잔액확인
        const myTokenBalance = await tokenContract.methods.balanceOf("내계좌");
        // 만약 계좌잔액이 현재 구매가 보다 작다면 실행 안됨
        if (myTokenBalance > this.price) {
          // 만든 세일에 대한 CA, 판매자,
          const resultOfTransferToken = await tokenContract.methods.approve(marketContractAddress, this.price);
          //
          // 현재 이아이템 거래 컨트랙트한테 토큰 권한 부여
          // 구매입장
          tokenContract.methods.approve(marketContractAddress, this.price, { from: 구매자 });
          // purchase함수 호출을 위한 컨트랙트 인스턴스 생성
          const createSaleInstance = await new web3.eth.Contract(SALE_ABI, marketContractAddress);
          const transactionResult = await createSaleInstance.methods.purchase();
          console.log(transactionResult, "전송결과는?");
        } else {
          alert("please, check your account's balance");
          // 새로고침
          this.$router.go();
        }
        // 내 계좌에서 컨트랙트에다가 토큰 전송 권한 부여
      }
      // 프라이빗키가 일치하지 않으면
      else {
        alert("Please, check your private key");
        this.authorPrivateKey = null;
      }
    },
  },
  created() {
    this.check();
    // console.log(getbalance);
    // console.log(this.product);
    // this.marketContractAddress = this.product.marketContractAddress;
    // this.price = this.product.price;
    // this.nftOwnerAddress = this.product.nftOwnerAddress;
    // this.nftTokenId = this.product.nftTokenId;
  },
};
</script>

<style></style>
