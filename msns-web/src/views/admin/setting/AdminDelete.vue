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
              <v-btn color="primary" dark class="mb-2" v-on="on">新增管理员</v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-form v-model="valid" ref="typeForm">
                    <v-row>
                      <v-col class="pa-0" cols="12" sm="12" md="12">
                        <v-text-field
                          :rules="[rules.required, rules.counter]"
                          v-model="editedItem.aname"
                          :disabled="formTitle != '新增'"
                          label="管理员昵称"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col class="pa-0" cols="12" sm="12" md="12">
                        <v-select
                          :value="editedItem.sex==null ? '未知' : editedItem.sex"
                          :items="sexs"
                          label="性别"
                        ></v-select>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-menu
                        ref="menu"
                        v-model="menu"
                        :close-on-content-click="false"
                        transition="scale-transition"
                        offset-y
                        min-width="290px"
                      >
                        <template v-slot:activator="{ on }">
                          <v-text-field
                            v-model="editedItem.birthday"
                            label="Birthday date"
                            readonly
                            v-on="on"
                          ></v-text-field>
                        </template>
                        <v-date-picker
                          ref="picker"
                          v-model="editedItem.birthday"
                          :max="new Date().toISOString().substr(0, 10)"
                          min="1950-01-01"
                          @change="saveDate"
                        ></v-date-picker>
                      </v-menu>
                    </v-row>
                    <v-row>
                      <v-col class="pa-0" cols="12" sm="12" md="12">
                        <v-text-field
                          :rules="[rules.required]"
                          v-model="editedItem.phone"
                          label="手机"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col class="pa-0" cols="12" sm="12" md="12">
                        <v-text-field
                          :rules="[rules.required,rules.emailRule]"
                          v-model="editedItem.email"
                          label="邮箱"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-text-field
                        v-if="formTitle == '新增'"
                        v-model="editedItem.password"
                        :rules="[rules.required,rules.passwordRule]"
                        label="密码"
                        required
                      ></v-text-field>
                    </v-row>
                    <v-row>
                      <v-text-field
                        v-if="formTitle == '新增'"
                        @keydown.native="testPassword()"
                        @keyup.native="testPassword()"
                        @blur="testPassword()"
                        v-model="repassword"
                        :rules="[rules.required,rules.repasswordRule]"
                        label="确认密码"
                        required
                      ></v-text-field>
                    </v-row>

                    <v-row>
                      <v-col cols="12" sm="6" md="4">
                        <v-switch
                          v-model="editedItem.statusBoolean"
                          :value="editedItem.status=='1'? true: false"
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
      <template v-slot:item.status="{ item }">
        <v-switch inset @click.stop="changeStatus(item)" v-model="item.statusBoolean" class="mx-2"></v-switch>
      </template>
      <template v-slot:item.birthday="{ item }">
        <v-card class="content" color="rgba(0,0,0,0)" flat>{{ formatDate(new Date(item.birthday)) }}</v-card>
      </template>
      <template v-slot:item.action="{ item }">
        <v-icon class="mr-2" @click="recover(item)">mdi-autorenew</v-icon>
        <v-icon color="red" @click="deleteItem(item)">mdi-delete</v-icon>
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
    sexs: [
      { text: "未知", value: "未知" },
      { text: "男", value: "男" },
      { text: "女", value: "女" },
      { text: "保密", value: "保密" }
    ],
    search: "", //搜索过滤
    totalType: 0, //总条数
    loading: true,
    pagination: {}, //分页信息
    headers: [
      { text: "id", value: "id" },
      { text: "管理员昵称", value: "aname", sortable: true },
      { text: "性别", value: "sex", sortable: false },
      { text: "生日", value: "birthday", sortable: false },
      { text: "手机", value: "phone", sortable: false },
      { text: "邮箱", value: "email", sortable: false },
      { text: "操作", value: "action", sortable: false }
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {},
    defaultItem: {},
    valid: true,
    repassword: null,
    rules: {
      required: value => !!value || "Required!",
      counter: v => (v && v.length <= 10) || "Max 10 characters",
      emailRule: v => /.+@.+\..+/.test(v) || "E-mail格式不正确",
      passwordRule: v =>
        /^(\w){6,20}$/.test(v) || "只能输入6-20个字母、数字、下划线",
      repasswordRule: "",
      phone: v => (v && v.length == 11) || "手机号为11位"
    }
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
        .get("/admin/page", {
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
          url: `/admin`,
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
      console.log(dessert);
      if (confirm("您确定要删除吗?注意：此删除为永久删除！！！")) {
        this.$http({
          method: "delete",
          url: `/admin`,
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
      this.editedItem.status = this.editedItem.statusBoolean ? "1" : "0";
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
            url: `/admin`,
            data: obj
          })
            .then(() => {
              this.initialize();
              alert("保存成功!");
            })
            .catch(err => {
              alert("保存失败" + err.response.data);
            });
        } else {
          //新增分类操作
          // this.desserts.push(this.editedItem);
          // console.log(this.desserts);
          const param = Object.assign(this.defaultItem, this.editedItem);
          //   console.log(param);
          this.$http({
            method: "post",
            url: "/admin",
            data: param
          })
            .then(() => {
              this.initialize();
              alert("保存成功!");
            })
            .catch(err => {
              alert("保存失败" + err.response.data);
            });
        }
        this.close();
      }
    },
    formatDate(time) {
      var date = new Date(time);
      return formatDate(date, "yyyy-MM-dd");
    },
    testPassword() {
      if (!/^(\w){6,20}$/.test(this.repassword)) {
        this.rules.repasswordRule = "只能输入6-20个字母、数字、下划线";
      } else if (this.editedItem.password != this.repassword) {
        this.rules.repasswordRule = "密码不一致";
      } else {
        this.rules.repasswordRule = true;
      }
    }
  }
};
</script>
