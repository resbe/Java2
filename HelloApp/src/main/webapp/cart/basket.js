export let basket = {
    totalCount: 0,
    totalPrice: 0,
    delCheckedItem: function () {
        console.log('delCheckedItem');
        $("input:checkbox[name='buy']:checked").each(function(k,kVal){
			let a =kVal.parentElement.parentElement.parentElement;
			$(a).remove();
		})
		this.updateUI();
    },
    delAllItem: function () {
		$('#basket').empty();
        console.log('delAllItem');
        console.log($('.sum'));
        this.updateUI();
    },
    reCalc: function () {
        console.log('reCalc');
 
        let price;
        let count;
        let count2 = 0;
        let price2 = 0;
        $.each($('.row.data'),function(index,item){
			price = ($(item).find('.p_price').val());
			count =($(item).find('.p_num').val());
			
			console.log(price * count); 
			price2 += parseInt( price * count);
			count2 += parseInt(count);
		})
		this.totalPrice = price2; 
		this.totalCount = count2;        
    },
    updateUI: function () {
        console.log('updateUI');
        this.reCalc();
        document.querySelector('#sum_p_num').textContent = '상품개수: ' + this.totalCount.formatNumber() + '개'
        document.querySelector('#sum_p_price').textContent = '합계금액: ' + this.totalPrice.formatNumber() + '원'
    },
    changePNum: function (pos) {	
	/*	let target = document.querySelectorAll('div.row.data:nth-of-type('+pos+')'); */
		console.log("changePNum");
		if(event.target.tagName =='input'){
			if(event.target.value()== null){
			 event.target.value() == 0;
				
			}
		}else if(event.target.tagName == 'I'){
			if(event.target.classList.contains('up')){
				//console.log(Number($('#p_num'+pos).val()))
			 console.log( $('#p_num'+pos).val())
			 $('#p_num'+pos).val(Number($('#p_num'+pos).val())+1) 
			 console.log( $('#p_num'+pos).val())
				//N번째 input의 value= +1;				
			}else{
				if($('#p_num'+pos).val()>0){
				 console.log( $('#p_num'+pos).val())
			 $('#p_num'+pos).val(Number($('#p_num'+pos).val())-1)
			  
			 console.log( $('#p_num'+pos).val())
			 }
			}
		} this.updateUI();
		
    },
    delItem: function (e) {	
		$(e.target).parent().parent().parent().remove();
        console.log('delItem');
    },
    cartList: function () {
		
        cartItems.forEach((item, idx) => {
            let template = document.querySelector('#template>div.row.data').cloneNode(true);
            template.querySelector('.img>img').setAttribute('src', '../img/' + item.image)
            template.querySelector('.pname>span').textContent = item.productNm
            template.querySelector('.basketprice>input').value = item.price
            template.querySelector('.basketprice').childNodes[2].textContent = item.price.formatNumber() + "원"
            template.querySelector('.updown>input').value = item.qty
            template.querySelector('.updown>input').setAttribute('value', item.qty)
            template.querySelector('.updown>input').setAttribute('id', 'p_num' + (idx + 1));
            template.querySelector('.sum').textContent = (item.price * item.qty).formatNumber() + "원"
            document.querySelector('#basket').append(template)
        })
    }
};

var cartItems = [{
        no: 1,
        productNm: '이것이 민트다.',
        qty: 2,
        price: 12000,
        image: 'item1.PNG'
    },
    {
        no: 2,
        productNm: '와 아이스크림.',
        qty: 1,
        price: 22000,
        image: 'item2.PNG'
    },
    {
        no: 3,
        productNm: '모나카 케익.',
        qty: 1,
        price: 13600,
        image: 'item3.PNG'
    }
]

// 숫자 3자리 콤마찍기
Number.prototype.formatNumber = function () {
    if (this == 0) return 0;
    let regex = /(^[+-]?\d+)(\d{3})/;
    let nstr = (this + '');
    while (regex.test(nstr)) nstr = nstr.replace(regex, '$1' + ',' + '$2');
    return nstr;
};