<template>
  <v-card
    width="700"
    v-ripple="false"
    class="mx-auto mb-4 pa-2"
    style="margin-top: 78px;box-shadow:1px 4px 7px rgba(255,0,0,.3)"
  >
    <v-row class="ma-0">
      <v-col class="pb-0" cols="12">
        <v-card flat>
          <v-textarea
            clearable
            clear-icon="mdi-close-circle-outline"
            label="有什么想和大家分享的"
            class
            id="dynamic_textarea"
            v-model.trim="dynamic_text"
            :value="dynamic_text"
            color="#EF9A9A"
            outlined
            flat
            solo
            hide-details
            rows="2"
          ></v-textarea>
        </v-card>
      </v-col>
    </v-row>
    <v-row class="ma-0" align="center">
      <v-col cols="1" class="py-0 px-1">
        <v-btn @click="showEmoji()" small fab text style="font-size:20px;"
          >😀</v-btn
        >
      </v-col>
      <v-col cols="1" class="py-0 px-1">
        <v-btn @click="showImg()" small fab text style="font-size:20px;"
          >🖼️</v-btn
        >
      </v-col>
      <v-col cols="1" class="py-0 px-1">
        <v-btn @click="showMusic()" small fab text style="font-size:20px;"
          >🎵</v-btn
        >
      </v-col>
      <v-col cols="9" class="d-flex justify-end py-0 pr-4">
        <v-btn small color="red" dark style="font-size:14px;">发布</v-btn>
      </v-col>
    </v-row>
    <v-row id="inputPanel" class="ma-0 mt-1">
      <!-- 表情输入框 -->
      <v-card
        id="emoji_card "
        v-if="show_emoji"
        height="100"
        style="overflow-y:auto"
        class="mx-1"
      >
        <v-btn
          v-for="(emoji, index) in emojis"
          :key="index"
          small
          fab
          text
          @click="addEmoji(index)"
          style="font-size:20px;"
          >{{ emoji }}</v-btn
        >
      </v-card>
      <!-- 图片上传框 -->
      <v-card
        v-if="show_img"
        class="d-flex align-center justify-start mx-1 px-2"
        width="700"
        height="100"
      >
        <v-hover
          v-slot:default="{ hover }"
          v-for="(imgUrl, index) in imgUrls"
          :key="index"
        >
          <v-card class="mr-1" height="80" width="80px">
            <v-img
              aspect-ratio="1"
              max-width="80"
              max-height="80"
              :src="imgUrl"
            ></v-img>

            <v-overlay
              v-if="hover"
              z-index="1"
              class="d-felx justify-end align-start"
              opacity="0"
              absolute
              value="true"
            >
              <v-btn
                height="24"
                @click="delImg(index)"
                width="24"
                class="del-img"
                icon
              >
                <v-icon color="#000">mdi-close-box</v-icon>
              </v-btn>
            </v-overlay>
          </v-card>
        </v-hover>
        <v-card
          v-if="show_img_input"
          class="d-flex align-center justify-center"
          height="80"
          width="80"
        >
          <v-icon>mdi-image-plus</v-icon>

          <v-overlay z-index="1" opacity="0" absolute value="true">
            <input
              type="file"
              @change="fileChange"
              id="img_input"
              accept="image/png, image/jpeg, image/gif, image/jpg"
              style="opacity: 0;height:80px;width:80px"
            />
          </v-overlay>
        </v-card>
      </v-card>
      <!-- 音乐上传输入框 -->
      <v-card
        v-if="show_music"
        width="700"
        height="100"
        class="d-flex justify-center align-center mx-1"
      >
        <v-file-input
          accept="audio/*"
          class="mx-12 pa-0"
          label="请选择音乐"
        ></v-file-input>
      </v-card>
    </v-row>
  </v-card>
</template>
<script>
export default {
  data: () => ({
    emojis: [
      "😀",
      "😃",
      "😄",
      "😁",
      "😆",
      "😅",
      "🤣",
      "😂",
      "😳",
      "😦",
      "😰",
      "😥",
      "😭",
      "🙂",
      "🙃",
      "😉",
      "😊",
      "😇",
      "🥰",
      "😍",
      "😘",
      "😗",
      "😚",
      "😋",
      "😛",
      "😜",
      "🤪",
      "😝",
      "🤗",
      "🤐",
      "🤨",
      "😐",
      "😑",
      "😶",
      "😏",
      "😒",
      "🙄",
      "😬",
      "🤥",
      "😌",
      "😴",
      "😪",
      "😷",
      "🤒",
      "🤢",
      "🤮",
      "🤧",
      "🥵",
      "🥶",
      "😎",
      "😕",
      "😟",
      "🙁",
      "😮",
      "😱",
      "😖",
      "😓",
      "😡",
      "😠",
      "😈",
      "👿",
      "💗",
      "❤",
      "👌",
      "🤏",
      "✌",
      "🤞"
    ],
    show_emoji: false,
    show_img: false,
    show_music: false,
    show_img_input: true,
    dynamic_text: "",
    img_rules: [
      value =>
        !value ||
        value.size < 2000000 ||
        "Avatar size should be less than 2 MB!"
    ],
    imgUrls: []
  }),
  methods: {
    showEmoji() {
      if (this.show_emoji == false) {
        this.show_emoji = true;
        this.show_img = false;
        this.show_music = false;
      } else {
        this.show_emoji = false;
        var dt = document.querySelector("#dynamic_textarea");
        var that = this;
        dt.removeEventListener("blur", that.text_blur, false);
      }
    },
    showImg() {
      if (this.show_img == false) {
        this.show_emoji = false;
        this.show_img = true;
        this.show_music = false;
      } else {
        this.show_img = false;
      }
    },
    showMusic() {
      if (this.show_music == false) {
        this.show_emoji = false;
        this.show_img = false;
        this.show_music = true;
      } else {
        this.show_music = false;
      }
    },
    addEmoji(i) {
      var dt = document.querySelector("#dynamic_textarea");
      var startPos = dt.selectionStart;
      var endPos = dt.selectionEnd;
      if (startPos === undefined || endPos === undefined) return;
      var txt = dt.value;
      var result =
        txt.substring(0, startPos) + this.emojis[i] + txt.substring(endPos);
      dt.value = result;
      var that = this;
      dt.addEventListener("blur", that.text_blur, false);
      dt.focus();
      dt.selectionStart = startPos + this.emojis[i].length;
      dt.selectionEnd = startPos + this.emojis[i].length;
      this.dynamic_text = result;
    },
    text_blur(e) {
      e.preventDefault();
      var dt = document.querySelector("#dynamic_textarea");
      dt.focus();
    },
    fileChange(e) {
      var img_input = document.querySelector("#img_input");

      var files = e.target.files || e.dataTransfer.files;
      if (!files.length) return;
      // var iamge = new Image();
      var that = this;
      for (var i = 0; i < files.length; i++) {
        var reader = new FileReader();
        reader.readAsDataURL(files[i]);
        reader.onload = function(e) {
          if (that.imgUrls.length <= 6) {
            that.imgUrls.push(e.target.result);
            that.show_img_input = true;
            if (that.imgUrls.length == 6) {
              that.show_img_input = false;
            }
          } else {
            that.show_img_input = false;
          }
        };
      }
      img_input.value = null;
    },
    delImg(i) {
      this.imgUrls.splice(i, 1);
    }
  }
};
</script>
