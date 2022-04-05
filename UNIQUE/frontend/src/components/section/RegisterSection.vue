<template>
  <section class="register-section section-space-b pt-4 pt-md-5 mt-md-3">
    <div class="container">
      <div class="row align-items-center justify-content-center">
        <div class="col-lg-11 col-md-11 col-sm-11">
          <!-- <form action="#" class="form-create mb-5 mb-lg-0"> -->
            <div class="section-head-sm ps-4">
              <h2 class="mb-2" v-html="SectionData.registerData.title"></h2>
            </div>
            <div class="form-item mb-4 d-flex">
              <div class="container">
                <div class="mb-4">
                  <h4 class="mb-2">ID</h4>
                  <input type="text" class="form-control" id="userName" placeholder="ID" v-model="credentials.memberId" autocomplete="off" />
                </div>
                <div class="mb-4">
                  <h4 class="mb-2">Password</h4>
                  <input type="password" class="form-control password" id="password" placeholder="Password" v-model="credentials.memberPassword" autocomplete="off" />
                </div>
                <!-- end form-floating -->

                <div class="mb-4">
                  <h5 class="mb-3">Upload file</h5>
                  <div class="file-upload-wrap">
                    <p class="file-name mb-4" id="file-name">PNG, GIF, WEBP, MP4 or MP3. Max 100mb.</p>
                    <input id="file-upload" class="file-upload-input" data-target="file-name" type="file" enctype="multipart/form-data" @change="selectFile" hidden />
                    <label for="file-upload" class="input-label btn btn-dark">Choose File</label>
                  </div>
                </div>
              </div>
              <!-- bio start -->
              <div class="mb-4 pb-4 container">
                <h4 class="mb-2">Bio</h4>
                <textarea type="password" class="form-control" style="height: 100%" id="bio" placeholder="Input your Biography" v-model="credentials.memberBio" autocomplete="off"></textarea>
                <div class="my-3">

                  <button class="btn btn-dark d-flex" style="margin-left: auto" type="submit" @click="signup(credentials)">{{ SectionData.registerData.btnText }}</button>
                  <p class="mt-3 form-text text-end">
                    {{ SectionData.registerData.haveAccountText }} <router-link :to="SectionData.registerData.btnTextLink" class="btn-link">{{ SectionData.registerData.btnTextTwo }}</router-link>
                  </p>
                </div>
              </div>
              <!-- bio end-->
              <!-- end form-floating -->
            </div>
          <!-- </form> -->
          <!-- <p class="mb-4 form-text text-end">{{ SectionData.registerData.termText }}</p> -->

          <!-- ------- -->
          <!-- <button class="btn btn-dark ms-5" type="submit" @click="signup(credentials)">{{ SectionData.registerData.btnText }}</button>

          <p class="mt-3 form-text ps-5">
            {{ SectionData.registerData.haveAccountText }} <router-link :to="SectionData.registerData.btnTextLink" class="btn-link">{{ SectionData.registerData.btnTextTwo }}</router-link>
          </p> -->
          <!-- ---- -->
        </div>
        <!-- end col-lg-6 -->
      </div>
      <!-- end row -->
    </div>
    <!-- end container -->
  </section>
  <!-- end register-section -->
</template>

<script>
// Import component data. You can change the data in the store to reflect in all component
import SectionData from "@/store/store.js";
import { mapActions } from "vuex";
export default {
  name: "RegisterSection",
  data() {
    return {
      SectionData,
      credentials: {
        memberId: null,
        memberPassword: null,
        memberBio: null,
        file: null,
      },
    };
  },
  methods: {
    selectFile(data) {
      this.credentials.file = data.target.files[0];
    },
    ...mapActions(["signup"]),
  },
  mounted() {
    /*  ======== Show/Hide passoword ======== */
    function showHidePassword(selector) {
      let elem = document.querySelectorAll(selector);
      if (elem.length > 0) {
        elem.forEach((item) => {
          item.addEventListener("click", function (e) {
            e.preventDefault();
            let target = document.getElementById(item.getAttribute("href"));
            if (target.type == "password") {
              target.type = "text";
              item.classList.add("is-shown");
            } else {
              target.type = "password";
              item.classList.remove("is-shown");
            }
          });
        });
      }
    }
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

    showHidePassword(".password-toggle");
  },
};
</script>
