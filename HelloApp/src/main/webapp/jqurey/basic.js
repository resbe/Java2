  // basic.js
  
  // document.addEventListener('DOMContentLoaded', function(){
  //   let liTag = document.createElement('li'); //Document object Model.
  //   liTag.innerText = 'Cherry';
  //   liTag.append()
  //   console.log(liTag);
  //   document.querySelector('#list').append(liTag);
  // })
  // javascript 객체 vs. jQuery 객체.
  $(document).ready(function(){
    //  let elem = $('<li />'); 
    //  // elem.innerText : 에러. 제이쿼리 객체는 자바스크립트의 속성을 쓸수없음.
    //  elem.text('Cherry');
    //  console.log(elem);
     $('#list').append($('<li />').text('Cherry'),//
                       $('<li />').text('Mango') );
   })