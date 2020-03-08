<template>
  <v-card flat>
    <v-data-table
      :headers="headers"
      :items="desserts"
      :options.sync="pagination"
      item-key="id"
      v-model="selected"
      no-data-text="啊，没有数据呢"
      :loading="loading"
      :server-items-length="totalType"
      class="elevation-1 mx-5 "
    >
      <template v-slot:top>
        <v-toolbar flat color="white">
          <v-text-field
            v-model="search"
            prepend-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
          ></v-text-field>
          <!--          <v-btn @click="initialize" icon><v-icon>mdi-magnify</v-icon></v-btn>-->
          <v-spacer></v-spacer>
        </v-toolbar>
      </template>
      <template v-slot:item.publishDate="{ item }">
        <v-card color="rgba(0,0,0,0)" flat>{{
          formatDate(new Date(item.publishDate))
        }}</v-card>
      </template>
      <template v-slot:item.dynamicContent="{ item }">
        <v-card color="rgba(0,0,0,0)" width="200" flat class="content">{{
          item.dynamicContent
        }}</v-card>
      </template>
      <template v-slot:item.typeStatusBoolean="{ item }">
        <v-switch
          inset
          v-model="item.typeStatusBoolean"
          @click.stop="changeStatus(item)"
          class="mx-2"
        ></v-switch>
      </template>
      <template v-slot:item.action="{ item }">
        <v-icon @click="deleteItem(item)">mdi-delete</v-icon>
      </template>
      <template v-slot:item.reportCount="{ item }">
        <td>{{ item.reportCount }}</td>
      </template>
      <template v-slot:no-data>
        <v-btn color="primary" @click="initialize">Reset</v-btn>
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
    sortBy: "id",
    sortDesc: false,
    selected: [],
    search: "", //搜索过滤
    totalType: 0, //总条数
    loading: true,
    pagination: {}, //分页信息
    headers: [
      { text: "id", value: "id" },
      {
        text: "动态内容",
        width: 200,
        value: "dynamicContent",
        sortable: false
      },
      { text: "发布者", value: "uname", sortable: false },
      { text: "发布日期", value: "publishDate" },
      { text: "举报量", value: "reportCount" },
      { text: "操作", value: "action", sortable: false }
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {},
    defaultItem: { typeName: "", typeStatusBoolean: false },
    valid: true,
    typenameRules: [
      v => !!v || "类型名不能为空！",
      v => (v && v.length <= 10) || "类型名不能超过10位"
    ]
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
    initialize() {
      this.$http
        .get("/dynamic/report/page", {
          params: {
            key: this.search,
            page: this.pagination.page, //当前页
            rows: this.pagination.itemsPerPage, //每页大小
            sortBy: this.pagination.sortBy, //排序字段
            desc: this.pagination.sortDesc, //是否降序
            isAll: true //是否查询所有未删除的
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
          if (this.desserts == null) {
            console.log("");
          }
        })
        .catch(err => {
          console.log(err);
        });
    },

    editItem(item) {
      this.editedIndex = this.desserts.indexOf(item);
      this.editedItem = Object.assign({}, item);
      this.dialog = true;
    },
    deleteItem(item) {
      const index = this.desserts.indexOf(item);
      const dessert = this.desserts[index];
      dessert.status = -1;
      console.log(dessert);
      if (confirm("您确定要删除吗?")) {
        this.$http({
          method: "put",
          url: `/dynamic`,
          data: dessert
        })
          .then(() => {
            this.initialize();
            alert("删除成功!");
            this.desserts.splice(index, 1);
          })
          .catch(() => {
            alert("删除失败");
          });
      }
    },

    close() {
      this.dialog = false;
      setTimeout(() => {
        this.editedItem = Object.assign({}, this.defaultItem);
        this.editedIndex = -1;
      }, 300);
    },

    save() {
      if (this.$refs.typeForm.validate()) {
        this.snackbar = true;
        if (this.editedIndex > -1) {
          //编辑操作
          const obj = Object.assign(
            this.desserts[this.editedIndex],
            this.editedItem
          );
          console.log(obj);
          this.$http({
            method: "put",
            url: `/dynamic/category`,
            data: obj
          })
            .then(() => {
              this.initialize();
              alert("保存成功!");
            })
            .catch(() => {
              alert("保存失败");
            });
        } else {
          //新增分类操作
          // this.desserts.push(this.editedItem);
          // console.log(this.desserts);
          const param = Object.assign(this.defaultItem, this.editedItem);
          console.log(param);
          this.$http({
            method: "post",
            url: "/dynamic/category",
            data: param
          })
            .then(() => {
              this.initialize();
              alert("保存成功!");
            })
            .catch(() => {
              alert("保存失败");
            });
        }
        this.close();
      }
    },
    formatDate(time) {
      var date = new Date(time);
      return formatDate(date, "yyyy-MM-dd hh:mm");
    }
  }
};
</script>
