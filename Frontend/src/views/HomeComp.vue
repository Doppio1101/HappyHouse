<template>
  <div>
    <div style="width=0px; height=0px;" id="map"></div>
    <v-container>
      <span id="title">인기있는 매물을 확인해보세요!</span>
      <v-carousel
        cycle
        height="500"
        hide-delimiter-background
        show-arrows-on-hover
        interval="4000"
      >
        <v-carousel-item
          v-for="(item, i) in items"
          :key="i"
          :src="item.src"
          height="100%"
        >
          <!-- <v-sheet :src="item.src" height="100%">
          <v-row class="fill-height" align="center" justify="center"> </v-row>
        </v-sheet> -->
        </v-carousel-item>
      </v-carousel>

      <v-container fluid>
        <v-row dense>
          <v-col v-for="card in cards" :key="card.title" :cols="card.flex">
            <v-card>
              <v-img
                :src="card.src"
                class="white--text align-end"
                gradient="to bottom, rgba(0,0,0,.1), rgba(0,0,0,.5)"
                height="200px"
              >
                <v-card-title v-text="card.title"></v-card-title>
              </v-img>

              <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn icon>
                  <v-icon>mdi-heart</v-icon>
                </v-btn>

                <v-btn icon>
                  <v-icon>mdi-bookmark</v-icon>
                </v-btn>

                <v-btn icon>
                  <v-icon>mdi-share-variant</v-icon>
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "HomeComp",
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");

      script.onload = () => {
        kakao.maps.load(this.initMap);
        console.log(kakao, window.kakao);
      };
      script.src =
        "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=de0da876eb57458c462a9b4a3a457b23&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      let container = document.getElementById("map");
      let options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        //관심지역 첫번째의 좌표가 들어가야 함.
        level: 6,
      };

      this.map = new kakao.maps.Map(container, options);
      this.geocoder = new kakao.maps.services.Geocoder();
    },
  },
  data() {
    return {
      items: [
        {
          src: "https://img.hankyung.com/photo/202102/AD.25354797.1.jpg",
        },
        {
          src: "https://img4.daumcdn.net/thumb/R658x0.q70/?fname=https://t1.daumcdn.net/news/202105/23/ohouse/20210523204629025rrrm.jpg",
        },
        {
          src: "https://img.kr.news.samsung.com/kr/wp-content/uploads/2016/07/%ED%81%AC%EA%B8%B0%EB%B3%80%ED%99%982_1-2-0.jpg",
        },
      ],
      cards: [
        {
          title: "조명이 예쁜 주택",
          src: "https://cdn.vuetifyjs.com/images/cards/house.jpg",
          flex: 4,
        },
        {
          title: "무지개 아파트",
          src: "https://t3.daumcdn.net/thumb/R720x0/?fname=http://t1.daumcdn.net/brunch/service/user/6P0U/image/aH-hxvPY_pc6_ls05648j42raXI",
          flex: 4,
        },
        {
          title: "공원이 있는 자연친화적 아파트",
          src: "https://lh3.googleusercontent.com/proxy/cowYbDtKU9Lkl5lOJ1QaXkOk4fdvhrMgcDc97EdT6Sgd-FlqR8nzDUj5_Qn34Wrr3EXIlJJ2timmvhcggzRsPo8RoST0XvCxRvdoqazhcV0wwZjipS0BLX8",
          flex: 4,
        },
      ],
    };
  },
};
</script>

<style></style>
