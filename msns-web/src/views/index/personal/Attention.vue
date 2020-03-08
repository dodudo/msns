<template>
  <v-card width="1112" class="mt-2">
    <v-card class="mx-2" flat height="54">
      <v-card-title>全部关注</v-card-title>
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
