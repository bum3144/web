$(document).ready(function(){	
	$.ajax('list.ajax', {
		type: 'GET',
		dataType: 'json', 			
	
		success: function(result){
			
			var listTable = $("listTable");
			var tr = null, td = null, a = null;
			result.forEach(function(list){
				tr = document.createElement('tr');
				tr.setAttribute('class', 'dataRow');
				[list.no,
				 list.title].forEach(function(value,index,arr){
					td = document.createElement('td');
					if (index == 1) {
						td.innerHTML = '<a href="#"' +
							' onclick="readMember(' + arr[0] +
							'); return false;">' +
							value + '</a>';
					} else {
						td.innerHTML = value;
					} 
					tr.appendChild(td);
				});
				td = document.createElement('td');
				a = document.createElement('a');
				a.href = '#';
				a.onclick = function(event) {
					deleteMember(list.no);
					event.preventDefault();
				};
			
			
				a.innerHTML = '삭제';
				td.appendChild(a);
				tr.appendChild(td);
	
				listTable.appendChild(tr);
			});
		},
		
/*		error: function(msg){
			alert('정보를 읽을 수 없습니다!');
			console.log(msg);
		}*/
		error: function(msg){
			alert('로그인 실행 중 오류 발생!');
			console.log(msg);
		}
	});

});


