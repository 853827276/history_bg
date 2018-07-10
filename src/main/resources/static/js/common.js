/**
 * 公共的js
 */
var defaultImgUrl ='/imgs/default.jpg';
var contextPath='/history/'
var domain = document.location.origin+contextPath;

var bgUrl ='http://127.0.0.1:10086/';
var bgUrl ='/';
var uploadPath=bgUrl+'upload/file?'+new Date().getTime();
var registerPath=bgUrl+'register?'+new Date().getTime();
var loginPath=bgUrl+'login';//login/{userName}/{password}
var addmessagePath=bgUrl+'index/addMessage?'+new Date().getTime();

var carouselPath=bgUrl+'index/carouselList?'+new Date().getTime();
var profilePath=bgUrl+'index/findById?'+new Date().getTime();
var consolePath=bgUrl+'console/index?'+new Date().getTime();
var consoleUserListPath=bgUrl+'console/user/list?'+new Date().getTime();

function formatDate(time){
    var date = new Date(time);

    var year = date.getFullYear(),
        month = date.getMonth() + 1,//月份是从0开始的
        day = date.getDate(),
        hour = date.getHours(),
        min = date.getMinutes(),
        sec = date.getSeconds();
    var newTime = year + '-' +
                month + '-' +
                day + ' ' +
                hour + ':' +
                min + ':' +
                sec;
    return newTime;         
}

var ajaxUtil=(function(){		
	return {
		jsonp: function(async,data,url,callback){
			$.ajax({
				url:url,
				type:'get',
				dataType:'jsonp',
				jsonp:"callback",
				data:data,
				async:async,
				success:function(res){
					callback(res);
				}
			});
		},
		get: function(async,data,url,callback){
			$.ajax({
				url:url,
				type:'get',
				data:data,
				async:async,
				success:function(res){
					callback(res);
				}
			});
		},
		post: function(async,data,url,callback){
			$.ajax({
				url:url,
				type:'post',
				data:data,
				async:async,
				success:function(res){
					callback(res);
				}
			});
		},
		formPost:function(data,url,callback){
			$.post(url,data,function(res){callback(res)});
		}
	}
})()