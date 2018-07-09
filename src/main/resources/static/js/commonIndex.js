$(function(){
	//加载轮播
	ajaxUtil.get(true,'',carouselPath,carouseCallback);	
	function carouseCallback(res){
		if(res.status){
			var html='';
			$.each(res.data,function(i,item){
				html +='<div style="background-image: url('+item.url+');background-size: cover;"></div>';
//				console.log("item ="+JSON.stringify(item));
			});
			$('#loadCarousel').empty().html(html);
			//常规轮播
			layui.carousel.render({
				elem : '#carousel',
				arrow : 'always',
				full:false,
				width: '100%',
				height: '768px',
				interval: 5000
			});
		}
	}
	
	//处理菜单
	ajaxUtil.get(true,'',profilePath,profileCallback);	
	function profileCallback(res){
		if(res.status){
			$('#login').hide();
			$('#register').hide();
			$('#profile').show();
			$('#changePwd').show();
			$('#showInfo').html('<img src="./imgs/photo.jpg" class="layui-nav-img" />退出<span class="layui-nav-more"></span>');
			if(res.data.isAdmin=='1'){
				$('#console').show();
			}else{
				$('#console').hide();
			}
		}else{
			$('#login').show();
			$('#register').show();
			$('#consoleIndex').hide();
			$('#profile').hide();
			$('#changePwd').hide();
			$('#quit').hide();
			$('#showInfo').html('<img src="./imgs/photo.jpg" class="layui-nav-img" />注册<span class="layui-nav-more"></span>');
		}
	}
});


new BaiduMap({
	id : "map",
	title : {
		text : "沈阳师范大学",
		className : "title"
	},
	content : {
		className : "content",
		text : [ "地址：沈阳市黄河北大街253号", "邮编：110034" ]
	},
	point : {
		lng : "123.4152600000",
		lat : "41.9087810000"
	},
	level : 15,
	zoom : true,
	type : [ "地图", "卫星", "三维" ],
	width : 320,
	height : 70,
	icon : {
		url : "./imgs/icon.png",
		width : 36,
		height : 36
	}
});