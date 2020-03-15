<template>
  <v-card flat style="position:relative;">
    <v-card flat>
      <v-card flat class="mx-auto pa-0" width="700" v-show="dynamics[0]==undefined">
        <v-card-text style="text-align:center">没有动态呢</v-card-text>
      </v-card>
      <v-card
        v-for="(dynamic, index) in dynamics"
        :key="index"
        :max-width="card_width"
        flat
        style="box-shadow:1px 4px 9px rgba(255,0,0,.4); "
        class="mx-auto mb-4 pb-2"
      >
        <v-list-item>
          <v-list-item-avatar color="grey"></v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title class="body-1">{{dynamic.author}}</v-list-item-title>
            <v-list-item-subtitle class="body-2">{{formatDate(dynamic.publishDate)}}</v-list-item-subtitle>
          </v-list-item-content>
          <v-spacer></v-spacer>
          <v-menu offset-y>
            <template v-slot:activator="{ on }">
              <v-btn v-on="on" icon>
                <v-icon color="gray">mdi-chevron-down</v-icon>
              </v-btn>
            </template>
            <v-list>
              <v-dialog v-model="dialog" max-width="600px">
                <template v-slot:activator="{ on }">
                  <v-btn text small v-on="on">举报</v-btn>
                </template>
                <v-card>
                  <v-card-title>
                    <span class="headline">举报</span>
                  </v-card-title>
                  <v-card-text>
                    <v-row>
                      <v-col cols="3" sm="6" md="4">
                        <v-radio-group v-model="radioGroup">
                          <v-radio
                            v-for="(radioItem, index) in radioItems"
                            :key="index"
                            :label="`${radioItem.name}`"
                            :value="`${radioItem.value}`"
                          ></v-radio>
                        </v-radio-group>
                      </v-col>
                    </v-row>
                  </v-card-text>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="blue darken-1" text @click="dialog = false">提交</v-btn>
                    <v-btn color="blue darken-1" text @click="dialog = false">取消</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-list>
          </v-menu>
        </v-list-item>
        <v-content class="px-4">
          <div
            ref="dynamicContent"
            class="text-content mb-2 dynamic-content"
            :class="{ fold: dynamic.fold }"
            :id="`text_${index}`"
          >{{ dynamic.dynamicContent }}</div>
          <v-row>
            <v-spacer></v-spacer>
            <v-btn
              text
              small
              tile
              class="mr-2 mt-n4"
              color="blue"
              v-show="dynamic.btnShow"
              @click="dynamic.fold = !dynamic.fold"
            >{{ dynamic.fold ? "展开" : "收起" }}</v-btn>
          </v-row>
          <v-row>
            <v-col v-show="imgUrl!=''" v-for="(imgUrl,i) in dynamics[index].imgUrls" :key="i">
              <v-img
                :width="dynamics[index].imgUrls.length <= 3 ? 322 : 200"
                v-if="imgUrl!=''"
                :height="dynamics[index].imgUrls.length <= 3 ? 322 : 200"
                :src="imgUrl"
              ></v-img>
            </v-col>
          </v-row>
        </v-content>
        <v-card v-if="dynamic.music != null" style outlined flat class="px-0 my-2 mx-4">
          <v-list class="pa-0">
            <v-list-item style="background-color:#FAFAFA">
              <v-card tile height="40" width="40">
                <v-list-item-avatar class="ma-0" tile>
                  <v-img :src="dynamic.music.musicCoverUrl"></v-img>
                </v-list-item-avatar>

                <v-overlay z-index="1" opacity="0.3" absolute value="true">
                  <v-btn icon>
                    <v-icon>mdi-play-circle-outline</v-icon>
                  </v-btn>
                </v-overlay>
              </v-card>
              <v-list-item-content class="ml-3">
                <v-list-item-title>{{dynamic.music.musicName}}</v-list-item-title>
                <v-list-item-subtitle>{{dynamic.music.musicAuthor}}</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
        <v-card-actions>
          <span class="caption ml-2">浏览次数{{dynamic.viewCount}}</span>
          <v-spacer></v-spacer>
          <v-card flat>
            <v-btn icon>
              <v-icon>mdi-thumb-up</v-icon>
            </v-btn>
            <span class="caption">{{dynamic.likeCount}}</span>
          </v-card>
          <v-card flat class="mx-3">
            <v-btn icon>
              <v-icon>mdi-heart</v-icon>
            </v-btn>
            <span class="caption">{{dynamic.favorCount}}</span>
          </v-card>
          <v-card flat>
            <v-btn @click="dynamic.show_input = !dynamic.show_input" icon>
              <v-icon>mdi-message-reply</v-icon>
            </v-btn>
            <span class="caption">{{dynamic.commentCount}}</span>
          </v-card>
        </v-card-actions>
        <v-card v-if="dynamic.show_input" flat>
          <v-textarea
            clearable
            clear-icon="mdi-close-circle-outline"
            label="评论哈哈哈"
            class="px-4"
            outlined
            flat
            hide-details
            rows="1"
          ></v-textarea>
          <v-card class="d-flex justify-end my-2" flat>
            <v-btn small width="100" dark color="#BA68C8" class="mr-4 caption">发表评论</v-btn>
          </v-card>
        </v-card>
      </v-card>
      <div v-if="totalPage != 0" class="text-center">
        <v-pagination v-model="currentPage" :length="totalPage" :total-visible="7"></v-pagination>
      </div>
    </v-card>
  </v-card>
</template>
<script>
import { formatDate } from "../assets/formatDate";
export default {
  props: {
    dynamicSearch: {
      type: Object,
      required: true
    }
  },
  data: () => ({
    dialog: false,
    dynamics: [],
    radioGroup: 1,
    radioItems: [
      { name: "色情低俗", value: "porn" },
      { name: "广告", value: "ad" },
      { name: "政治敏感", value: "politics" },
      { name: "违法", value: "Illegal" },
      { name: "病毒木马", value: "virus" },
      { name: "其他", value: "other" }
    ],
    absolute: true,
    overlay: false,
    hideText: "",
    isfold: false,
    card_width: 700,
    showFoldBtn: false,
    totalPage: 0,
    currentPage: 1,
    newsDynamicSearch: {}
  }),
  methods: {
    showComment(e) {
      // console.log(e);
      this.dynamics[e].show_input = !this.dynamics[e].show_input;
    },
    searAllDynamic() {
      this.dynamicSearch.page = this.currentPage;
      this.$http({
        method: "post",
        url: "/search/dynamicPage",
        data: this.dynamicSearch
      }).then(resp => {
        this.dynamics = resp.data.items;
        this.totalPage = resp.data.totalPage;

        // console.log(resp.data);
        for (let i = 0; i < this.dynamics.length; i++) {
          this.dynamics[i].btnShow = false;
          this.dynamics[i].fold = false;
          this.dynamics[i].show_input = false;
          if (this.dynamics[i].imgUrls != null) {
            this.dynamics[i].imgUrls = this.dynamics[i].imgUrls.split(",");
          }
          this.dynamics[i] = Object.assign({}, this.dynamics[i]);
        }
        this.dynamics = Object.assign({}, this.dynamics);
        // console.log(this.dynamics[0]);
      });
    },
    formatDate(time) {
      var date = new Date(time);
      var today = new Date();
      if (formatDate(today, "yyyy-MM-dd") == formatDate(date, "yyyy-MM-dd")) {
        return formatDate(date, "hh:mm");
      } else {
        return formatDate(date, "yyyy-MM-dd hh:mm");
      }
    },
    foldControl(dynamicContent) {
      this.$nextTick(() => {
        if (dynamicContent != null) {
          for (let i = 0; i < dynamicContent.length; i++) {
            let textHeight = window
              .getComputedStyle(dynamicContent[i])
              .height.replace("px", "");
            // console.log(textHeight);
            if (textHeight > 120) {
              this.dynamics[i].fold = true;
              this.dynamics[i].btnShow = true;
            }
          }
        }
      });
    }
  },
  watch: {
    currentPage() {
      this.searAllDynamic();
    },
    dynamics() {
      var dynamicContent = this.$refs.dynamicContent;
      this.foldControl(dynamicContent);
    },
    "dynamicSearch.key"() {
      this.searAllDynamic();
    },
    "dynamicSearch.sortBy"() {
      this.searAllDynamic();
    },
    "dynamicSearch.desc"() {
      this.searAllDynamic();
    }
  },

  created() {
    this.$nextTick(() => {
      this.searAllDynamic();
    });
  },
  updated() {},
  mounted() {
    let that = this;
    setTimeout(() => {
      // console.log(that.$refs);
      var refs = that.$refs;
      var dynamicContent = refs.dynamicContent;
      this.foldControl(dynamicContent);
      // console.log(dynamicContent);
    }, 200);
  }
};
</script>
<style scoped>
.text-content {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-box-orient: vertical;
}
.fold {
  -webkit-line-clamp: 5;
}
</style>
