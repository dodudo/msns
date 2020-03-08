<template>
  <v-card width="1112" class="mt-2">
    <v-card flat class="mx-2" height="54">
      <v-card-title>我的粉丝</v-card-title>
    </v-card>
    <PeopleList></PeopleList>
  </v-card>
</template>
<script>
import PeopleList from "../../../components/PeopleList";
export default {
  components: {
    PeopleList
  }
};
</script>
