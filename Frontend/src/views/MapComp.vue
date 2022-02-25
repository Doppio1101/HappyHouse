<template>
  <div id="search">
    <v-container class="grey lighten-5">
        
        <v-row no-gutters>
            <v-col :cols="7">
                <v-row>
                    <v-container>
                    <v-row><span style="font-size: 40px">지도</span></v-row>
                    <v-row><div id="map" style="width:90%; height:500px;"></div></v-row>
                    </v-container>
                </v-row>
                <v-row v-if="aptAry.length > 0">
                    <v-simple-table
                        fixed-header
                        height="300px"
                    >
                        <template v-slot:default>
                        <thead>
                            <tr>
                            <th class="text-left">
                                아파트 이름
                            </th>
                            <th class="text-left">
                                가격
                            </th>
                            <th class="text-left">
                                면적
                            </th>
                            <th class="text-left">
                                층
                            </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr
                            v-for="aptInfo in aptAry"
                            :key="aptInfo.name"
                            >
                            <td>{{ aptInfo.aptName }}</td>
                            <td>{{ aptInfo.dealAmount }}만원</td>
                            <td>{{ parseInt(aptInfo.area / 3.3) }}평</td>
                            <td>{{ aptInfo.floor }}층</td>
                            </tr>
                        </tbody>
                        </template>
                    </v-simple-table>
                </v-row>
            </v-col>
            <v-col :cols="5">
                <v-container>
                    <v-row>
                        <v-col :cols="4">
                            <v-select
                            v-model="sidoSelect"
                            :items="sidoAry"
                            item-text="sidoName"
                            item-value="sidoCode"
                            label="시도선택"
                            persistent-hint
                            return-object
                            single-line
                            @change="changeSido"
                            ></v-select>
                        </v-col>
                        <v-col :cols="4">
                            <v-select
                            v-model="gugunSelect"
                            :items="gugunAry"
                            item-text="gugunName"
                            item-value="gugunCode"
                            label="구군선택"
                            persistent-hint
                            return-object
                            single-line
                            @change="changeGugun"
                            ></v-select>
                        </v-col>
                        <v-col :cols="4">
                            <v-select
                            v-model="dongSelect"
                            :items="dongAry"
                            item-text="dongName"
                            item-value="dongCode"
                            label="법정동선택"
                            persistent-hint
                            return-object
                            single-line
                            @change="changeDong"
                            ></v-select>
                        </v-col>
                    </v-row>
                    <v-row v-if="extraAttentionInfo != null">
                        <v-col :cols="12">
                          <v-card>
                          <v-tabs
                          v-model="tab"
                            fixed-tabs
                            background-color="indigo"
                            dark
                          >
                            <v-tab style="bolder;" :href="'#tab-'+index" v-for="(item, index) in extraAttentionInfo" :key="index" @click="addAttention(item)">
                                {{item.nickname}}
                                
                              </v-tab>
                              <v-tab v-if="extraAttentionInfo.length < 3" :href="'#tab-plus'">
                                <v-icon>mdi-plus</v-icon>
                              </v-tab>
                          </v-tabs>
                          <v-tabs-items v-model="tab" fixed-tabs>
                              <v-tab-item
                                v-for="(item, i) in extraAttentionInfo"
                                :key="i"
                                :value="'tab-' + i"
                                :id="'tab-' + i"
                              >
                                <v-card flat>
                                  <v-card-text>
                                    <v-col :cols="12"><h2>{{ item.address }}</h2></v-col>
                                    <v-row>
                                    <v-col :cols="9"></v-col>
                                    <v-col :cols="3">
                                    <!-- <v-btn
                                      class="mx-2"
                                      small
                                      fab
                                      dark
                                      outlined
                                      color="blue-grey darken-1"
                                      @click="updateAtten"
                                    >
                                      <v-icon dark > mdi-pencil </v-icon>
                                    </v-btn> -->
                                    <v-btn
                                      class="mx-2"
                                      small
                                      fab
                                      dark
                                      color="red accent-4"
                                      @click="delAtten(item)"
                                    >
                                      <v-icon dark > mdi-delete </v-icon>
                                    </v-btn>
                                    </v-col>
                                    </v-row>
                                  </v-card-text>
                                </v-card>
                              </v-tab-item>

                              <!-- 추가 부분 -->
                              <v-tab-item
                                
                                :id="'tab-plus'"
                              >
                                <v-card flat>
                                  <v-card-text>
                                    <v-col cols="12" sm="6">
                                      <v-text-field
                                        label="Nickname1"
                                        v-model="addAtten.nickname"
                                        filled
                                        rounded
                                        dense
                                      ></v-text-field>
                                    </v-col>
                                    <v-col cols="12">
                                      <v-text-field
                                        label="Address"
                                        v-model="addAtten.totalAddress"
                                        @click="searchAttention()"
                                      ></v-text-field>
                                    </v-col>
                                    <v-row>
                                    <v-col :cols="8"></v-col>
                                    <v-col :cols="4">
                                    <v-btn
                                      class="mx-2"
                                      small
                                      fab
                                      dark
                                      outlined
                                      color="blue-grey darken-1"
                                      @click="registAttention"
                                    >
                                      <v-icon dark > mdi-plus </v-icon>
                                    </v-btn>
                                    <v-btn
                                      class="mx-2"
                                      small
                                      fab
                                      dark
                                      outlined
                                      color="red accent-4"
                                      @click="cancel"
                                    >
                                      <v-icon dark > mdi-minus-circle </v-icon>
                                    </v-btn>
                                    </v-col>
                                    </v-row>
                                  </v-card-text>
                                </v-card>
                              </v-tab-item>



                            </v-tabs-items>
                            </v-card>
                        </v-col>
                        
                    </v-row>
                    <v-row>
                        <v-col :cols="10">
                          <v-text-field
                            label="Address"
                            v-model="atten.totalAddress"
                            @click="execDaumPostcode"
                          ></v-text-field>
                        </v-col>
                        <v-col :cols="2">
                          <v-btn
                            class="mx-2"
                            small
                            fab
                            dark
                            color="indigo"
                            @click="execDaumPostcode"
                          >
                            <v-icon dark > mdi-magnify </v-icon>
                          </v-btn>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col :cols="3">
                          <v-btn
                            class="mx-2"
                            large
                            fab
                            dark
                            color="indigo"
                            @click="check(1)"
                          >
                            1km
                          </v-btn>
                        </v-col>
                        <v-col :cols="3">
                          <v-btn
                            class="mx-2"
                            large
                            fab
                            dark
                            color="indigo"
                            @click="check(3)"
                          >
                            3km
                          </v-btn>
                        </v-col>
                        <v-col :cols="6">

                        </v-col>
                    </v-row>
                </v-container>
            </v-col>
        </v-row>
    
  </v-container>
  </div>
</template>

<script>
import {localhost} from "@/js/common.js";
import { mapState, mapActions, mapMutations } from "vuex";
var markers = [];
export default {
    data() {
        return {
            sidoAry : [
                
            ],
            sidoSelect : {

            },
            
            gugunAry : [
                
            ],
            gugunSelect : {},
            
            dongAry : [
                
            ],
            atten: {
                userid: "",
                bname: "",
                sigungucod: "",
                nickname: "",
                postnum: "",
                address: "",
                detailaddress: "",
                totalAddress: "",
                lat: "",
                lng: "",
            },
            addAtten: {
                userid: "",
                bname: "",
                sigungucod: "",
                nickname: "",
                postnum: "",
                address: "",
                detailaddress: "",
                totalAddress: "",
                lat: "",
                lng: "",
            },
            extraAttentionInfo: [
              {
                userid: "",
                bname: "",
                sigungucod: "",
                nickname: "",
                postnum: "",
                address: "",
                detailaddress: "",
                totalAddress: "",
                lat: "",
                lng: "",
              },
            ],
            selectAttention:{
              lat : "",
              lng : "",
            },
            dongSelect : {},
            extraAry : [],
            aptAry: [],
            tab: 0,
            text: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.',
      
        }
    },
    computed:{
      ...mapState(["userInfo", "attentionInfo", "isLogin", "isLoginError"]),

    },
    async mounted() {

    let that = this;
    await this.makeKakao();

    //카카오 객체 생김
    // location.reload();

    const script = document.createElement("script");
    script.src =
      "//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js";
    document.head.appendChild(script);
    
    if(this.attentionInfo){
      await this.setting(that);
    }
    await this.changeGugun();

  },
  async created() {
      console.log(this.getDistanceFromLatLonInKm());
      this.initSido();
      // let that = this;
      this.extraAttentionInfo = this.attentionInfo;
      console.log(this.extraAttentionInfo);
      if(this.extraAttentionInfo[0].sigungucode){
        let sigugunCode = this.extraAttentionInfo[0].sigungucode;
        let sidoCode = sigugunCode[0]+""+sigugunCode[1];
        console.log("sidoCode: ",sidoCode)
        this.sidoSelect = {
          sidoCode : sidoCode,
          sicoName : "대기",
        }
        await this.changeSido();
        for(let i=0; i<this.sidoAry.length; i++){
          if(sidoCode == this.sidoAry[i].sidoCode){
            this.sidoSelect = {
              sidoCode : sidoCode,
              sidoName : this.sidoAry[i].sidoName,
            }
            break;
          }
        }
        
        this.gugunSelect = {
          gugunCode: sigugunCode,
          gugunName: "대기"
        }
        await this.changeGugun();
        for(let i=0; i<this.gugunAry.length; i++){
          if(sigugunCode == this.gugunAry[i].gugunCode){
            this.gugunSelect = {
              gugunCode : sigugunCode,
              gugunName : this.gugunAry[i].gugunName,
            }
            break;
          }
        }

      }
      this.extraAttentionInfo.push();
      // this.getApt("11110");//his.gugunCode);//gugun업데이트 할 때 주석 해제하고 실행 한번만
      
  },
  methods: {
    ...mapActions(["getAttentionInfo"]),
    ...mapMutations(["SET_ATTENTION_INFO"]),

    async updateAtten(){

    },
    async delAtten(item){

      console.log("in", this.extraAttentionInfo);
      console.log(this.extraAttentionInfo.length);
      let extra = [];
      for(let i=0; i<this.extraAttentionInfo.length; i++){
        if(this.extraAttentionInfo[i].no == item.no){
          continue;
        }else{
          extra.push(this.extraAttentionInfo[i])
        }
      }
      console.log("extra",extra);

      await localhost({
          method: "delete",
          url: "/attention/delete/" + this.userInfo.userid,
        }).then((data) => {
          console.log("삭제 완료?",data);
        });
      console.log(extra.length);
      for (let i = 0; i < extra.length; i++) {
            extra[i].userid = this.userInfo.userid;
            await localhost({
              method: "post",
              url: "/attention/regist",
              data: extra[i],
            }).then((data) => {
              console.log("재입력",data);
            });
      }
      let token = sessionStorage.getItem("access-token");
        if (this.isLogin) {
          await this.getAttentionInfo(token);
        }
        // this.SET_ATTENTION_INFO(this.extra);
        // console.log(this.extraAttentionInfo);
        // console.log(this.attentionInfo);
        this.extraAttentionInfo = this.attentionInfo;
      this.extraAttentionInfo.push();
      this.tab = 0;
    },
    makeKakao(){
      if (window.kakao && window.kakao.maps) {
        this.initMap();
      } else {
        const script = document.createElement("script");
        
        script.onload = () => {kakao.maps.load(this.initMap);
          console.log(kakao, window.kakao);
          
        };
        script.src =
          "//dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=de0da876eb57458c462a9b4a3a457b23&libraries=services";
        document.head.appendChild(script);
      }
    },
    setting(that){
      // setTimeout(function(){
        this.removeMarker();
        this.geocoder.addressSearch(this.attentionInfo[0].address, function(result, status) {
          // console.log(this)

              // 정상적으로 검색이 완료됐으면 
          if (status === kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
              
              var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png'; // 마커이미지의 주소입니다    
              var imageSize = new kakao.maps.Size(24, 35); 
              var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 

              //lat : y좌표, lng : x좌표
              // console.log("y: "+result[0].y + "x: "+result[0].x);
              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new kakao.maps.Marker({
                  map: that.map,
                  position: coords,
                  image: markerImage
              });
              
              marker.setMap(that.map);
              markers.push(marker);
              that.map.setCenter(coords);

          } 
        });
      // }
      // ,5000);
    },
    initMap() {
      let container = document.getElementById("map");
      let options = {
        center: new kakao.maps.LatLng(33.450701, 126.570667),
        //관심지역 첫번째의 좌표가 들어가야 함.
        level: 5,
      };
      
      this.map = new kakao.maps.Map(container, options);
      this.geocoder = new kakao.maps.services.Geocoder();
      
    },
    test(){
        console.log("in");
    },
    async initSido(){
        await localhost.get("sigugun/sido")
        .then( ({data}) => {
            this.sidoAry = data;
        })
    },
    async changeSido(){
        this.gugunAry = [];
        this.dongAry = [];
        await localhost.get("sigugun/gugun/"+this.sidoSelect.sidoCode)
        .then( ({data})=>{
          this.gugunAry = data;
        })
    },
    async changeGugun(){
      this.dongAry = [];
        await localhost.get("sigugun/dong/"+this.gugunSelect.gugunCode)
        .then(({data})=>{
          console.log(data);
            if(data.status == "success"){
              this.dongAry = data.data;
            }else{
              this.dongAry = [
                {
                  dongName: "매물이 없습니다.",
                  dongCode: "12345678",
                }
              ]
            }
        })
        let that = this;
        for(let i=0; i<this.dongAry.length; i++){
          await this.geocoder.addressSearch(this.sidoSelect.sidoName+" "+this.gugunSelect.gugunName+" " +this.dongAry[i].dongName+" "+this.dongAry[i].jibun, function(result, status) {
              // console.log(this)

              // 정상적으로 검색이 완료됐으면 
              if (status === kakao.maps.services.Status.OK) {
                  
                  var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                  //lat : y좌표, lng : x좌표
                  // console.log("y: "+result[0].y + "x: "+result[0].x);
                  // 결과값으로 받은 위치를 마커로 표시합니다
                  var marker = new kakao.maps.Marker({
                      map: that.map,
                      position: coords
                  });
                  let lat = result[0].y;
                  let lng = result[0].x;
                  that.dongAry[i].lat = lat;
                  that.dongAry[i].lng = lng;
                  console.log(lat, lng);
                  // 인포윈도우로 장소에 대한 설명을 표시합니다
                  // var infowindow = new kakao.maps.InfoWindow({
                  //     content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
                  // });
                  // infowindow.open(that.map, marker);
                  markers.push(marker);
                  // marker.setMap(that.map);
                  // that.map.setCenter(coords);
    
              } 
          });
        }
    },
    async getApt(gugunCode){
      await localhost.get("sigugun/gu/"+gugunCode)
        .then( res => {
          // this.extraAry = data.data;
            console.log(res);
            //공공데이터에서 새롭게 데이터를 받아옴
        })
        
        
    },
    
    getDistanceFromLatLonInKm(lat2, lng2) {
      let lat1 = this.selectAttention.lat,
          lng1= this.selectAttention.lng
          // lat2 = 126.671359,
          // lng2 = 37.6397252

        function deg2rad(deg) { 
          return deg * (Math.PI/180) 
        } 
        var R = 6371; // Radius of the earth in km 
        var dLat = deg2rad(lat2-lat1); // deg2rad below 
        var dLon = deg2rad(lng2-lng1); 
        var a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2); 
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
        var d = R * c; // Distance in km 
        return d; 
    },
    addressToMarker(){
      let that = this;
      this.removeMarker();
      this.geocoder.addressSearch(this.atten.address, function(result, status) {
        // console.log(this)

            // 정상적으로 검색이 완료됐으면 
            if (status === kakao.maps.services.Status.OK) {
                // console.log("시 군 동"+that.dongSelect.dongName+" "+that.aptAry[i].roadName+" "+that.aptAry[i].aptName);
                // console.log("마커 찍힘"+that.aptAry[i].roadName+" "+that.aptAry[i].aptName+" "+that.aptAry[i].jibun)
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                //lat : y좌표, lng : x좌표
                // console.log("y: "+result[0].y + "x: "+result[0].x);
                // 결과값으로 받은 위치를 마커로 표시합니다
                var marker = new kakao.maps.Marker({
                    map: that.map,
                    position: coords
                });
                
                // 인포윈도우로 장소에 대한 설명을 표시합니다
                // var infowindow = new kakao.maps.InfoWindow({
                //     content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
                // });
                // infowindow.open(that.map, marker);

                marker.setMap(that.map);
                that.map.setCenter(coords);
  
            } 
        });
    },
   
    async changeDong(){
      this.aptAry = [];
      this.removeMarker();
      for(let i=0; i<this.dongAry.length; i++){
        if(this.dongAry[i].dongCode == this.dongSelect.dongCode){
          this.aptAry.push(this.dongAry[i]);   
        }
      }
      let that = this;//마커 찍을 때 this를 못 찾아서 that용법을 사용...!
      // 주소로 좌표를 검색합니다
      for(let i=0; i<this.aptAry.length; i++){
        let lat;
        let lng;
        await this.geocoder.addressSearch(this.sidoSelect.sidoName+" "+this.gugunSelect.gugunName+" " +this.aptAry[i].dongName+" "+this.aptAry[i].jibun, function(result, status) {
            // console.log(this)

            // 정상적으로 검색이 완료됐으면 
            if (status === kakao.maps.services.Status.OK) {
                // console.log("시 군 동"+that.dongSelect.dongName+" "+that.aptAry[i].roadName+" "+that.aptAry[i].aptName);
                // console.log("마커 찍힘"+that.aptAry[i].roadName+" "+that.aptAry[i].aptName+" "+that.aptAry[i].jibun)
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                //lat : y좌표, lng : x좌표
                // console.log("y: "+result[0].y + "x: "+result[0].x);
                // 결과값으로 받은 위치를 마커로 표시합니다
                var marker = new kakao.maps.Marker({
                    map: that.map,
                    position: coords
                });
                lat = result[0].y;
                lng = result[0].x;
                that.aptAry[i].lat = lat;
                that.aptAry[i].lng = lng;
                console.log(lat, lng);
                // 인포윈도우로 장소에 대한 설명을 표시합니다
                // var infowindow = new kakao.maps.InfoWindow({
                //     content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
                // });
                // infowindow.open(that.map, marker);
                markers.push(marker);
                // marker.setMap(that.map);
                that.map.setCenter(coords);
  
            } 
        });
               

        
      }
      // var coords = new kakao.maps.LatLng(this.aptAry[this.aptAry.length/2].lat, this.aptAry[this.aptAry.length/2].lng);
      console.log(this.aptAry);
      // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
      
    },
    async check(km){
      let that = this;
      await this.removeMarker();

      for(let i=0; i<this.dongAry.length; i++){
        // let distance = await this.getDistanceFromLatLonInKm(this.dongAry[i].lat, this.dongAry[i].lng);
        await this.geocoder.addressSearch(this.sidoSelect.sidoName+" "+this.gugunSelect.gugunName+" " +this.dongAry[i].dongName+" "+this.dongAry[i].jibun, function(result, status) {
            // console.log(this)

            // 정상적으로 검색이 완료됐으면 
            if (status === kakao.maps.services.Status.OK) {
                // console.log("시 군 동"+that.dongSelect.dongName+" "+that.aptAry[i].roadName+" "+that.aptAry[i].aptName);
                // console.log("마커 찍힘"+that.aptAry[i].roadName+" "+that.aptAry[i].aptName+" "+that.aptAry[i].jibun)
                var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
                //lat : y좌표, lng : x좌표
                // console.log("y: "+result[0].y + "x: "+result[0].x);
                // 결과값으로 받은 위치를 마커로 표시합니다
                var marker = new kakao.maps.Marker({
                    map: that.map,
                    position: coords
                });
                if(that.getDistanceFromLatLonInKm(result[0].y, result[0].x) <= km ){
                  console.log(that.dongAry[i].aptName);
                  markers.push(marker);
                  marker.setMap(that.map);
                }else{
                  // console.log("어림도 없지",result[0].y, result[0].x)
                }
                
  
            } 
        });
        
      }
    },
    
    removeMarker(){
      console.log("지우기 호출")
      for(let i=1; i<markers.length; i++){
        markers[i].setMap(null);
      }
      markers = [];
    },
    async addAttention(item){
      this.removeMarker();
      let that = this;
      let sigugunCode = item.sigungucode
      let sidoCode = sigugunCode[0]+""+sigugunCode[1];
        this.sidoSelect = {
          sidoCode: sigugunCode[0]+""+sigugunCode[1],
          sidoName: "대기",
        }
        await this.changeSido();
        for(let i=0; i<this.sidoAry.length; i++){
          if(sidoCode == this.sidoAry[i].sidoCode){
            this.sidoSelect = {
              sidoCode : sidoCode,
              sidoName : this.sidoAry[i].sidoName,
            }
            break;
          }
        }
        this.gugunSelect = {
          gugunCode: sigugunCode,
          gugunName: "관심지역"
        }
        await this.changeGugun();
        for(let i=0; i<this.gugunAry.length; i++){
          if(sigugunCode == this.gugunAry[i].gugunCode){
            this.gugunSelect = {
              gugunCode : sigugunCode,
              gugunName : this.gugunAry[i].gugunName,
            }
            break;
          }
        }
        await this.geocoder.addressSearch(item.address, function(result, status) {
          // console.log(this)

              // 정상적으로 검색이 완료됐으면 
          if (status === kakao.maps.services.Status.OK) {
              var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
              
              var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png'; // 마커이미지의 주소입니다    
              var imageSize = new kakao.maps.Size(24, 35); 
              var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize); 

              //lat : y좌표, lng : x좌표
              // console.log("y: "+result[0].y + "x: "+result[0].x);
              // 결과값으로 받은 위치를 마커로 표시합니다
              var marker = new kakao.maps.Marker({
                  map: that.map,
                  position: coords,
                  image: markerImage
              });
              
              
              that.selectAttention.lat =  result[0].y;
              that.selectAttention.lng =  result[0].x;

              marker.setMap(that.map);
              markers.push(marker);
              that.map.setCenter(coords);

          } 
        });
        


    },
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.atten.detailaddress !== "") {
            this.atten.detailaddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.atten.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.atten.address = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.atten.detailaddress += data.bname;
              this.atten.bname = data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.atten.detailaddress +=
                this.atten.detailaddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            // if (this.atten[n].detailAddress !== "") {
            //   this.atten[n].detailAddress = `(${this.atten[n].detailAddress})`;
            // }
          } else {
            this.atten.detailaddress = "";
          }
          // 우편번호를 입력한다.
          this.atten.postnum = data.zonecode;

          //this.atten.bname[n] = data.bname;
          this.atten.sigungucode = data.sigunguCode;

          // 주소+상세주소
          this.atten.totalAddress = this.atten.address;
          if (this.atten.detailaddress !== "")
            this.atten.totalAddress += `(${this.atten.detailaddress})`;

          this.addressToMarker();
        },
      }).open();
    },
    searchAttention() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.addAtten.detailaddress !== "") {
            this.addAtten.detailaddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.addAtten.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.addAtten.address = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.addAtten.detailaddress += data.bname;
              this.addAtten.bname = data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.addAtten.detailaddress +=
                this.addAtten.detailaddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            // if (this.atten[n].detailAddress !== "") {
            //   this.atten[n].detailAddress = `(${this.atten[n].detailAddress})`;
            // }
          } else {
            this.addAtten.detailaddress = "";
          }
          // 우편번호를 입력한다.
          this.addAtten.postnum = data.zonecode;

          //this.atten.bname[n] = data.bname;
          this.addAtten.sigungucode = data.sigunguCode;

          // 주소+상세주소
          this.addAtten.totalAddress = this.addAtten.address;
          console.log(this.addAtten.address);
          if (this.addAtten.detailaddress !== "")
            this.addAtten.totalAddress += `(${this.addAtten.detailaddress})`;

          this.addressToMarker();
        },
      }).open();
    },
    async registAttention(){
      
            this.addAtten.userid = this.userInfo.userid;
            await localhost({
              method: "post",
              url: "/attention/regist",
              data: this.addAtten,
            }).then((data) => {
              console.log("입력",data);
            });
      
      let token = sessionStorage.getItem("access-token");
        if (this.isLogin) {
          await this.getAttentionInfo(token);
        }
      this.extraAttentionInfo = this.attentionInfo;
      this.extraAttentionInfo.push();
      // this.tab = 0;
      // await localhost({
      //         method: "post",
      //         url: "/attention/regist",
      //         data: this.addAtten,
      //       }).then((data) => {
      //         console.log(data);
      //       });
      this.cancel();
    },
    cancel(){
      this.addAtten = {
                userid: "",
                bname: "",
                sigungucod: "",
                nickname: "",
                postnum: "",
                address: "",
                detailaddress: "",
                totalAddress: "",
                lat: "",
                lng: "",
            };
      this.tab=0;
    }
  },
}
</script>

<style>

</style>