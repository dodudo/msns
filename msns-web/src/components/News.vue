<template>
  <v-card flat style="position:relative;">
    <v-card flat>
      <v-card
        v-for="(card_text, index) in card_texts"
        :key="index"
        :max-width="card_width"
        flat
        style="box-shadow:1px 4px 9px rgba(255,0,0,.4); "
        class="mx-auto mb-4 pb-2"
      >
        <v-list-item>
          <v-list-item-avatar color="grey"></v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title class="body-1">哈哈哈</v-list-item-title>
            <v-list-item-subtitle class="body-2"
              >2019-12-10</v-list-item-subtitle
            >
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
                    <v-btn color="blue darken-1" text @click="dialog = false"
                      >提交</v-btn
                    >
                    <v-btn color="blue darken-1" text @click="dialog = false"
                      >取消</v-btn
                    >
                  </v-card-actions>
                </v-card>
              </v-dialog>
            </v-list>
          </v-menu>
        </v-list-item>
        <v-content class="px-4">
          <div
            ref="card_text"
            class="text-content mb-2"
            :class="{ fold: card_text.fold }"
            :id="`text_${index}`"
          >
            {{ card_text.text }}
          </div>
          <v-row>
            <v-spacer></v-spacer>
            <v-btn
              text
              small
              tile
              class="mr-2 mt-n4"
              color="blue"
              v-show="card_text.btnShow"
              @click="card_text.fold = !card_text.fold"
              >{{ card_text.fold ? "展开" : "收起" }}</v-btn
            >
          </v-row>
          <v-img
            src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg"
            height="194"
          ></v-img>
        </v-content>
        <v-card v-show="true" style outlined flat class="px-0 my-2 mx-4">
          <v-list class="pa-0">
            <v-list-item style="background-color:#FAFAFA">
              <v-card tile height="40" width="40">
                <v-list-item-avatar class="ma-0" tile>
                  <v-img
                    src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg"
                  ></v-img>
                </v-list-item-avatar>

                <v-overlay z-index="1" opacity="0.3" absolute value="true">
                  <v-btn icon>
                    <v-icon>mdi-play-circle-outline</v-icon>
                  </v-btn>
                </v-overlay>
              </v-card>
              <v-list-item-content class="ml-3">
                <v-list-item-title>忽然</v-list-item-title>
                <v-list-item-subtitle>李志</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>
          </v-list>
        </v-card>
        <v-card-actions>
          <span class="caption ml-2">浏览次数</span>
          <v-spacer></v-spacer>
          <v-card flat>
            <v-btn icon>
              <v-icon>mdi-thumb-up</v-icon>
            </v-btn>
            <span class="caption">12</span>
          </v-card>
          <v-card flat class="mx-3">
            <v-btn icon>
              <v-icon>mdi-heart</v-icon>
            </v-btn>
            <span class="caption">12</span>
          </v-card>
          <v-card flat>
            <v-btn @click="card_text.show_input = !card_text.show_input" icon>
              <v-icon>mdi-message-reply</v-icon>
            </v-btn>
            <span class="caption">12</span>
          </v-card>
        </v-card-actions>
        <v-card v-if="card_text.show_input" flat>
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
            <v-btn small width="100" dark color="#BA68C8" class="mr-4 caption"
              >发表评论</v-btn
            >
          </v-card>
        </v-card>
      </v-card>
    </v-card>
  </v-card>
</template>
<script>
export default {
  data: () => ({
    dialog: false,
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
    card_texts: [
      {
        btnShow: false,
        fold: false,
        show_input: false,
        text: `Visit ten places on our planet that are underdsfad
          sadffffffffffsdfa
          asdfaaaaaaaaaaaaaaaa
          dsaffffffffffffffffffffffffffffffffffffffffffff
          sdfaweqggggggaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
          dsaffffffffffffffffffffffffffffffffffffffffffff
          sdfaweqggggggaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
          geeeeeeeeeeeeeeeeeeeeeeeeeeeeee
          eggggggggggggggggggggggg
          egggggggggggggggggggggggggggggggggggggg
          eeeeeeeeeeeeeeeeeeeeeeeeeeeeeegoing the biggest changes today.`
      },
      {
        btnShow: false,
        fold: false,
        show_input: false,
        text: `sdagdgsdagasdgasdg`
      },
      {
        btnShow: false,
        fold: false,
        show_input: false,
        text: `Visit ten places on our planet that are underdsfad
          sadffffffffffsdfa
          asdfaaaaaaaaaaaaaaaa
          dsaffffffffffffffffffffffffffffffffffffffffffff
          sdfaweqggggggaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
          dsaffffffffffffffffffffffffffffffffffffffffffff
          sdfaweqggggggaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
          geeeeeeeeeeeeeeeeeeeeeeeeeeeeee
          eggggggggggggggggggggggg
          egggggggggggggggggggggggggggggggggggggg
          eeeeeeeeeeeeeeeeeeeeeeeeeeeeeegoing the biggest changes today.`
      },
      {
        btnShow: false,
        fold: false,
        show_input: false,
        text: `Visit ten places on our planet that are underdsfad
          sadffffffffffsdfa
          asdfaaaaaaaaaaaaaaaa
          dsaffffffffffffffffffffffffffffffffffffffffffff
          sdfaweqggggggaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
          dsaffffffffffffffffffffffffffffffffffffffffffff
          sdfaweqggggggaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
          geeeeeeeeeeeeeeeeeeeeeeeeeeeeee
          eggggggggggggggggggggggg
          egggggggggggggggggggggggggggggggggggggg
          eeeeeeeeeeeeeeeeeeeeeeeeeeeeeegoing the biggest changes today.`
      },
      {
        btnShow: false,
        fold: false,
        show_input: false,
        text: `sdagdgsdagasdgasdg`
      },
      {
        btnShow: false,
        fold: false,
        show_input: false,
        text: `sdagdgsdagasdgasdg`
      },
      {
        btnShow: false,
        fold: false,
        show_input: false,
        text: `sdagdgsdagasdgasdg`
      }
    ],
    hideText: "",
    isfold: false,
    card_width: 700,
    showFoldBtn: false
  }),
  methods: {},
  watch: {},
  mounted() {
    for (let i = 0; i < this.$refs.card_text.length; i++) {
      let textHeight = window
        .getComputedStyle(this.$refs.card_text[i])
        .height.replace("px", "");
      if (textHeight > 120) {
        this.card_texts[i].fold = true;
        this.card_texts[i].btnShow = true;
      }
    }
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
