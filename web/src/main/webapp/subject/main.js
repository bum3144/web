$(document).ready(function(){

		
	$.ajax('list.ajax', {
		type: 'GET',
		dataType: 'json', 	
		success: function(members){				
			$.each(member,function(key){
				var memberTable = $("memberTable");
				var list = result[key];				
				var content = "<table>";				
				for(i=0;i<list.length;i++){
					content += "<tr>";
					content +="<td>" + list[i].NO + "<td>";
					content +="<td>" + list[i].TITLE + "<td>";
					content +="</tr>";
				}
				content += "</table>";
				$("#table").appendChild(content);
			});
		},
		error: function(msg){
			alert('실행 중 오류 발생!');
			console.log(msg);
		}
	});

});
