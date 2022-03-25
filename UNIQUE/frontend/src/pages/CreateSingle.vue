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
              <router-link :to="SectionData.createSingleData.path" class="btn-link fw-semibold"><em class="ni ni-arrow-left"></em> {{ SectionData.createSingleData.btnText }}</router-link>
              <h1 class="mt-2">{{ SectionData.createSingleData.title }}</h1>
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
              <!-- <div class="form-item mb-4"> -->
              <!-- <h5 class="mb-3">Select Method</h5> -->
              <!-- <ul class="row g-3 nav nav-tabs nav-tabs-s2" id="myTab" role="tablist">
                  <li class="nav-item col-4 col-sm-4 col-lg-3" role="presentation" v-for="list in SectionData.selectMethodTabNav" :key="list.id">
                    <button class="nav-link" :class="list.isActive" :id="list.slug" data-bs-toggle="tab" :data-bs-target="list.bsTarget" type="button">
                      <em class="ni nav-link-icon" :class="list.icon"></em>
                      <span class="nav-link-title mt-1 d-block">{{ list.title }}</span>
                    </button>
                  </li>
                </ul> -->
              <!-- <div class="tab-content mt-4" id="myTabContent">
                  <div class="tab-pane fade show active" id="fixed-price" role="tabpanel" aria-labelledby="fixed-price-tab">
                    <div class="form-create-tab-wrap">
                      <label class="mb-2 form-label">Price</label>
                      <input type="text" class="form-control form-control-s1" placeholder="Enter a price for item" />
                    </div> -->
              <!-- end form-create-tab-wrap -->
              <!-- </div> -->
              <!-- end tab-pane -->
              <!-- <div class="tab-pane fade" id="timed-auction" role="tabpanel" aria-labelledby="timed-auction-tab">
                    <div class="form-create-tab-wrap">
                      <label class="mb-2 form-label">Minimum bid</label>
                      <input type="text" class="form-control form-control-s1" placeholder="Enter Minimum bid" />
                      <div class="row mt-3">
                        <div class="col-lg-6">
                          <label class="mb-2 form-label">Starting date</label>
                          <input type="date" class="form-control form-control-s1" />
                        </div> -->
              <!-- end col-lg-6 -->
              <!-- <div class="col-lg-6">
                          <label class="mb-2 form-label">Expiration date</label>
                          <input type="date" class="form-control form-control-s1" />
                        </div> -->
              <!-- end col-lg-6 -->
              <!-- </div> -->
              <!-- end row -->
              <!-- </div> -->
              <!-- end form-create-tab-wrap -->
              <!-- </div> -->
              <!-- end tab-pane -->
              <!-- <div class="tab-pane fade" id="open-for-bids" role="tabpanel" aria-labelledby="open-for-bids-tab">
                    <div class="form-create-tab-wrap">
                      <label class="mb-2 form-label">Minimum bid</label>
                      <input type="text" class="form-control form-control-s1" placeholder="Enter Minimum bid" />
                    </div> -->
              <!-- end form-create-tab-wrap -->
              <!-- </div> -->
              <!-- end tab-pane -->
              <!-- </div> -->
              <!-- end tab-content -->
              <!-- </div> -->
              <!-- end form-item -->
              <div class="form-item mb-3">
                <div class="switch-wrap">
                  <div class="d-flex align-items-center justify-content-between">
                    <div class="me-2">
                      <h5 class="mb-1">Unlock once purchased</h5>
                      <p class="form-text">Content will be unlocked after successful transaction</p>
                    </div>
                    <div class="form-check form-switch form-switch-s1">
                      <input class="form-check-input checkbox-switcher" data-target="switch-content-unlock" type="checkbox" />
                    </div>
                    <!-- end form-check -->
                  </div>
                  <!-- end d-flex -->
                  <div class="switch-content-unlock mt-4" id="switch-content-unlock">
                    <input type="text" name="text" class="form-control form-control-s1" placeholder="Access key, code to redeem or link to a file..." />
                  </div>
                </div>
                <!-- end switch-wrap -->
              </div>
              <!-- end form-item -->
              <div class="form-item mb-4">
                <h5 class="mb-1">Choose collection</h5>
                <p class="form-text mb-3">This is the collection where your item will appear.</p>
                <!-- <v-select class="generic-select" v-model="selected" :options="options"></v-select> -->
              </div>
              <!-- end form-item -->
              <div class="form-item mb-4">
                <div class="mb-4">
                  <label class="mb-2 form-label">Title</label>
                  <input type="text" class="form-control form-control-s1" v-model="form.nftName" placeholder="e. g. Redeemable T-Shirt with logo" />
                </div>
                <div class="mb-4">
                  <label class="mb-2 form-label">Description</label>
                  <textarea name="message" class="form-control form-control-s1" v-model="form.description" placeholder="e. g. After purchasing you’ll be able to get the real T-Shirt"></textarea>
                </div>
                <div class="mb-3">
                  <label class="mb-2 form-label">Royalties</label>
                  <input type="text" class="form-control form-control-s1" placeholder="e.g 10%" />
                  <p class="form-text mt-1">Suggested: 0, 10%, 20%, 30%. Maximum is 70%</p>
                </div>
              </div>
              <!-- end form-item -->

              <button type="button" data-bs-toggle="modal" data-bs-target="#createNftModal" class="btn btn-dark d-block">Create Item</button>

              <!-- 
              <button data-bs-toggle="modal" data-bs-target="#createNftModal" class="btn btn-dark d-block">Create Item</button> -->
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
    <Footer classname="bg-dark on-dark"></Footer>
    <!-- Modal -->
    <div class="modal fade" id="createNftModal" tabindex="-1" aria-hidden="true">
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
            <button class="btn btn-dark d-block" @click="submitCreateNFT">Confirm</button>
            <!-- <a :href="SectionData.createNftModal.btnLink" class="btn btn-dark d-block" @click="submitCreateNFT"></a> -->
          </div>
          <!-- end modal-body -->
        </div>
        <!-- end modal-content -->
      </div>
      <!-- end modal-dialog -->
    </div>
    <!-- endmodal -->
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
import ABI from "../../common/ABI";
// import abi from "../../../smart-contracts/build/contracts/SsafyNFT.json";
import SsafyNFT from "../../../smart-contracts/build/contracts/SsafyNFT.json";
const abi = ABI.CONTRACT_ABI.NFT_ABI;
const CA = SsafyNFT.networks["5777"].address;
// console.log(CA);

// 네트워크 연결
let web3 = new Web3(new Web3.providers.HttpProvider("http://127.0.0.1:7545"));
// let webs = new Web3("http://127.0.0.1:7545");

// let pollWeb3 = state => {

export default {
  name: "CreateSingle",
  data() {
    return {
      SectionData,
      selected: "Select Collection",
      options: ["Select Collection", "Abstraction", "Patternlicious", "Skecthify", "Cartoonism", "Virtuland", "Papercut"],
      // d
      form: {
<<<<<<< HEAD
=======
        // author: null,
        author: "성현",
>>>>>>> FE/sunghyun
        file: null,
        nftName: null,
        description: null,
      },
      authorPrivateKey: null,
      // authorPublicKey: null,
    };
  },
  methods: {
    selectFile(data) {
      this.form.nftName = data.nftName;
      this.form.file = data.target.files[0];
<<<<<<< HEAD
      console.log(data.target.files)
      console.log(data.target.files[0])

      // console.log(data.target.files[0]);
      // if (data.target.files[0].type.includes("video")) {
      //   // 나는 비디오
      //   this.form.file = data[0],
      //     // type: "video",

      //     // previewImage: URL.createObjectURL(data[i]),
      // } else {
      //   // console.log(data.files.type, "11");
      //   this.form.file = {
      //     type: "img",
      //     content: data[0],
      //     // previewImage: URL.createObjectURL(data[i]),
      //   };
      // }
    },
    submitCreateNFT() {
      console.log(this.form);
      let data = new FormData();
      data.append('nftName', this.form.nftName);
      data.append('file', this.form.file);
      console.log(data)

      axios({
        method: "POST",
        url: `${SERVER_URL}/file`,
        data: data,
        headers: {
          // Authorization: token,
          Authorization:
          "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY0ODc4MDgzMX0.CEFASNbzeHivF75lnL7B_1Nv3OivjJGhrkTRNAGJGEqbV7xv5XVMQFdWxvw4WPjLwRHZXWwIucBV69Um-f8_dw",
          "Content-Type": "multipart/form-data",
        },
      }).then((res) => {
        console.log(res);
      });
    },
=======
    },
    async submitCreateNFT() {
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
      // console.log(this.authorPrivateKey);
      // privatekey는 0x로 시작하는듯?
      const checkPubKey = await getAddressFrom("0x" + this.authorPrivateKey);
      const temp = await web3.eth.getAccounts();
      const myAccount = temp[0];

      // 공개키가 유효하다면 정보 등록
      if (checkPubKey === myAccount) {
        console.log("일치합니다.");
        let data = new FormData();
        // data.append("author", this.form.author);
        data.append("nftName", this.form.nftName);
        // data.append("description", this.form.description);
        data.append("file", this.form.file);
        axios({
          method: "POST",
          url: `${SERVER_URL}/file`,
          data: data,
          headers: {
            // Authorization: token,
            Authorization:
              "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsImV4cCI6MTY0ODc4MDgzMX0.CEFASNbzeHivF75lnL7B_1Nv3OivjJGhrkTRNAGJGEqbV7xv5XVMQFdWxvw4WPjLwRHZXWwIucBV69Um-f8_dw",
            "Content-Type": "multipart/form-data",
          },
        }).then((preIPFSresult) => {
          async function load() {
            const IPFSresult = preIPFSresult.data;
            console.log(IPFSresult, "ipfs결과");
            const ssafyToken1 = await new web3.eth.Contract(abi, CA);
            console.log(ssafyToken1,)
            let mintResult = await ssafyToken1.methods.create(myAccount, IPFSresult);
            //
            // const finalresult = mintResult.encodeABI();
            console.log(mintResult, "민트 컨트랙트 결과");
            // console.log(finalresult, "민트컨트랙트 결과를 해석");
            // 함수결과값
            // 메타 url:
          }
          load();
        });
      }
      // 위에 3번 전 과정
      // console.log(this.form);
    },
    // async getValue() {
    //   web3 = new Web3(web3.currentProvider);
    //   let electionContract = new web3.eth.Contract(this.electionContract.abi, "0x9828F99985a337c41fE3Ef1B72932365d3EA4e58");
    //   this.currentAddress = electionContract._address;
    //   let candidatesCount = await electionContract.methods.candidatesCount().call();
    //   this.tableData = [];
    //   for (let i = 0; i < candidatesCount; i++) {
    //     if (i != 0) {
    //       let candidates = await electionContract.methods.candidates(i).call();
    //       this.tableData.push({ id: i, name: candidates.name, votes: candidates.voteCount });
    //     }
    //   }
    // },
    // async setVote() {
    //   web3 = new Web3(web3.currentProvider);
    //   let electionContract = new web3.eth.Contract(this.electionContract.abi, "0x9828F99985a337c41fE3Ef1B72932365d3EA4e58");
    //   let vote = await electionContract.methods.vote(this.selectedCandidate).send({ from: process.env.VUE_APP_ETHADDRESS });
    //   console.log(vote);
    // },
>>>>>>> FE/sunghyun
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
    function checkboxSwitcher(selector) {
      let elem = document.querySelectorAll(selector);
      if (elem.length > 0) {
        elem.forEach((item) => {
          item.addEventListener("change", function () {
            let target = document.getElementById(item.dataset.target);
            if (this.checked) {
              target.classList.add("is-shown");
            } else {
              target.classList.remove("is-shown");
            }
          });
        });
      }
    }

    checkboxSwitcher(".checkbox-switcher");
  },
};
</script>
