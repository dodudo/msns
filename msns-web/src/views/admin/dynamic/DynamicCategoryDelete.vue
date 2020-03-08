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
          <v-dialog v-model="dialog" max-width="500px">
            <template v-slot:activator="{ on }">
              <v-btn color="primary" dark class="mb-2" v-on="on"
                >新增分类</v-btn
              >
            </template>
            <v-card>
              <v-card-title>
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-form v-model="valid" ref="typeForm">
                    <v-row>
                      <v-col cols="12" sm="6" md="4">
                        <v-text-field
                          required
                          :rules="typenameRules"
                          v-model="editedItem.typeName"
                          label="类型名"
                        ></v-text-field>
                      </v-col>
                      <v-col cols="12" sm="6" md="4">
                        <v-switch
                          required
                          v-model="editedItem.typeStatusBoolean"
                          label="是否启用"
                        ></v-switch>
                      </v-col>
                    </v-row>
                  </v-form>
                </v-container>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="close">Cancel</v-btn>
                <v-btn color="blue darken-1" text @click="save">Save</v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
        </v-toolbar>
      </template>
      <template v-slot:item.typeStatusBoolean="{}">已删除</template>
      <template v-slot:item.action="{ item }">
        <v-icon class="mr-2" @click="recover(item)">mdi-autorenew</v-icon>
        <v-icon @click="deleteItem(item)">mdi-delete</v-icon>
      </template>
    </v-data-table>
  </v-card>
</template>
<script>
export default {
  data: () => ({
    dialog: false,
    selected: [],
    search: "", //搜索过滤
    totalType: 0, //总条数
    loading: true,
    pagination: {}, //分页信息
    headers: [
      { text: "id", value: "id" },
      { text: "类型名", value: "typeName", sortable: false },
      { text: "状态", value: "typeStatusBoolean", sortable: false },
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
        .get("/dynamic/category/page", {
          params: {
            key: this.search,
            page: this.pagination.page, //当前页
            rows: this.pagination.itemsPerPage, //每页大小
            sortBy: this.pagination.sortBy, //排序字段
            desc: this.pagination.sortDesc, //是否降序
            isAll: false
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
    recover(item) {
      // console.log(item);
      const index = this.desserts.indexOf(item);
      if (confirm("您确定恢复此条记录吗？")) {
        item.typeStatus = 0;
        this.$http({
          method: "put",
          url: `/dynamic/category`,
          data: item
        })
          .then(() => {
            this.initialize();
            this.desserts.splice(index, 1);
          })
          .catch(() => {});
      }
    },
    deleteItem(item) {
      const index = this.desserts.indexOf(item);
      const dessert = this.desserts[index];
      dessert.typeStatus = -1;
      console.log(dessert);
      if (confirm("您确定要删除吗? 注意：此删除为永久删除！！！")) {
        this.$http({
          method: "delete",
          url: `/dynamic/category`,
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
    }
  }
};
</script>
