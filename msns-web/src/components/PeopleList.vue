<template>
  <v-card height style="overflow-y:auto" flat class="mt-2 pa-2">
    <v-list class="pa-0 ma-0" two-line>
      <template v-for="(item, index) in peopleItems">
        <v-divider v-if="item.divider" :key="index"></v-divider>
        <v-list-item class="my-3" v-else :key="item.title">
          <v-list-item-avatar size="60" class="my-0">
            <v-img :src="item.avatar"></v-img>
          </v-list-item-avatar>
          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
            <v-list-item-subtitle>{{ item.subtitle }}</v-list-item-subtitle>
          </v-list-item-content>
          <v-list-item-action>
            <v-btn @click.stop="favor(index)">{{
              item.favor ? "已关注" : "关注"
            }}</v-btn>
          </v-list-item-action>
        </v-list-item>
      </template>
    </v-list>
  </v-card>
</template>
<script>
export default {
  data: () => ({
    peopleItems: [
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "哈哈哈",
        subtitle: "欢迎走进此站知名的严肃频道",
        favor: true
      },
      { divider: true, inset: true },
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "果子哥哥工作室",
        subtitle:
          "网络视频配音红人 代表作品《重庆方言版蜡笔小新、葫芦娃》等 微博签约自媒体",
        favor: true
      },
      { divider: true, inset: true },
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "晏小遨",
        subtitle: "搬运工。微博：ianthe94",
        favor: true
      },
      { divider: true, inset: true },
      {
        avatar: "https://picsum.photos/510/300?random",
        title: "谢春花本花",
        subtitle: "音乐人谢春花",
        favor: true
      }
    ],
    i: []
  }),
  methods: {
    favor(index) {
      if (this.peopleItems[index].favor == true) {
        if (confirm("您确定要取消关注吗？")) {
          this.peopleItems[index].favor = !this.peopleItems[index].favor;
        }
      } else {
        this.peopleItems[index].favor = !this.peopleItems[index].favor;
      }
    },
    playMusic() {}
  }
};
</script>
