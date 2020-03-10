<template>
  <v-card flat>
    <v-data-table
      :headers="headers"
      :items="desserts"
      :options.sync="pagination"
      item-key="id"
      :server-items-length="totalType"
      v-model="selected"
      no-data-text="啊，没有数据呢"
      :loading="loading"
      class="elevation-1 mx-5"
    >
      <template v-slot:top>
        <v-toolbar width="400" flat color="white">
          <v-text-field
            v-model="search"
            prepend-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          ></v-text-field>
        </v-toolbar>
      </template>
      <template v-slot:item.status="{ item }">
        <v-switch inset @click.stop="changeStatus(item)" v-model="item.statusBoolean" class="mx-2"></v-switch>
      </template>
      <template v-slot:item.birthday="{ item }">
        <v-card class="content" color="rgba(0,0,0,0)" flat>{{ formatDate(new Date(item.birthday)) }}</v-card>
      </template>
      <template v-slot:item.avatarUrl="{ item }">
        <v-avatar width="40" height="40">
          <img
            :src="
            item.avatarUrl == null
              ? require(`../../../assets/defaultCover.jpg`)
              : item.avatarUrl
          "
            :alt="item.uname"
          />
        </v-avatar>
      </template>
      <template v-slot:item.action="{ item }">
        <v-icon class="mr-2" @click="recover(item)">mdi-autorenew</v-icon>
      </template>
    </v-data-table>
  </v-card>
</template>
<style scoped>
.content {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
</style>
<script>
import { formatDate } from "../../../assets/formatDate";
export default {
  data: () => ({
    dialog: false,
    selected: [],
    menu: false,
    search: "", //搜索过滤
    totalType: 0, //总条数
    loading: true,
    pagination: {}, //分页信息
    headers: [
      { text: "id", value: "id" },
      { text: "头像", value: "avatarUrl", sortable: false },
      { text: "用户名", value: "uname", sortable: true },
      { text: "性别", value: "sex", sortable: false },
      { text: "生日", value: "birthday", sortable: false },
      { text: "手机", value: "phone", sortable: false },
      { text: "邮箱", value: "email", sortable: false },
      { text: "恢复", value: "action", sortable: false }
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {},
    defaultItem: {}
  }),

  computed: {
    formTitle() {
      return this.editedIndex === -1 ? "新增" : "编辑";
    }
  },

  watch: {
    dialog(val) {
      val || this.close();
    },
    pagination: {
      //监视pagination属性的变化
      deep: true, // deep为true，会监视pagination的属性及属性中的对象属性变化
      handler() {
        // 变化后的回调函数，这里我们再次调用initialize即可
        this.initialize();
      }
    },
    search: {
      handler() {
        // 变化后的回调函数，这里我们再次调用initialize即可
        this.initialize();
      }
    }
  },

  mounted() {
    //渲染后执行
    this.initialize();
  },

  methods: {
    saveDate(date) {
      this.$refs.menu.save(date);
    },
    initialize() {
      this.$http
        .get("/user/page", {
          params: {
            key: this.search,
            page: this.pagination.page, //当前页
            rows: this.pagination.itemsPerPage, //每页大小
            sortBy: this.pagination.sortBy, //排序字段
            desc: this.pagination.sortDesc, //是否降序
            isAll: false //是否查询所有未删除的
          },
          paramsSerializer: params => {
            return this.$qs.stringify(params, { indices: false });
          }
        })
        .then(resp => {
          // console.log(resp.data)
          this.loading = false;
          this.desserts = resp.data.items;
          this.totalType = resp.data.total;
          for (var i = 0; i < this.desserts.length; i++) {
            this.desserts[i].statusBoolean =
              this.desserts[i].status == "1" ? true : false;
          }
        })
        .catch(err => {
          console.log(err);
        });
    },
    recover(item) {
      // console.log(item);
      const index = this.desserts.indexOf(item);
      if (confirm("您确定恢复此条记录吗？")) {
        item.status = 0;
        this.$http({
          method: "put",
          url: `/user`,
          data: item
        })
          .then(() => {
            this.initialize();
            this.desserts.splice(index, 1);
          })
          .catch(() => {});
      }
    },
    close() {
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },
    formatDate(time) {
      var date = new Date(time);
      return formatDate(date, "yyyy-MM-dd");
    }
  }
};
</script>
