/**
 * 处理后台菜单显示
 */
var currentUrl =window.location.pathname;
//$.each($('#leftMenu dl dd'),function(i,v){
//	var menuUrl =v.innerHTML;
//
//	console.log("i="+i+"menuUrl="+typeof(menuUrl));
//	if(currentUrl==menuUrl){
//		$(this).addClass('layui-this');
//	}
//});

$('#leftMenu dl dd').each(function(i,v){
	var menuUrl =v.innerHTML;
	if(menuUrl.indexOf(currentUrl)>0){
		$(this).addClass('layui-this');
	}
});