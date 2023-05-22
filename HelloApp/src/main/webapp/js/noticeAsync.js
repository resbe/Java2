/**
 * 	noticeAsync.js
 */
// async function()...

async function loadData(){
	let promise = await fetch('noticeListJson.do');
	let promise1 = promise.json(); //json -> object.
	let fields = ['noticeId', 'noticeTitle', 'noticeWriter','attachFile']
	promise1.forEach(function(item) {
		let tr = document.createElement('tr');
		for(let prop in item){
			let td = document.createElement('td');
			td.innerText = itemp[prop];
			tr.append(td);
		}
		document.getElementById('noticeList').append(tr);
	});
	
}

document.addEventListener('DOMContentLoaded',function(){
	loadData();
})