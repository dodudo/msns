<template>
  <v-app>
    <v-card height="100vh" flat :img="require('../../assets/abg.jpg')">
      <v-container fluid fill-height>
        <TopBar></TopBar>
        <v-layout align-center justify-center>
          <v-flex xs12 sm8 md4>
            <v-card color="rgba(255,255,255,.7)" class="elevation-12">
              <v-toolbar dark color="#fb864c">
                <v-toolbar-title>欢迎登录</v-toolbar-title>
                <v-spacer></v-spacer>
              </v-toolbar>
              <v-card-text>
                <v-form ref="form" v-model="valid" lazy-validation>
                  <v-text-field
                    v-model="name"
                    :counter="10"
                    :rules="nameRules"
                    label="用户名"
                    required
                  ></v-text-field>

                  <v-text-field
                    v-model="password"
                    :rules="passwordRules"
                    label="密码"
                    required
                  ></v-text-field>

                  <div class="mx-auto">
                    <v-btn :disabled="!valid" color="#fb864c" @click="validate"
                      >登录</v-btn
                    >
                    <v-btn class="ma-2" color="#fb864c" @click="toRegister"
                      >没有账号？去注册</v-btn
                    >
                  </div>
                </v-form>
              </v-card-text>
            </v-card>
          </v-flex>
        </v-layout>
      </v-container>
    </v-card>
  </v-app>
</template>
<script>
import TopBar from "../../components/TopBar";
export default {
  data: () => ({
    valid: true,
    name: "",
    nameRules: [
      v => !!v || "Name is required",
      v => /^[a-zA-Z]{4,10}$/.test(v) || "只能输入4-10个以字母开头的字串"
    ],
    password: "",
    passwordRules: [
      v => !!v || "Password is required",
      v => /^(\w){6,20}$/.test(v) || "只能输入6-20个字母、数字、下划线"
    ]
  }),

  methods: {
    validate() {
      if (this.$refs.form.validate()) {
        this.snackbar = true;
      }
    },
    toRegister() {
      this.$router.push("/register");
    }
  },
  components: {
    TopBar
  }
};
</script>
