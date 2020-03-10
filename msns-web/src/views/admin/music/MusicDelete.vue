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
              <v-btn color="primary" dark class="mb-2" v-on="on">新增音乐</v-btn>
            </template>
            <v-card>
              <v-card-title>
                <span class="headline">{{ formTitle }}</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-form v-if="formTitle == '新增'" v-model="valid" ref="musicFileForm">
                    <v-row>
                      <v-col class="pa-0" cols="12" sm="12" md="12">
                        <v-file-input
                          :rules="[rules.required]"
                          accept="audio/*"
                          :error-messages="musicFileErr"
                          @change="musicFileChange"
                          label="请选择音乐文件"
                        ></v-file-input>
                      </v-col>
                    </v-row>
                  </v-form>
                  <v-form v-model="valid" ref="imageFileForm">
                    <v-row align="center">
                      <v-col class="pa-0" cols="10" sm="10" md="10">
                        <v-file-input @change="fileChange" accept="image/*" label="请选择音乐封面文件"></v-file-input>
                      </v-col>
                      <v-col cols="2">
                        <v-img
                          aspect-ratio="1"
                          max-width="80"
                          max-height="80"
                          :src="editedItem.musicCoverUrl == null ? coverUrl : editedItem.musicCoverUrl == null"
                        ></v-img>
                      </v-col>
                    </v-row>
                  </v-form>
                  <v-form v-model="valid" ref="typeForm">
                    <v-row>
                      <v-col class="pa-0" cols="12" sm="12" md="12">
                        <v-text-field
                          :rules="[rules.required, rules.counter]"
                          v-model="editedItem.musicName"
                          label="歌曲名"
                        ></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col class="pa-0" cols="12" sm="12" md="12">
                        <v-text-field v-model="editedItem.musicAuthor" label="歌手"></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col class="pa-0" cols="12" sm="12" md="12">
                        <v-text-field v-model="editedItem.musicAlbum" label="专辑"></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col class="pa-0" cols="12" sm="12" md="12">
                        <v-text-field v-model="editedItem.musicPublisDate" label="发行时间"></v-text-field>
                      </v-col>
                    </v-row>
                    <v-row>
                      <v-col class="pa-0" cols="12" sm="12" md="12">
                        <v-select
                          :value="
                            editedItem.musicTypeId == null
                              ? 1
                              : parseInt(editedItem.musicTypeId)
                          "
                          :items="musicTypes"
                          item-text="typeName"
                          item-value="id"
                          label="类型"
                        ></v-select>
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
      <!-- 专辑图片 -->
      <template v-slot:item.musicCoverUrl="{ item }">
        <v-img
          :src="
            item.musicCoverUrl == null
              ? require(`../../../assets/defaultCover.jpg`)
              : item.musicCoverUrl
          "
          width="40"
          height="40"
        ></v-img>
      </template>
      <!-- 音乐id -->
      <template v-slot:item.musicId="{ item }">
        <v-card width="100" class="content" color="rgba(0,0,0,0)" flat>{{ item.musicId }}</v-card>
      </template>
      <!-- 歌曲名 -->
      <template v-slot:item.musicName="{ item }">
        <v-card width="100" class="content" color="rgba(0,0,0,0)" flat>{{ item.musicName }}</v-card>
      </template>
      <!-- 歌手 -->
      <template v-slot:item.musicAuthor="{ item }">
        <v-card class="content" color="rgba(0,0,0,0)" flat>{{ item.musicAuthor }}</v-card>
      </template>
      <!-- 专辑名 -->
      <template v-slot:item.musicAlbum="{ item }">
        <v-card width="100" class="content" color="rgba(0,0,0,0)" flat>{{ item.musicAlbum }}</v-card>
      </template>
      <template v-slot:item.musicType="{ item }">
        <v-card color="rgba(0,0,0,0)" flat>
          {{
          musicTypes.find(function(i) {
          return i.id == item.musicTypeId;
          }) != null
          ? musicTypes.find(function(i) {
          return i.id == item.musicTypeId;
          }).typeName
          : "null"
          }}
        </v-card>
      </template>
      <template v-slot:item.musicPublisDate="{ item }">
        <v-card color="rgba(0,0,0,0)" flat>{{ item.musicPublisDate }}</v-card>
      </template>
      <template v-slot:item.musicPushDate="{ item }">
        <v-card color="rgba(0,0,0,0)" flat>
          {{
          formatDate(new Date(item.musicPushDate))
          }}
        </v-card>
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
    search: "", //搜索过滤
    totalType: 0, //总条数
    loading: true,
    pagination: {}, //分页信息
    rules: {
      required: value => !!value || "Required!",
      counter: v => (v && v.length <= 50) || "Max 50 characters"
    },
    headers: [
      { text: "id", value: "id" },
      { text: "音乐id", value: "musicId", sortable: false },
      { text: "音乐封面", value: "musicCoverUrl", sortable: false },
      { text: "歌曲名", value: "musicName" },
      { text: "歌手", value: "musicAuthor", sortable: false },
      { text: "专辑", value: "musicAlbum", sortable: false },
      { text: "发行日期", value: "musicPublisDate", sortable: false },
      { text: "上传日期", value: "musicPushDate" },
      { text: "时长", value: "musicLength", sortable: false },
      { text: "类型", value: "musicType", sortable: false },
      { text: "操作", value: "action", sortable: false }
    ],
    desserts: [],
    editedIndex: -1,
    editedItem: {},
    defaultItem: {},
    valid: true,
    typenameRules: [
      v => !!v || "类型名不能为空！",
      v => (v && v.length <= 10) || "类型名不能超过10位"
    ],
    coverUrl: "",
    coverFile: null,
    musicUrl: "",
    musicTags: {},
    musicTypes: [],
    musicFileErr: null
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
      this.$http("/music/type/all").then(resp => {
        this.musicTypes = resp.data;
      });
      this.$http
        .get("/music/page", {
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
    fileChange(e) {
      this.coverUrl = "";
      if (e == undefined) return;
      var reader = new FileReader();
      reader.readAsDataURL(e);
      var that = this;
      reader.onload = function(e) {
        that.coverUrl = e.target.result;
      };
      let formData = new FormData();
      formData.append("file", e);
      this.$http({
        method: "post",
        url: "http://localhost:10000/upload/image",
        data: formData,
        headers: {
          "Content-Type": "multipart/form-data"
        }
      }).then(resp => {
        this.editedItem.musicCoverUrl = resp.data;
        this.coverUrl = resp.data;
      });
      // console.log(this.coverUrl)
    },
    musicFileChange(e) {
      this.editedItem.musicName = "";
      if (e == undefined) return;
      // console.log(this.editedItem)
      let formData = new FormData();
      formData.append("file", e);
      this.$http({
        method: "post",
        url: "http://localhost:10000/upload/music",
        data: formData,
        headers: {
          "Content-Type": "multipart/form-data"
        }
      })
        .then(resp => {
          this.editedItem = resp.data;
          if (
            this.editedItem.musicTypeId == null ||
            this.editedItem.musicTypeId == ""
          ) {
            this.editedItem.musicTypeId = 1;
          }
          this.musicFileErr = null;
        })
        .catch(() => {
          this.musicFileErr = "不支持的文件类型!";
        });
    },

    changeStatus(item) {
      console.log(item);
      item.typeStatusBoolean = !item.typeStatusBoolean;
      this.$http({
        method: "put",
        url: `/dynamic/category`,
        data: item
      })
        .then(() => {
          this.initialize();
        })
        .catch(() => {});
    },
    deleteItem(item) {
      const index = this.desserts.indexOf(item);
      const dessert = this.desserts[index];
      console.log(dessert);
      if (confirm("您确定要删除吗? 注意：此删除为永久删除！！！")) {
        this.$http({
          method: "delete",
          url: `/music`,
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
    recover(item) {
      // console.log(item);
      const index = this.desserts.indexOf(item);
      if (confirm("您确定恢复此条记录吗？")) {
        item.status = 1;
        this.$http({
          method: "put",
          url: `/music`,
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
            url: `/music`,
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
          if (!this.$refs.musicFileForm.validate()) return;
          //新增分类操作
          this.desserts.push(this.editedItem);
          // console.log(this.desserts);
          const param = Object.assign(this.defaultItem, this.editedItem);
          //   console.log(param);
          param.status = "1";
          this.$http({
            method: "post",
            url: "/music",
            data: param
          })
            .then(() => {
              alert("保存成功!");
              this.initialize();
            })
            .catch(err => {
              alert("保存失败:" + err.response.data);
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
