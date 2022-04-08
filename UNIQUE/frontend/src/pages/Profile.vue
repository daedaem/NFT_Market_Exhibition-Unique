<template>
  <div class="page-wrap">
    <!-- header  -->
    <header class="header-section has-header-main bg-gradient-2">
      <!-- <router-link>aaaaa</router-link> -->
      <!-- Header main -->
      <HeaderMain></HeaderMain>
      <!-- hero -->
      <AuthorHero
        avatarSize="avatar-3"
        :isCopyInput="true"
        coverimg2="https://t1.daumcdn.net/cfile/tistory/187E474D4D18B4EF35"
        coverimg="https://cdn.pixabay.com/photo/2015/12/01/15/43/black-1072366__340.jpg"
        :img2="member.fileUrl"
        :profileImageUrl="member.profileImageUrl"
        :title="grade"
        :username="member.memberId"
        :address="member.memberAddress"
        :btntext="SectionData.authorPersonalData.btnText"
        :btnlink="SectionData.authorPersonalData.btnLink"
        :myId="member.memberSeq"
      ></AuthorHero>
    </header>
    <!-- Author section  -->
    <ProfileSection
     :content = member.memberBio
     :datetext = member.regDt
     :nftList = nftList
     ></ProfileSection>
    <!-- Footer  -->
    <Footer classname="bg-black on-dark"></Footer>
  </div>
  <!-- end page-wrap -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
const SERVER_URL = process.env.VUE_APP_SERVER_URL;
import axios from "axios";
import SectionData from "@/store/store.js";
import { mapState } from "vuex";
export default {
  name: "profile",
  components: {},
  data() {
    return {
      SectionData,
      id: this.$route.params.id,
      nftList: null,
      member:{
        memberSeq: 0,
        memberAddress: "",
        memberId: 0,
        memberBio: "",
        regDt: null,
        profileImageUrl: null,
      },
      grade: null,
    };
  },
  methods : {
    async getProfile() {
      axios({
        method: "GET",
        url: `${SERVER_URL}/api/members/profile/${this.$route.params.id}`,
        headers: {
          Authorization: this.authToken,
        },
      })
      .then((res)=> {
        console.log(res.data,'userInfo')
        console.log(res.data.regDt,'userInfodddd')
        console.log(res.data.memberAddress,'userInfoxxxxx')
        this.member.memberSeq = res.data.memberSeq
        this.member.memberAddress = res.data.memberAddress
        this.member.memberId = res.data.memberId
        this.member.memberBio = res.data.memberBio
        this.member.regDt = res.data.regDt
        this.member.profileImageUrl = `https://j6e205.p.ssafy.io/${res.data.profileImageUrl}`
        if (res.data.authority == 0) {
          this.grade = "Private Artist";
        }
      })
      .then((res) => {
        this.getNft()
      });
    },
    async getNft() {
      const itmes = await axios({
        method: "GET",
        url: `${SERVER_URL}/api/nft/items/${this.member.memberAddress}`,
        headers: {
          Authorization: this.authToken,
        },
      }).then((res) => {
        console.log(res.data, "nft");
        this.nftList = res.data.nftList;
      });
      // this.total = this.exhibitionWorkList.length;
    },
  },
  computed: {
    ...mapState(["authToken", "myAddress", "userId"]),
  },
  created() {
    this.getProfile();
  },
};
</script>
