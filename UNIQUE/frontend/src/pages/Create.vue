<template>
  <div class="page-wrap">
    <!-- header  -->
    <header class="header-section has-header-main bg-pattern-3">
      <!-- Header main -->
      <HeaderMain></HeaderMain>
    </header>
    <!-- create -->
    <section class="create-section section-space-b pt-4 pt-md-5 mt-md-4">
      <div class="container">
        <div class="row justify-content-center">
          <div class="col-lg-8">
            <div class="section-head-sm">
              <!-- <router-link :to="SectionData.createData.path" class="btn-link fw-semibold"><em class="ni ni-arrow-left"></em> {{ SectionData.createData.btnText }}</router-link> -->
              <router-link :to="{ name: 'Home' }" class="btn-link fw-semibold"><em class="ni ni-arrow-left"></em> Home </router-link>
              <h1 class="mt-2">{{ SectionData.createData.title }}</h1>
            </div>
          </div>
          <!-- end col -->
          <div class="col-lg-8">
            <form action="#" class="form-create mb-5 mb-lg-0">
              <div class="form-item mb-4">
                <h5 class="mb-3">Upload file</h5>
                <div class="file-upload-wrap">
                  <p class="file-name mb-4" id="file-name">PNG, GIF, WEBP, MP4 or MP3. Max 100mb.</p>
                  <input id="file-upload" class="file-upload-input" data-target="file-name" type="file" enctype="multipart/form-data" @change="selectFile" hidden />
                  <label for="file-upload" class="input-label btn btn-dark">Choose File</label>
                </div>
              </div>
              <!-- end form-item -->
              <!-- 작품 제목 및 설명 입력  -->
              <div class="form-item mb-4">
                <div class="mb-4">
                  <label class="mb-2 form-label">Title</label>
                  <input type="text" class="form-control form-control-s1" v-model="form.nftName" placeholder="e. g. Redeemable T-Shirt with logo" />
                </div>
                <div class="mb-4">
                  <label class="mb-2 form-label">Description</label>
                  <textarea name="message" class="form-control form-control-s1" v-model="form.nftDescription" placeholder="e. g. After purchasing you’ll be able to get the real T-Shirt"></textarea>
                </div>
              </div>
              <!-- end form-item -->
              <button type="button" data-bs-toggle="modal" data-bs-target="#createNftModal" class="btn btn-dark d-block" @click="checkInputData">Create Item</button>
            </form>
          </div>
          <!-- endn col -->
        </div>
        <!-- row-->
      </div>
      <!-- container -->
    </section>
    <!-- create-section -->
    <!-- Footer  -->
    <Footer classname="bg-black on-dark"></Footer>
    <!-- first Modal -->
    <!-- 등록하려는 아이템의 제목, 설명, 파일이 모두 있을 때 다음 모달로 -->
    <div v-if="this.form.nftDescription && this.form.nftName && this.form.file" class="modal fade" id="createNftModal" tabindex="-1" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">{{ SectionData.createNftModal.title }}</h4>
            <button type="button" class="btn-close icon-btn" data-bs-dismiss="modal" aria-label="Close">
              <em class="ni ni-cross"></em>
            </button>
          </div>
          <!-- end modal-header -->
          <div class="modal-body">
            <p class="mb-3" v-html="SectionData.createNftModal.content"></p>
            <div class="mb-3">
              <label class="form-label">{{ SectionData.createNftModal.labelText }}</label>
              <input type="text" class="form-control form-control-s1" v-model="authorPrivateKey" placeholder="please typing your Private Key" />
            </div>
            <button class="btn btn-dark d-block" @click="submitCreateNFT" data-bs-target="#createNftModal2" data-bs-toggle="modal">Confirm</button>
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
    <div class="modal fade" id="createNftModal2" tabindex="-1" aria-hidden="true" v-if="authorPrivateKey && checkInputData === true">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
          <div class="modal-header d-flex flex-column">
            <h4 class="modal-title d-flex justify-content-center">You created {{ form.nftName }}</h4>
            <button type="button" class="btn-close icon-btn" data-bs-dismiss="modal" aria-label="Close">
              <em class="ni ni-cross"></em>
            </button>
            <p class="d-flex justify-content-center">Wow! You just create your NFT</p>
          </div>
          <!-- end second modal-header -->
          <div class="modal-body d-flex flex-column">
            <div class="mb-3 d-flex justify-content-center">
              <img src="../images/thumb/nft-full.jpg" class="justify-content-center" alt="" />
              <!-- <img src=`${}` alt="" /> -->
            </div>
            <!-- <a :href="SectionData.createNftModal.btnLink" class="btn btn-dark d-block" @click="submitCreateNFT"></a> -->
          </div>
          <!-- end second modal-body -->
        </div>
        <!-- end second modal-content -->
      </div>
      <!-- end second modal-dialog -->
    </div>
  </div>
  <!-- end page-wrap -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import axios from "axios";
import Web3 from "web3";
import SectionData from "@/store/store.js";
import getAddressFrom from "../utils/AddressExtractor";
// import ABI from "../../common/ABI";
// const abi = ABI.CONTRACT_ABI.NFT_ABI;
import SsafyNFT from "../../smart-contracts/build/contracts/SsafyNFT.json";
import { mapState } from "vuex";

const NFT_ABI = SsafyNFT.abi;
// const NFT_CA = "0x6c927304104cdaa5a8b3691e0ade8a3ded41a333";
const NFT_CA = SsafyNFT.networks["202112031219"].address;

// 네트워크 연결
const GANACHE_SERVER_URL = "http://20.196.209.2:8545";

let web3 = new Web3(new Web3.providers.HttpProvider(GANACHE_SERVER_URL)); // let Web3 = require("web3");
// let web2 = web3.setProvider(new Web3.providers.HttpProvider(GANACHE_SERVER_URL));
// console.log(web3, "web3");
// console.log(web2, "web2");
// console.log(web3.currentProvider, "?");
// let web3 = new Web3();
// web3.setProvider(new web3.providers.HttpProvider("http://127.0.0.1:8545"));
// let webs = new Web3("http://127.0.0.1:7545");

// let pollWeb3 = state => {

export default {
  name: "Create",
  data() {
    return {
      // myAccount: null,
      SectionData,
      selected: "Select Collection",
      options: ["Select Collection", "Abstraction", "Patternlicious", "Skecthify", "Cartoonism", "Virtuland", "Papercut"],
      // d
      form: {
        nftWorkUri: null,
        nftType: null,
        nftName: null,
        nftAuthorName: this.username,
        nftDescription: null,
        file: null,
      },
      authorPrivateKey: null,
      newtokenId: null,

      // authorPublicKey: null,
    };
  },
  methods: {
    selectFile(data) {
      this.form.file = data.target.files[0];
    },
    checkInputData() {
      // console.log(authToken);
      // console.log(this.$store.state.myAddress);
      // console.log(myAddress);
      // console.log(this.myAddress);
      // log(NFT_CA);myAddress
      // console.log(this.date[0], this.date[1], this.form.price);
      if (!this.form.file || !this.form.nftName || !this.form.nftDescription) {
        alert("Please Input information for Create your item");
      } else {
        console.log("통과");
        return true;
      }
    },

    async submitCreateNFT() {
      console.log(GANACHE_SERVER_URL, "GANACHE_SERVER_URL");
      console.log(SERVER_URL, "SERVER_URL");
      /**
       * PJT Ⅱ - 과제 1: 작품 등록 및 NFT 생성
       * Req.1-F1 작품 등록 화면 및 등록 요청
       *
       * 구현 예)
       * 1. 아이템 업로드 및 모든 정보가 입력되면 등록 승인을 위한 모달창이 열립니다.
       * 2. 해당 모달 창에서 개인키를 입력하면 getAddressFrom() 함수를 통해 공개키가 반환되며, 공개키가 유효한 경우 작가명, 제목,
       * 아이템 소개 정보를 인코딩하여 formData에 아이템의 이미지와 함께 append 합니다.
       * 3. 만들어진 formData는 아이템 등록 API를 통해 전달되고, 정상적으로 반영된 경우 이미지의 링크와 item ID를 반환 받습니다.
       * 4. 이후 공개키와 생성된 item ID, 이미지 링크를 이용해 NFT 생성을 위한 함수를 호출합니다.
       * 정상적으로 트랜잭션이 완결된 후 token Id가 반환됩니다.
       * 5. 정상 동작 시 token Id와 owner_address를 백엔드에 업데이트 요청합니다.
       */
      // url:해시된, nft:이름, 작성자 일련번호
      console.log(typeof this.authorPrivateKey);
      const checkPubKey = await getAddressFrom(this.authorPrivateKey);
      // 내계좌 조회 1.
      console.log(typeof checkPubKey, "체크퍼브키");
      // 서버 배포 후
      const myAccount = await this.myAddress;
      console.log(typeof myAccount, "되나");

      // 내계좌 조회 2번
      // var sender = web3.eth.accounts.privateKetToAccount("0x" + 프라이빗키);
      // web3.eth.accounts.wallet.add(sender);
      // web3.eth.defaultAccount = sender.address;
      // var senderAddress = web3.eth.defaultAccount;
      // console.log(myAccount, "내계좌입니다.");

      // 공개키가 유효하다면 정보 등록
      if (checkPubKey === myAccount) {
        // console.log("일치합니다.");
        let data = new FormData();
        data.append("nftAuthorName", this.username);
        data.append("nftName", this.form.nftName);
        data.append("nftDescription", this.form.nftDescription);
        data.append("file", this.form.file);
        const createIPFS = await axios({
          method: "POST",
          url: `${SERVER_URL}/api/file`,
          data: data,
          headers: {
            // Authorization: token,
            Authorization: this.$store.state.authToken,
            "Content-Type": "multipart/form-data",
          },
        });
        const IPFSresult = createIPFS.data.nftMetadataUri;
        // console.log(IPFSresult, "ipfs결과");
        const NFTcreateContractInstance = await new web3.eth.Contract(NFT_ABI, NFT_CA);
        // console.log(myAccount);
        console.log(NFTcreateContractInstance, "NFTcreateContractInstance");
        // 1번째 방법 state 변경 안시키는 call함수 호출
        // const results = await NFTcreateContractInstance.methods.current().call({ from: myAccount });
        // console.log(results, "results");
        // 2번째 트랜잭션하는 함수 호출
        let createNFTResponse = await NFTcreateContractInstance.methods.create(myAccount, IPFSresult);
        // let newtokenId = await NFTcreateContractInstance.methods.current().call();
        // console.log(newtokenId, "newtokenId");
        let web2 = web3.setProvider(new Web3.providers.HttpProvider(GANACHE_SERVER_URL));
        console.log(web3, "web3");
        console.log(web2, "web2");
        console.log(web3.currentProvider, "?");
        console.log(createNFTResponse, "createNFTResponse");
        const contractEncodedMethod = createNFTResponse.encodeABI();
        console.log(contractEncodedMethod, "contractEncodedMethod");
        // 서명
        // 원래는 서명하시겠습니까 뜨는게?!

        // const gasEstimate = await createNFTResponse.estimateGas({ from: myAccount });
        // console.log(gasEstimate, "가스 측정");
        const rawTx = {
          from: myAccount,
          to: NFT_CA,
          // gas: gasEstimate,
          gas: 500000,
          data: contractEncodedMethod,
        };
        web2 = web3.setProvider(new Web3.providers.HttpProvider(GANACHE_SERVER_URL));
        console.log(web3, "web3");
        console.log(web2, "web2");
        console.log(web3.currentProvider, "?");
        console.log(rawTx, "rawTx");
        //
        // const walletAccount = web3.eth.accounts.privateKeyToAccount(this.authorPrivateKey);
        // console.log(walletAccount, "walletAccount");
        // console.log("walletAccount" + walletAccount);
        // const gasPrice = await web3.eth.getGasPrice();
        // console.log(gasPrice, "gasPrice");
        const signedTx = await web3.eth.accounts.signTransaction(rawTx, this.authorPrivateKey);
        console.log(signedTx, "signedTx");
        // this.createSaleCAss = await web3.eth.getTransactionReceipt(tran.transactionHash);
        // console.log(createSaleCAss, "createSaleCAss?");
        if (signedTx == null) {
          console.log("TransactionSignFailedException");
        } else {
          let tran = await web3.eth.sendSignedTransaction(signedTx.rawTransaction);
          // .on("transactionHash", (txhash) => {
          //   console.log("Tx Hash: " + txhash);
          // });
          console.log(tran, "tran");
          // const createSaleCAss = await web3.eth.getTransactionReceipt(tran.transactionHash);
          // console.log(createSaleCAss, "createSaleCAss?");
          //   .on("confirmation", console.log);
          console.log(tran, "tran");
          // const resultofCreate = await web3.eth.getTransactionReceipt("0xb39946bd3c149058e66628568c1a818e5ba10647e9eccf4a6e6f50a3ef866885");
          // console.log(resultofCreate, "컨트랙트어드레스");
        }

        // --------------------------
        // const newtokenId = createNFTResponse.events.Transfer.returnValues.tokenId;
        console.log("이후 토큰 아이디");
        let newtokenId = await NFTcreateContractInstance.methods.current().call();
        web2 = web3.setProvider(new Web3.providers.HttpProvider(GANACHE_SERVER_URL));
        console.log(web3, "web3");
        console.log(web2, "web2");
        console.log(web3.currentProvider, "?");
        this.newtokenId = newtokenId;
        console.log(newtokenId, "이거토큰아이디임");
        // 토큰id의 주인주소

        console.log("1번 아이디입니다 ");
        console.log("아아");

        const ownerof = await NFTcreateContractInstance.methods.ownerOf(newtokenId).call().then(console.log);
        // 해당 토큰의 uri 주소
        const tokenurls = await NFTcreateContractInstance.methods.tokenURI(newtokenId).call().then(console.log);
        // 아래 세가지
        // nft에 대한 정보 백엔드에 업로드
        console.log(newtokenId, "newtokenId");
        console.log(createNFTResponse, "createNFTResponse");
        console.log("------------------------");
        newtokenId = await NFTcreateContractInstance.methods.current().call();
        console.log(newtokenId, "newtokenId");
        console.log(createNFTResponse, "createNFTResponse");
        let checking = NFTcreateContractInstance.methods.getApproved(newtokenId).call();
        console.log(checking, "11111111111111111");
        console.log(newtokenId, myAccount, IPFSresult, NFT_CA, "됩니까");
        const createNFTtoBack = await axios({
          method: "PUT",
          url: `${SERVER_URL}/api/file/update`,
          data: { tokenId: newtokenId, ownerAddress: myAccount, metadataUri: IPFSresult, contractAddress: NFT_CA },
          headers: {
            // Authorization: token,
            Authorization: this.$store.state.authToken,
          },
        });
        await axios({
          method: "get",
          url: `${SERVER_URL}/api/nft/items/${myAccount}`,
          headers: {
            // Authorization: token,
            Authorization: this.$store.state.authToken,
          },
        }).then((res) => {
          // console.log(res.data.nftList[41].nftSeq);
          const pos = res.data.nftList[res.data.nftList.length - 1].nftSeq;
          this.$router.push({ name: "ProductDetail", params: { id: pos } });
        });

        // console.log(newtokenId, myAccount, IPFSresult, NFT_CA, "됩니까");

        // ownerof, newtokenId, IPFSresult

        // NFTcreateContractInstance.methods.create(myAccount, IPFSresult).then((res) => {
        //   console.log(res, "결과");
        // });
        // console.log(NFTcreateContractInstance.methods, "방법들");
        // mintResult.methods.get().call().then(console.log);
        // const finalresult = mintResult.encodeABI();
        // .then(() => {
        //   // console.log(this.newtokenId);
        //   this.$router.push({
        //     name: "ProductDetail",
        //     params: {
        //       // id:1
        //       id: this.newtokenId,
        //       //
        //     },
        //   });
        // });
      } else {
        this.authorPrivateKey = null;
        // this.$router.go();
      }
      // 위에 3번 전 과정
      // console.log(this.form);
    },
  },
  computed: {
    ...mapState(["myAddress", "username"]),
    ...mapState(["authToken"]),
  },
  mounted() {
    /*==============File upload =============== */
    function fileUpload(selector) {
      let elem = document.querySelectorAll(selector);
      if (elem.length > 0) {
        elem.forEach((item) => {
          item.addEventListener("change", function () {
            var target = document.getElementById(item.dataset.target);
            var allowedExtensions = ["jpg", "png", "gif", "webp", "mp4", "mp3"];
            var fileExtension = this.value.split(".").pop();
            var lastDot = this.value.lastIndexOf(".");
            var ext = this.value.substring(lastDot + 1);
            var extTxt = (target.value = ext);

            if (!allowedExtensions.includes(fileExtension)) {
              alert(extTxt + " file type not allowed, Please upload jpg, png, gif, webp, mp4 or mp3 file");
              target.innerHTML = "Please upload jpg, png, gif, webp, mp4 or mp3 file";
            } else {
              target.innerHTML = item.files[0].name;
            }
          });
        });
      }
    }

    fileUpload(".file-upload-input");

    /*  ============== Unlock once purchased Checkbox switcher ============= */
    // function checkboxSwitcher(selector) {
    //   let elem = document.querySelectorAll(selector);
    //   if (elem.length > 0) {
    //     elem.forEach((item) => {
    //       item.addEventListener("change", function () {
    //         let target = document.getElementById(item.dataset.target);
    //         if (this.checked) {
    //           console.log(target, "뭔데 도대체 이게 어?");
    //           target.classList.add("is-shown");
    //         } else {
    //           console.log(target, "뭔데 도대체 이게 어?");

    //           target.classList.remove("is-shown");
    //         }
    //       });
    //     });
    //   }
    // }
    // checkboxSwitcher(".checkbox-switcher");
  },
};
</script>
