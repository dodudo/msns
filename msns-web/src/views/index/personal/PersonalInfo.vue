<template>
  <v-card class="mt-3 pa-2" width="1114">
    <v-row align="center">
      <v-col align-self="center">
        <v-card flat height="40">
          <h4 class="ml-2" style="line-height:40px">我的头像</h4>
        </v-card>
        <v-divider></v-divider>
      </v-col>
    </v-row>
    <v-row align="center" justify="center">
      <v-col cols="1">
        <v-avatar size="120">
          <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John" />
        </v-avatar>
      </v-col>
      <v-col cols="1">
        <v-btn style="text-align:center;" dark fab class="ml-10" color="red">
          <span style="white-space:normal;width:45px">更换头像</span>
        </v-btn>
      </v-col>
    </v-row>
    <v-row align="center">
      <v-col align-self="center">
        <v-card flat height="40">
          <h4 class="ml-2" style="line-height:40px">我的信息</h4>
        </v-card>
        <v-divider></v-divider>
      </v-col>
    </v-row>
    <v-row align="center">
      <v-col class="ml-6" style="text-align:right" cols="1">昵称:</v-col>
      <v-col cols="4">
        <v-text-field
          solo
          hide-details
          flat
          dense
          color="#EF5350"
          outlined
          label="昵称"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row align="center">
      <v-col class="ml-6" style="text-align:right" cols="1">用户名:</v-col>
      <v-col cols="4">
        <v-text-field
          solo
          disabled
          hide-details
          flat
          dense
          color="#EF5350"
          label="用户名"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row align="center">
      <v-col class="ml-6" style="text-align:right" cols="1">我的签名:</v-col>
      <v-col cols="4">
        <v-text-field
          solo
          hide-details
          flat
          dense
          color="#EF5350"
          outlined
          label="签名"
        ></v-text-field>
      </v-col>
    </v-row>
    <v-row align="center">
      <v-col class="ml-6" style="text-align:right" cols="1">性别:</v-col>
      <v-col cols="4">
        <v-radio-group v-model="row" row>
          <v-radio label="男" value="boy"></v-radio>
          <v-radio label="女" value="girl"></v-radio>
          <v-radio label="保密" value="security"></v-radio>
        </v-radio-group>
      </v-col>
    </v-row>
    <v-row align="center">
      <v-col class="ml-6" style="text-align:right" cols="1">生日:</v-col>
      <v-col cols="4">
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
              v-model="date"
              label="Birthday date"
              readonly
              v-on="on"
            ></v-text-field>
          </template>
          <v-date-picker
            ref="picker"
            v-model="date"
            :max="new Date().toISOString().substr(0, 10)"
            min="1950-01-01"
            @change="save"
          ></v-date-picker>
        </v-menu>
      </v-col>
    </v-row>
    <v-row align="center">
      <v-col align-self="center">
        <v-card flat height="40">
          <h4 class="ml-2" style="line-height:40px">账号安全</h4>
        </v-card>
        <v-divider></v-divider>
      </v-col>
    </v-row>
    <v-form ref="form" v-model="valid">
      <v-row align="center">
        <v-col cols="1" class="ml-6 mb-6" style="text-align:right"
          >旧密码:</v-col
        >
        <v-col class="py-0" cols="4">
          <v-text-field
            solo
            outlined
            flat
            dense
            v-model="oldPassword"
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[oldPasswordRules.min]"
            :type="show1 ? 'text' : 'password'"
            name="oldPassword"
            label="请输入旧密码"
            counter
            @click:append="show1 = !show1"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row align="center">
        <v-col cols="1" class="ml-6 mb-6" style="text-align:right"
          >新密码:</v-col
        >
        <v-col class="py-0" cols="4">
          <v-text-field
            solo
            outlined
            flat
            dense
            v-model="newPassword"
            :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="[newPasswordRules.min]"
            :type="show2 ? 'text' : 'password'"
            name="input-10-1"
            label="请输入新密码"
            counter
            @click:append="show2 = !show2"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row align="center">
        <v-col cols="1" class="ml-6 mb-6 caption" style="text-align:right"
          >确认新密码:</v-col
        >
        <v-col class="py-0" cols="4">
          <v-text-field
            solo
            outlined
            flat
            dense
            v-model="reNewPassword"
            :append-icon="show3 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="reNewPasswordRules"
            :type="show3 ? 'text' : 'password'"
            name="input-10-1"
            label="请再次输入新密码"
            counter
            @keydown.native="testPassword()"
            @keyup.native="testPassword()"
            @blur="testPassword()"
            @click:append="show3 = !show3"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-row justify="center" align="center">
        <v-col cols="7">
          <v-btn color="red" :disabled="!valid" width="80" @click="submit()"
            >保存</v-btn
          >
        </v-col>
      </v-row>
    </v-form>
  </v-card>
</template>
<script>
export default {
  data: () => ({
    date: null,
    menu: false,
    show1: false,
    show2: false,
    show3: false,
    oldPassword: "",
    newPassword: "",
    reNewPassword: "",
    row: "",
    btnStatus: false,
    reNewpasswordErr: [],
    oldPasswordRules: {
      min: v => v.length >= 6 || "密码最少6位"
    },
    newPasswordRules: { min: v => v.length >= 6 || "密码最少6位" },
    reNewPasswordRules: [],
    valid: true
  }),
  watch: {
    menu(val) {
      val && setTimeout(() => (this.$refs.picker.activePicker = "YEAR"));
    }
  },
  methods: {
    save(date) {
      this.$refs.menu.save(date);
    },
    testPassword() {
      if (this.reNewPassword == "") {
        this.reNewPasswordRules = ["长度不能为0"];
      } else {
        if (!/^(\w){6,20}$/.test(this.reNewPassword)) {
          this.reNewPasswordRules = ["只能输入6-20个字母、数字、下划线"];
        } else if (this.newPassword != this.reNewPassword) {
          this.reNewPasswordRules = ["密码不一致"];
        } else {
          this.reNewPasswordRules = [true];
        }
      }
    },
    submit() {
      if (this.$refs.form.validate()) {
        this.snackbar = true;
      }
    }
  }
};
</script>
