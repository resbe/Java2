export default {
  name : 'my-board-read',
  template : `<div>
  <table id="list">
    <tr>
        <td style="width:50px;">글제목</td>
        <td>{{ item.title }}</td>
    </tr>
    <tr style="height:300px">
      <td colspan="2">{{ item.content }}</td>
    </tr>
  </table>
  <router-link tag="button" style="float:right;" v-bind:to="{ name : 'boardList' }">목록</router-link>
</div>`,
  data : function(){
    return {
      board : {}
    }
  },
  props : ['item'],
  created : function (){
    fetch('http://192.168.0.51:8081/myserver/boardInfo?no='+this.item.no)
    .then(resopns => Response.json())
    .then(data => {
        this.board = data;
    })
    .catch(err => console.log(err));
  }
}